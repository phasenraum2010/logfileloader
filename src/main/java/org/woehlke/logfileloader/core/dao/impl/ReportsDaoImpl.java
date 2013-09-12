package org.woehlke.logfileloader.core.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.woehlke.logfileloader.core.dao.ReportsDao;
import org.woehlke.logfileloader.core.dao.model.*;
import org.woehlke.logfileloader.core.dao.rowmapper.*;

import javax.sql.DataSource;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: tw
 * Date: 04.09.13
 * Time: 12:21
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class ReportsDaoImpl implements ReportsDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Page<IpNumbersReportItem> listIpNumbers(Pageable pageable) {
        String sql1 = "select IP.id as id,ip,count(ip) as nr from IP,LINEITEM where LINEITEM.ip_id=IP.id group by ip,id order by nr DESC";
        String sql2 = "select count(nr) from ("+sql1+") as COUNT";
        long total = jdbcTemplate.queryForLong(sql2);
        List<IpNumbersReportItem> list = jdbcTemplate.query(sql1, new IpNumbersReportItemMapper());
        int toIndex=(pageable.getOffset()+pageable.getPageSize())>list.size()?list.size():(pageable.getOffset()+pageable.getPageSize());
        Page<IpNumbersReportItem> page =new PageImpl<IpNumbersReportItem>(list.subList(pageable.getOffset(),toIndex),pageable,total);
        return page;
    }

    @Override
    public Page<BrowserReportItem> listBrowser(Pageable pageable) {
        String sql1 = "select BROWSER.id as id,browser,count(browser) as nr from BROWSER,LINEITEM where LINEITEM.browser_id=BROWSER.id group by browser,id order by nr DESC";
        String sql2 = "select count(nr) from ("+sql1+") as COUNT";
        long total = jdbcTemplate.queryForLong(sql2);
        List<BrowserReportItem> list = jdbcTemplate.query(sql1, new BrowserReportItemMapper());
        int toIndex=(pageable.getOffset()+pageable.getPageSize())>list.size()?list.size():(pageable.getOffset()+pageable.getPageSize());
        Page<BrowserReportItem> page = new PageImpl<BrowserReportItem>(list.subList(pageable.getOffset(),toIndex),pageable,total);
        return page;
    }

    @Override
    public Page<PageReportItem> listPages(Pageable pageable) {
        String sql1 = "select REQUEST.id as id,request,count(request) as nr from REQUEST,LINEITEM where LINEITEM.request_id=REQUEST.id group by request,id order by nr DESC";
        String sql2 = "select count(nr) from ("+sql1+") as COUNT";
        long total = jdbcTemplate.queryForLong(sql2);
        List<PageReportItem> list = jdbcTemplate.query(sql1, new PageReportItemMapper());
        int toIndex=(pageable.getOffset()+pageable.getPageSize())>list.size()?list.size():(pageable.getOffset()+pageable.getPageSize());
        Page<PageReportItem> page = new PageImpl<PageReportItem>(list.subList(pageable.getOffset(),toIndex),pageable,total);
        return page;
    }

    @Override
    public Page<PageReportItem> listUrlsForBrowser(long browserId,Pageable pageable) {
        String sql1 = "select REQUEST.id as id,request,count(request) as nr from REQUEST,LINEITEM where LINEITEM.request_id=REQUEST.id and LINEITEM.browser_id=? group by request,id order by nr DESC";
        String sql2 = "select count(nr) from ("+sql1+") as COUNT";
        long total = jdbcTemplate.queryForLong(sql2,browserId);
        List<PageReportItem> list = jdbcTemplate.query(sql1, new PageReportItemMapper(), browserId);
        int toIndex=(pageable.getOffset()+pageable.getPageSize())>list.size()?list.size():(pageable.getOffset()+pageable.getPageSize());
        Page<PageReportItem> page = new PageImpl<PageReportItem>(list.subList(pageable.getOffset(),toIndex),pageable,total);
        return page;
    }

    @Override
    public Page<BrowserReportItem> listBrowserForUrls(long urlId, Pageable pageable) {
        String sql1 = "select BROWSER.id as id,browser,count(browser) as nr from BROWSER,LINEITEM where LINEITEM.browser_id=BROWSER.id and LINEITEM.request_id=? group by browser,id order by nr DESC";
        String sql2 = "select count(nr) from ("+sql1+") as COUNT";
        long total = jdbcTemplate.queryForLong(sql2,urlId);
        List<BrowserReportItem> list = jdbcTemplate.query(sql1, new BrowserReportItemMapper(),urlId);
        int toIndex=(pageable.getOffset()+pageable.getPageSize())>list.size()?list.size():(pageable.getOffset()+pageable.getPageSize());
        Page<BrowserReportItem> page = new PageImpl<BrowserReportItem>(list.subList(pageable.getOffset(),toIndex),pageable,total);
        return page;
    }

    @Override
    public Page<HttpCodeReportItem> listHttpCodes(Pageable pageable) {
        String sql1 = "select HTTPCODE.id as id,code,count(code) as nr from HTTPCODE,LINEITEM where LINEITEM.httpcode_id=HTTPCODE.id group by code,id order by nr DESC";
        String sql2 = "select count(nr) from ("+sql1+") as COUNT";
        long total = jdbcTemplate.queryForLong(sql2);
        List<HttpCodeReportItem> list = jdbcTemplate.query(sql1, new HttpCodeReportItemMapper());
        int toIndex=(pageable.getOffset()+pageable.getPageSize())>list.size()?list.size():(pageable.getOffset()+pageable.getPageSize());
        Page<HttpCodeReportItem> page = new PageImpl<HttpCodeReportItem>(list.subList(pageable.getOffset(),toIndex),pageable,total);
        return page;
    }

    @Override
    public Page<PageReportItem> listUrlsForHttpCodes(long httpCodeId, Pageable pageable) {
        String sql1 = "select REQUEST.id as id,request,count(request) as nr from REQUEST,LINEITEM where LINEITEM.request_id=REQUEST.id and LINEITEM.httpcode_id=? group by request,id order by nr DESC";
        String sql2 = "select count(nr) from ("+sql1+") as COUNT";
        long total = jdbcTemplate.queryForLong(sql2,httpCodeId);
        List<PageReportItem> list = jdbcTemplate.query(sql1, new PageReportItemMapper(), httpCodeId);
        int toIndex=(pageable.getOffset()+pageable.getPageSize())>list.size()?list.size():(pageable.getOffset()+pageable.getPageSize());
        Page<PageReportItem> page = new PageImpl<PageReportItem>(list.subList(pageable.getOffset(),toIndex),pageable,total);
        return page;
    }

    @Override
    public Page<BrowserReportItem> listBrowserForHttpCodes(long httpCodeId, Pageable pageable) {
        String sql1 = "select BROWSER.id as id,browser,count(browser) as nr from BROWSER,LINEITEM where LINEITEM.browser_id=BROWSER.id and LINEITEM.httpcode_id=? group by browser,id order by nr DESC";
        String sql2 = "select count(nr) from ("+sql1+") as COUNT";
        long total = jdbcTemplate.queryForLong(sql2,httpCodeId);
        List<BrowserReportItem> list = jdbcTemplate.query(sql1, new BrowserReportItemMapper(),httpCodeId);
        int toIndex=(pageable.getOffset()+pageable.getPageSize())>list.size()?list.size():(pageable.getOffset()+pageable.getPageSize());
        Page<BrowserReportItem> page = new PageImpl<BrowserReportItem>(list.subList(pageable.getOffset(),toIndex),pageable,total);
        return page;
    }

    @Override
    public Page<IpNumbersReportItem> listIpNumbersForHttpCodes(long httpCodeId, Pageable pageable) {
        String sql1 = "select IP.id as id,ip,count(ip) as nr from IP,LINEITEM where LINEITEM.ip_id=IP.id and LINEITEM.httpcode_id=? group by ip,id order by nr DESC";
        String sql2 = "select count(nr) from ("+sql1+") as COUNT";
        long total = jdbcTemplate.queryForLong(sql2,httpCodeId);
        List<IpNumbersReportItem> list = jdbcTemplate.query(sql1, new IpNumbersReportItemMapper(),httpCodeId);
        int toIndex=(pageable.getOffset()+pageable.getPageSize())>list.size()?list.size():(pageable.getOffset()+pageable.getPageSize());
        Page<IpNumbersReportItem> page = new PageImpl<IpNumbersReportItem>(list.subList(pageable.getOffset(),toIndex),pageable,total);
        return page;
    }

    @Override
    public Page<PageReportItem> listUrlsForIpNumber(long ipNumberId, Pageable pageable) {
        String sql1 = "select REQUEST.id as id,request,count(request) as nr from REQUEST,LINEITEM where LINEITEM.request_id=REQUEST.id and LINEITEM.ip_id=? group by request,id order by nr DESC";
        String sql2 = "select count(nr) from ("+sql1+") as COUNT";
        long total = jdbcTemplate.queryForLong(sql2,ipNumberId);
        List<PageReportItem> list = jdbcTemplate.query(sql1, new PageReportItemMapper(), ipNumberId);
        int toIndex=(pageable.getOffset()+pageable.getPageSize())>list.size()?list.size():(pageable.getOffset()+pageable.getPageSize());
        Page<PageReportItem> page = new PageImpl<PageReportItem>(list.subList(pageable.getOffset(),toIndex),pageable,total);
        return page;
    }

    @Override
    public Page<TimelineDaysItem> listDays(Pageable pageable) {
        String sql1 = "select DAY.id as id,day,count(day) as nr from DAY,LINEITEM where LINEITEM.day_id=DAY.id group by day,id order by day DESC";
        String sql2 = "select count(nr) from ("+sql1+") as COUNT";
        long total = jdbcTemplate.queryForLong(sql2);
        List<TimelineDaysItem> list = jdbcTemplate.query(sql1, new TimelineDaysItemMapper());
        int toIndex=(pageable.getOffset()+pageable.getPageSize())>list.size()?list.size():(pageable.getOffset()+pageable.getPageSize());
        Page<TimelineDaysItem> page = new PageImpl<TimelineDaysItem>(list.subList(pageable.getOffset(),toIndex),pageable,total);
        return page;
    }

    @Override
    public Page<HttpCodeReportItem> listHttpCodesForDay(long dayId, Pageable pageable) {
        String sql1 = "select HTTPCODE.id as id,code,count(code) as nr from HTTPCODE,LINEITEM where LINEITEM.httpcode_id=HTTPCODE.id and LINEITEM.day_id=? group by code,id order by nr DESC";
        String sql2 = "select count(nr) from ("+sql1+") as COUNT";
        long total = jdbcTemplate.queryForLong(sql2,dayId);
        List<HttpCodeReportItem> list = jdbcTemplate.query(sql1, new HttpCodeReportItemMapper(),dayId);
        int toIndex=(pageable.getOffset()+pageable.getPageSize())>list.size()?list.size():(pageable.getOffset()+pageable.getPageSize());
        Page<HttpCodeReportItem> page = new PageImpl<HttpCodeReportItem>(list.subList(pageable.getOffset(),toIndex),pageable,total);
        return page;
    }

    @Override
    public Page<PageReportItem> listUrlsForDay(long dayId, Pageable pageable) {
        String sql1 = "select REQUEST.id as id,request,count(request) as nr from REQUEST,LINEITEM where LINEITEM.request_id=REQUEST.id and LINEITEM.day_id=? group by request,id order by nr DESC";
        String sql2 = "select count(nr) from ("+sql1+") as COUNT";
        long total = jdbcTemplate.queryForLong(sql2,dayId);
        List<PageReportItem> list = jdbcTemplate.query(sql1, new PageReportItemMapper(), dayId);
        int toIndex=(pageable.getOffset()+pageable.getPageSize())>list.size()?list.size():(pageable.getOffset()+pageable.getPageSize());
        Page<PageReportItem> page = new PageImpl<PageReportItem>(list.subList(pageable.getOffset(),toIndex),pageable,total);
        return page;
    }

    @Override
    public Page<BrowserReportItem> listBrowserForDay(long dayId, Pageable pageable) {
        String sql1 = "select BROWSER.id as id,browser,count(browser) as nr from BROWSER,LINEITEM where LINEITEM.browser_id=BROWSER.id and LINEITEM.day_id=? group by browser,id order by nr DESC";
        String sql2 = "select count(nr) from ("+sql1+") as COUNT";
        long total = jdbcTemplate.queryForLong(sql2,dayId);
        List<BrowserReportItem> list = jdbcTemplate.query(sql1, new BrowserReportItemMapper(),dayId);
        int toIndex=(pageable.getOffset()+pageable.getPageSize())>list.size()?list.size():(pageable.getOffset()+pageable.getPageSize());
        Page<BrowserReportItem> page = new PageImpl<BrowserReportItem>(list.subList(pageable.getOffset(),toIndex),pageable,total);
        return page;
    }
}
