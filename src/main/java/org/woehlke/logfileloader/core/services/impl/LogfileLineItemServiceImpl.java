package org.woehlke.logfileloader.core.services.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.woehlke.logfileloader.core.entities.*;
import org.woehlke.logfileloader.core.repositories.*;
import org.woehlke.logfileloader.core.services.LogfileLineItemService;

import javax.inject.Inject;

/**
 * Created with IntelliJ IDEA.
 * User: tw
 * Date: 03.09.13
 * Time: 16:00
 * To change this template use File | Settings | File Templates.
 */
@Service
public class LogfileLineItemServiceImpl implements LogfileLineItemService {

    @Inject
    private BrowserRepository browserRepository;

    @Inject
    private HttpCodeRepository httpCodeRepository;

    @Inject
    private IpRepository ipRepository;

    @Inject
    private LogfileLineItemRepository logfileLineItemRepository;

    @Inject
    private RequestRepository requestRepository;

    @Inject
    private DayRepository dayRepository;

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
    public void save(LogfileLineItem logfileLineItem) {
        Browser browser = browserRepository.findByBrowser(logfileLineItem.getBrowser().getBrowser());
        if (browser == null) {
            browser = browserRepository.saveAndFlush(logfileLineItem.getBrowser());
        }
        logfileLineItem.setBrowser(browser);
        HttpCode httpCode = httpCodeRepository.findByCode(logfileLineItem.getHttpCode().getCode());
        if (httpCode == null) {
            httpCode = httpCodeRepository.saveAndFlush(logfileLineItem.getHttpCode());
        }
        logfileLineItem.setHttpCode(httpCode);
        Ip ip = ipRepository.findByIp(logfileLineItem.getIp().getIp());
        if (ip == null) {
            ip = ipRepository.saveAndFlush(logfileLineItem.getIp());
        }
        logfileLineItem.setIp(ip);
        Request request = requestRepository.findByRequest(logfileLineItem.getRequest().getRequest());
        if (request == null) {
            request = requestRepository.saveAndFlush(logfileLineItem.getRequest());
        }
        logfileLineItem.setRequest(request);
        Day day = dayRepository.findByDay(logfileLineItem.getDay().getDay());
        if(day == null){
            day =  dayRepository.saveAndFlush(logfileLineItem.getDay());
        }
        logfileLineItem.setDay(day);
        logfileLineItemRepository.saveAndFlush(logfileLineItem);
    }
}
