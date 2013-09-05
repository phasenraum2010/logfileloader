package org.woehlke.logfileloader.core.dao.rowmapper;

import org.springframework.jdbc.core.RowMapper;
import org.woehlke.logfileloader.core.dao.model.IpNumbersReportItem;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * User: tw
 * Date: 04.09.13
 * Time: 12:53
 * To change this template use File | Settings | File Templates.
 */
public class IpNumbersReportItemMapper implements RowMapper<IpNumbersReportItem> {

    @Override
    public IpNumbersReportItem mapRow(ResultSet rs, int rowNum) throws SQLException {
        IpNumbersReportItem ip = new IpNumbersReportItem();
        ip.setIp(rs.getString("ip"));
        ip.setNr(rs.getInt("nr"));
        ip.setId(rs.getLong("id"));
        return ip;
    }
}
