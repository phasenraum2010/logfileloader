package org.woehlke.logfileloader.core.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.woehlke.logfileloader.core.dao.model.*;

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

    Page<PageReportItem> listUrlsForBrowser(long browserId,Pageable pageable);

    Page<BrowserReportItem> listBrowserForUrls(long urlId, Pageable pageable);

    Page<HttpCodeReportItem> listHttpCodes(Pageable pageable);

    Page<PageReportItem> listUrlsForHttpCodes(long httpCodeId, Pageable pageable);

    Page<BrowserReportItem> listBrowserForHttpCodes(long httpCodeId, Pageable pageable);

    Page<IpNumbersReportItem> listIpNumbersForHttpCodes(long httpCodeId, Pageable pageable);

    Page<PageReportItem> listUrlsForIpNumber(long ipNumberId, Pageable pageable);

    Page<TimelineDaysItem> getTimelineDays(Pageable pageable);
}
