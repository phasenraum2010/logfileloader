package org.woehlke.logfileloader.eai.pipelines;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.integration.annotation.MessageEndpoint;
import org.woehlke.logfileloader.core.entities.*;
import org.woehlke.logfileloader.core.services.*;
import org.woehlke.logfileloader.eai.events.ProcessOneLogfileLineEvent;

import javax.inject.Inject;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

/**
 * Created with IntelliJ IDEA.
 * User: Fert
 * Date: 02.09.13
 * Time: 14:10
 * To change this template use File | Settings | File Templates.
 */
@MessageEndpoint("processLogfileLinesPipeline")
public class PostProcessingPipeline {

    private final static Logger LOGGER = LoggerFactory.getLogger(PostProcessingPipeline.class);

    @Inject
    private LogfileLineItemService logfileLineItemService;

    @Inject
    private BrowserService browserService;

    @Inject
    private DayService dayService;

    @Inject
    private HttpCodeService httpCodeService;

    @Inject
    private IpService ipService;

    @Inject
    private RequestService requestService;

    public ProcessOneLogfileLineEvent log(ProcessOneLogfileLineEvent event) {
        //LOGGER.info(event.toString());
        return event;
    }

    public ProcessOneLogfileLineEvent getIpNumber(ProcessOneLogfileLineEvent event) {
        String line = event.getLine().getLine();
        String ipString = line.split(" ")[0];
        try {
            if(ipService.find(ipString)==null){
                ipService.createOrFetch(ipString);
            }
        } catch (Exception e){
            LOGGER.error(e.getMessage());
        }
        event.setIp(ipString);
        return event;
    }

    public ProcessOneLogfileLineEvent getTimeStamp(ProcessOneLogfileLineEvent event) {
        String line = event.getLine().getLine();
        String rest = line.split("\\[")[1];
        String datetimeString = rest.split("\\]")[0].split(" ")[0];
        //LOGGER.info("datetimeString: "+datetimeString);
        String timezoneString = rest.split("\\]")[0].split(" ")[1];
        //LOGGER.info("timezoneString: "+timezoneString);
        Locale locales[] = {Locale.GERMANY,Locale.ENGLISH};
        for(final Locale locale:locales){
            SimpleDateFormat parserSDF = new SimpleDateFormat("dd/MMM/yyyy:HH:mm:ss", locale);
            Date date = null;
            try {
                int timezone = Integer.parseInt(timezoneString);
                Date datetime = parserSDF.parse(datetimeString);
                timezone -= 100; //CEST
                timezone *= 60 * 1000;
                long timestamp = datetime.getTime() + timezone;
                date = new Date(timestamp);
                event.setDatetime(date);
                if(dayService.find(date)==null){
                    dayService.createOrFetch(date);
                }
            } catch (NumberFormatException u) {
                LOGGER.error(u.getMessage());
            } catch (ParseException e) {
                LOGGER.error(e.getMessage());
            } catch (Exception e) {
                LOGGER.warn(e.getMessage());
            }
            //LOGGER.info("### "+event.toString());
        }
        return event;
    }

    public ProcessOneLogfileLineEvent getRequestString(ProcessOneLogfileLineEvent event) {
        String line = event.getLine().getLine();
        String requestLine = "UNDEFINED";
        try {
            requestLine = line.split("\"")[1].split(" ")[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            LOGGER.warn(e.getMessage());
        }
        event.setRequestLine(requestLine);
        try {
            if(requestService.find(requestLine)==null){
                requestService.createOrFetch(requestLine);
            }
        } catch (Exception e) {
            LOGGER.warn(e.getMessage());
        }
        //LOGGER.info(requestLine);
        return event;
    }

    public ProcessOneLogfileLineEvent getHttpCode(ProcessOneLogfileLineEvent event) {
        String line = event.getLine().getLine();
        String httpCode = "UNDEFINED";
        try {
            httpCode = line.split("\"")[2].split(" ")[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            LOGGER.warn(e.getMessage());
        }
        event.setHttpCode(httpCode);
        try {
            if(httpCodeService.find(httpCode)==null){
                httpCodeService.createOrFetch(httpCode);
            }
        } catch (Exception e) {
            LOGGER.warn(e.getMessage());
        }
        //LOGGER.info(httpCode);
        return event;
    }

    public ProcessOneLogfileLineEvent getBrowser(ProcessOneLogfileLineEvent event) {
        String line = event.getLine().getLine();
        String browser = "UNDEFINED";
        try {
            browser = line.split("\"")[5];
        } catch (ArrayIndexOutOfBoundsException e) {
            LOGGER.warn(e.getMessage());
        }
        event.setBrowser(browser);
        try {
            if(browserService.find(browser)==null){
                browserService.createOrFetch(browser);
            }
        } catch (Exception e) {
            LOGGER.warn(e.getMessage());
        }
        //LOGGER.info(browser);
        return event;
    }

    public ProcessOneLogfileLineEvent pushIntoDatabase(ProcessOneLogfileLineEvent event) {
        //LOGGER.info("pushIntoDatabase: "+event.toString());
        LogfileLineItem logfileLineItem = new LogfileLineItem();
        logfileLineItem.setLine(event.getLine().getLine());
        logfileLineItem.setTime(event.getDatetime());
        Browser browser = browserService.find(event.getBrowser());
        Day day = dayService.find(event.getDatetime());
        HttpCode httpCode = httpCodeService.find(event.getHttpCode());
        Ip ip = ipService.find(event.getIp());
        Request request = requestService.find(event.getRequestLine());
        logfileLineItem.setBrowser(browser);
        logfileLineItem.setIp(ip);
        logfileLineItem.setHttpCode(httpCode);
        logfileLineItem.setRequest(request);
        logfileLineItem.setDay(day);
        logfileLineItemService.save(logfileLineItem);
        return event;
    }
}
