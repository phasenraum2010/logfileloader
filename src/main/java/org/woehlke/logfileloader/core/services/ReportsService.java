package org.woehlke.logfileloader.core.services;

import org.woehlke.logfileloader.core.dao.model.BrowserReportItem;
import org.woehlke.logfileloader.core.dao.model.HttpCodeReportItem;
import org.woehlke.logfileloader.core.dao.model.IpNumbersReportItem;
import org.woehlke.logfileloader.core.dao.model.PageReportItem;
import org.woehlke.logfileloader.core.entities.HttpCode;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: tw
 * Date: 04.09.13
 * Time: 12:13
 * To change this template use File | Settings | File Templates.
 */
public interface ReportsService {

    List<IpNumbersReportItem> listIpNumbers();

    List<BrowserReportItem> listBrowser();

    List<PageReportItem> listPages();

    List<PageReportItem> listUrlsForBrowser(long browserId);

    List<BrowserReportItem> listBrowserForUrls(long urlId);

    List<HttpCodeReportItem> listHttpCodes();

    List<PageReportItem> listUrlsForHttpCodes(long httpCodeId);

    List<BrowserReportItem> listBrowserForHttpCodes(long httpCodeId);

    HttpCode findHttpCodeById(long httpCodeId);

    List<IpNumbersReportItem> listIpNumbersForHttpCodes(long httpCodeId);
}
