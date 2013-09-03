package org.woehlke.logfileloader.eai.pipelines;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.integration.annotation.MessageEndpoint;
import org.woehlke.logfileloader.core.entities.*;
import org.woehlke.logfileloader.core.services.LogfileLineItemService;
import org.woehlke.logfileloader.eai.events.ProcessLogfileLinesEvent;

import javax.inject.Inject;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Fert
 * Date: 02.09.13
 * Time: 14:10
 * To change this template use File | Settings | File Templates.
 */
@MessageEndpoint("processLogfileLinesPipeline")
public class ProcessLogfileLinesPipeline {

    private final static Logger LOGGER = LoggerFactory.getLogger(ProcessLogfileLinesPipeline.class);

    @Inject
    private LogfileLineItemService logfileLineItemService;

    public ProcessLogfileLinesEvent log(ProcessLogfileLinesEvent event){
        LOGGER.info(event.toString());
        return event;
    }

    public ProcessLogfileLinesEvent getIpNumber(ProcessLogfileLinesEvent event) {
        String line = event.getLine().getLine();
        String ip = line.split(" ")[0];
        event.setIp(ip);
        return event;
    }

    public ProcessLogfileLinesEvent getTimeStamp(ProcessLogfileLinesEvent event) {
        String line = event.getLine().getLine();
        String rest = line.split("\\[")[1];
        String datetimeString = rest.split("\\]")[0].split(" ")[0];
        LOGGER.info(datetimeString);
        String timezoneString =  rest.split("\\]")[0].split(" ")[1];
        LOGGER.info(timezoneString);
        SimpleDateFormat parserSDF=new SimpleDateFormat("dd/MMM/yyyy:HH:mm:ss");
        try {
            int timezone = Integer.parseInt(timezoneString);
            Date datetime = parserSDF.parse(datetimeString);
            timezone -= 100; //CEST
            timezone *= 60*1000;
            event.setDatetime(new Date(datetime.getTime()+timezone));
        } catch (NumberFormatException u) {
            u.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        LOGGER.info(event.toString());
        return event;
    }

    public ProcessLogfileLinesEvent getRequestString(ProcessLogfileLinesEvent event) {
        String line = event.getLine().getLine();
        String requestLine = "UNDEFINED";
        try {
            requestLine = line.split("\"")[1].split(" ")[1];
        } catch (ArrayIndexOutOfBoundsException e){
           e.printStackTrace();
        }
        event.setRequestLine(requestLine);
        LOGGER.info(requestLine);
        return event;
    }

    public ProcessLogfileLinesEvent getHttpCode(ProcessLogfileLinesEvent event) {
        String line = event.getLine().getLine();
        String httpCode = "UNDEFINED";
        try {
            httpCode = line.split("\"")[2].split(" ")[1];
        } catch (ArrayIndexOutOfBoundsException e){
            e.printStackTrace();
        }
        event.setHttpCode(httpCode);
        LOGGER.info(httpCode);
        return event;
    }

    public ProcessLogfileLinesEvent getBrowser(ProcessLogfileLinesEvent event) {
        String line = event.getLine().getLine();
        String browser = "UNDEFINED";
        try {
            browser = line.split("\"")[5];
        } catch (ArrayIndexOutOfBoundsException e){
            e.printStackTrace();
        }
        event.setBrowser(browser);
        LOGGER.info(browser);
        return event;
    }

    public ProcessLogfileLinesEvent pushIntoDatabase(ProcessLogfileLinesEvent event) {
        Browser browser = new Browser();
        HttpCode httpCode = new HttpCode();
        Ip ip = new Ip();
        LogfileLineItem logfileLineItem = new LogfileLineItem();
        Request request = new Request();
        browser.setBrowser(event.getBrowser());
        httpCode.setCode(event.getHttpCode());
        ip.setIp(event.getIp());
        request.setRequest(event.getRequestLine());
        logfileLineItem.setLine(event.getLine().getLine());
        logfileLineItem.setBrowser(browser);
        logfileLineItem.setIp(ip);
        logfileLineItem.setHttpCode(httpCode);
        logfileLineItem.setRequest(request);
        logfileLineItemService.save(logfileLineItem);
        return event;
    }
}
