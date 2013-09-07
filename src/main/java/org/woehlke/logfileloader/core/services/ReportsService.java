package org.woehlke.logfileloader.core.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.woehlke.logfileloader.core.dao.model.BrowserReportItem;
import org.woehlke.logfileloader.core.dao.model.HttpCodeReportItem;
import org.woehlke.logfileloader.core.dao.model.IpNumbersReportItem;
import org.woehlke.logfileloader.core.dao.model.PageReportItem;
import org.woehlke.logfileloader.core.entities.Browser;
import org.woehlke.logfileloader.core.entities.HttpCode;
import org.woehlke.logfileloader.core.entities.Ip;
import org.woehlke.logfileloader.core.entities.Request;

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

    Page<PageReportItem> listPages(Pageable pageable);

    List<PageReportItem> listUrlsForBrowser(long browserId);

    List<BrowserReportItem> listBrowserForUrls(long urlId);

    List<HttpCodeReportItem> listHttpCodes();

    List<PageReportItem> listUrlsForHttpCodes(long httpCodeId);

    List<BrowserReportItem> listBrowserForHttpCodes(long httpCodeId);

    HttpCode findHttpCodeById(long httpCodeId);

    List<IpNumbersReportItem> listIpNumbersForHttpCodes(long httpCodeId);

    List<PageReportItem> listUrlsForIpNumber(long ipNumberId);

    Browser findBrowserById(long browserId);

    Ip findIpById(long ipNumberId);

    Request findRequestById(long urlId);
}
