package org.woehlke.logfileloader.core.services.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.woehlke.logfileloader.core.dao.ReportsDao;
import org.woehlke.logfileloader.core.dao.model.BrowserReportItem;
import org.woehlke.logfileloader.core.dao.model.HttpCodeReportItem;
import org.woehlke.logfileloader.core.dao.model.IpNumbersReportItem;
import org.woehlke.logfileloader.core.dao.model.PageReportItem;
import org.woehlke.logfileloader.core.entities.HttpCode;
import org.woehlke.logfileloader.core.repositories.BrowserRepository;
import org.woehlke.logfileloader.core.repositories.HttpCodeRepository;
import org.woehlke.logfileloader.core.services.ReportsService;

import javax.inject.Inject;
import java.util.List;

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

    @Override
    public List<IpNumbersReportItem> listIpNumbers() {
        return reportsDao.listIpNumbers();
    }

    @Override
    public List<BrowserReportItem> listBrowser() {
        return reportsDao.listBrowser();
    }

    @Override
    public List<PageReportItem> listPages() {
        return reportsDao.listPages();
    }

    @Override
    public List<PageReportItem> listUrlsForBrowser(long browserId) {
        return reportsDao.listUrlsForBrowser(browserId);
    }

    @Override
    public List<BrowserReportItem> listBrowserForUrls(long urlId) {
        return reportsDao.listBrowserForUrls(urlId);
    }

    @Override
    public List<HttpCodeReportItem> listHttpCodes() {
        return reportsDao.listHttpCodes();
    }

    @Override
    public List<PageReportItem> listUrlsForHttpCodes(long httpCodeId) {
        return reportsDao.listUrlsForHttpCodes(httpCodeId);
    }

    @Override
    public List<BrowserReportItem> listBrowserForHttpCodes(long httpCodeId) {
        return reportsDao.listBrowserForHttpCodes(httpCodeId);
    }

    @Override
    public HttpCode findHttpCodeById(long httpCodeId) {
        return httpCodeRepository.findOne(httpCodeId);
    }

    @Override
    public List<IpNumbersReportItem> listIpNumbersForHttpCodes(long httpCodeId) {
        return reportsDao.listIpNumbersForHttpCodes(httpCodeId);
    }
}
