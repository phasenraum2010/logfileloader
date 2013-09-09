package org.woehlke.logfileloader.core.dao.rowmapper;

import org.springframework.jdbc.core.RowMapper;
import org.woehlke.logfileloader.core.dao.model.TimelineDaysItem;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * User: Fert
 * Date: 09.09.13
 * Time: 14:06
 * To change this template use File | Settings | File Templates.
 */
public class TimelineDaysItemMapper implements RowMapper<TimelineDaysItem> {

    @Override
    public TimelineDaysItem mapRow(ResultSet rs, int rowNum) throws SQLException {
        TimelineDaysItem e = new TimelineDaysItem();
        e.setDay(rs.getDate("day"));
        e.setNr(rs.getInt("nr"));
        return e;
    }
}
