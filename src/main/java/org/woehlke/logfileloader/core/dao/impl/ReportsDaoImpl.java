package org.woehlke.logfileloader.core.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.woehlke.logfileloader.core.dao.ReportsDao;
import org.woehlke.logfileloader.core.dao.model.BrowserReportItem;
import org.woehlke.logfileloader.core.dao.model.IpNumbersReportItem;
import org.woehlke.logfileloader.core.dao.model.PageReportItem;
import org.woehlke.logfileloader.core.dao.rowmapper.BrowserReportItemMapper;
import org.woehlke.logfileloader.core.dao.rowmapper.IpNumbersReportItemMapper;
import org.woehlke.logfileloader.core.dao.rowmapper.PageReportItemMapper;

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
    public List<IpNumbersReportItem> listIpNumbers() {
        String sql = "select ip,count(ip) as nr from IP,LINEITEM where LINEITEM.ip_id=IP.id group by ip order by nr DESC";
        return jdbcTemplate.query(sql, new IpNumbersReportItemMapper());
    }

    @Override
    public List<BrowserReportItem> listBrowser() {
        String sql = "select browser,count(browser) as nr from BROWSER,LINEITEM where LINEITEM.browser_id=BROWSER.id group by browser order by nr DESC";
        return jdbcTemplate.query(sql, new BrowserReportItemMapper());
    }

    @Override
    public List<PageReportItem> listPages() {
        String sql = "select request,count(request) as nr from REQUEST,LINEITEM where LINEITEM.request_id=REQUEST.id group by request order by nr DESC";
        return jdbcTemplate.query(sql, new PageReportItemMapper());
    }
}
