package org.woehlke.logfileloader.core.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.woehlke.logfileloader.core.dao.LogfileImportDao;

import javax.sql.DataSource;

/**
 * Created with IntelliJ IDEA.
 * User: tw
 * Date: 06.10.13
 * Time: 09:23
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class LogfileImportDaoImpl implements LogfileImportDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void setAllLogfileLinesToUnProcessed() {
        String sql = "update LINE set processed=0";
        jdbcTemplate.execute(sql);
    }
}
