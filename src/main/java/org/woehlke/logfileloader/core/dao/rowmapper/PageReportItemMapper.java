package org.woehlke.logfileloader.core.dao.rowmapper;

import org.springframework.jdbc.core.RowMapper;
import org.woehlke.logfileloader.core.dao.model.PageReportItem;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * User: tw
 * Date: 04.09.13
 * Time: 13:42
 * To change this template use File | Settings | File Templates.
 */
public class PageReportItemMapper implements RowMapper<PageReportItem> {

    @Override
    public PageReportItem mapRow(ResultSet rs, int rowNum) throws SQLException {
        PageReportItem p = new PageReportItem();
        p.setRequest(rs.getString("request"));
        p.setNr(rs.getInt("nr"));
        p.setId(rs.getLong("id"));
        return p;
    }
}
