package org.woehlke.logfileloader.core.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.woehlke.logfileloader.core.dao.model.BrowserReportItem;
import org.woehlke.logfileloader.core.dao.model.HttpCodeReportItem;
import org.woehlke.logfileloader.core.dao.model.IpNumbersReportItem;
import org.woehlke.logfileloader.core.dao.model.PageReportItem;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: tw
 * Date: 04.09.13
 * Time: 12:21
 * To change this template use File | Settings | File Templates.
 */
public interface ReportsDao {

    Page<IpNumbersReportItem> listIpNumbers(Pageable pageable);

    Page<BrowserReportItem> listBrowser(Pageable pageable);

    Page<PageReportItem> listPages(Pageable pageable);

    List<PageReportItem> listUrlsForBrowser(long browserId);

    List<BrowserReportItem> listBrowserForUrls(long urlId);

    List<HttpCodeReportItem> listHttpCodes();

    List<PageReportItem> listUrlsForHttpCodes(long httpCodeId);

    List<BrowserReportItem> listBrowserForHttpCodes(long httpCodeId);

    List<IpNumbersReportItem> listIpNumbersForHttpCodes(long httpCodeId);

    List<PageReportItem> listUrlsForIpNumber(long ipNumberId);
}
