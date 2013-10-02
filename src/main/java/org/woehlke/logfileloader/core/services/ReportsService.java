package org.woehlke.logfileloader.core.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.woehlke.logfileloader.core.dao.model.*;
import org.woehlke.logfileloader.core.entities.*;

/**
 * Created with IntelliJ IDEA.
 * User: tw
 * Date: 04.09.13
 * Time: 12:13
 * To change this template use File | Settings | File Templates.
 */
public interface ReportsService {

    Page<IpNumbersReportItem> listIpNumbers(Pageable pageable);

    Page<BrowserReportItem> listBrowser(Pageable pageable);

    Page<PageReportItem> listPages(Pageable pageable);

    Page<PageReportItem> listUrlsForBrowser(long browserId, Pageable pageable);

    Page<BrowserReportItem> listBrowserForUrls(long urlId, Pageable pageable);

    Page<HttpCodeReportItem> listHttpCodes(Pageable pageable);

    Page<PageReportItem> listUrlsForHttpCodes(long httpCodeId, Pageable pageable);

    Page<BrowserReportItem> listBrowserForHttpCodes(long httpCodeId, Pageable pageable);

    HttpCode findHttpCodeById(long httpCodeId);

    Page<IpNumbersReportItem> listIpNumbersForHttpCodes(long httpCodeId, Pageable pageable);

    Page<PageReportItem> listUrlsForIpNumber(long ipNumberId, Pageable pageable);

    Browser findBrowserById(long browserId);

    Ip findIpById(long ipNumberId);

    Request findRequestById(long urlId);

    Page<TimelineDaysItem> listDays(Pageable pageable);

    Day findDayById(long dayId);

    Page<HttpCodeReportItem> listHttpCodesForDay(long dayId, Pageable pageable);

    Page<PageReportItem> listUrlsForDay(long dayId, Pageable pageable);

    Page<BrowserReportItem> listBrowserForDay(long dayId, Pageable pageable);
}
