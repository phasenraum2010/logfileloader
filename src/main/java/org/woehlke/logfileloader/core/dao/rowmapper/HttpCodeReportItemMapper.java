package org.woehlke.logfileloader.core.dao.rowmapper;

import org.springframework.jdbc.core.RowMapper;
import org.woehlke.logfileloader.core.model.HttpCodeReportItem;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * User: Fert
 * Date: 06.09.13
 * Time: 13:08
 * To change this template use File | Settings | File Templates.
 */
public class HttpCodeReportItemMapper implements RowMapper<HttpCodeReportItem> {

    @Override
    public HttpCodeReportItem mapRow(ResultSet rs, int rowNum) throws SQLException {
        HttpCodeReportItem c = new HttpCodeReportItem();
        c.setHttpCode(rs.getString("code"));
        c.setNr(rs.getInt("nr"));
        c.setId(rs.getLong("id"));
        return c;
    }
}
