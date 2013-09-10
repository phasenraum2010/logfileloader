package org.woehlke.logfileloader.core.services.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.woehlke.logfileloader.core.dao.ReportsDao;
import org.woehlke.logfileloader.core.dao.model.*;
import org.woehlke.logfileloader.core.entities.*;
import org.woehlke.logfileloader.core.repositories.*;
import org.woehlke.logfileloader.core.services.ReportsService;

import javax.inject.Inject;

/**
 * Created with IntelliJ IDEA.
 * User: tw
 * Date: 04.09.13
 * Time: 12:14
 * To change this template use File | Settings | File Templates.
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
public class ReportsServiceImpl implements ReportsService {

    @Inject
    private ReportsDao reportsDao;

    @Inject
    private HttpCodeRepository httpCodeRepository;

    @Inject
    private BrowserRepository browserRepository;

    @Inject
    private IpRepository ipRepository;

    @Inject
    private RequestRepository requestRepository;

    @Inject
    private DayRepository dayRepository;

    @Override
    public Page<IpNumbersReportItem> listIpNumbers(Pageable pageable) {
        return reportsDao.listIpNumbers(pageable);
    }

    @Override
    public Page<BrowserReportItem> listBrowser(Pageable pageable) {
        return reportsDao.listBrowser(pageable);
    }

    @Override
    public Page<PageReportItem> listPages(Pageable pageable) {
        return reportsDao.listPages(pageable);
    }

    @Override
    public Page<PageReportItem> listUrlsForBrowser(long browserId, Pageable pageable) {
        return reportsDao.listUrlsForBrowser(browserId,pageable);
    }

    @Override
    public Page<BrowserReportItem> listBrowserForUrls(long urlId, Pageable pageable) {
        return reportsDao.listBrowserForUrls(urlId,pageable);
    }

    @Override
    public Page<HttpCodeReportItem> listHttpCodes(Pageable pageable) {
        return reportsDao.listHttpCodes(pageable);
    }

    @Override
    public Page<PageReportItem> listUrlsForHttpCodes(long httpCodeId, Pageable pageable) {
        return reportsDao.listUrlsForHttpCodes(httpCodeId,pageable);
    }

    @Override
    public Page<BrowserReportItem> listBrowserForHttpCodes(long httpCodeId, Pageable pageable) {
        return reportsDao.listBrowserForHttpCodes(httpCodeId,pageable);
    }

    @Override
    public HttpCode findHttpCodeById(long httpCodeId) {
        return httpCodeRepository.findOne(httpCodeId);
    }

    @Override
    public Page<IpNumbersReportItem> listIpNumbersForHttpCodes(long httpCodeId, Pageable pageable) {
        return reportsDao.listIpNumbersForHttpCodes(httpCodeId,pageable);
    }

    @Override
    public Page<PageReportItem> listUrlsForIpNumber(long ipNumberId, Pageable pageable) {
        return reportsDao.listUrlsForIpNumber(ipNumberId,pageable);
    }

    @Override
    public Browser findBrowserById(long browserId) {
        return browserRepository.findOne(browserId);
    }

    @Override
    public Ip findIpById(long ipNumberId) {
        return ipRepository.findOne(ipNumberId);
    }

    @Override
    public Request findRequestById(long urlId) {
        return requestRepository.findOne(urlId);
    }

    @Override
    public Page<TimelineDaysItem> listDays(Pageable pageable) {
        return reportsDao.listDays(pageable);
    }

    @Override
    public Day findDayById(long dayId) {
        return dayRepository.findOne(dayId);
    }

    @Override
    public Page<HttpCodeReportItem> listHttpCodesForDay(long dayId, Pageable pageable) {
        return reportsDao.listHttpCodesForDay(dayId, pageable);
    }
}
