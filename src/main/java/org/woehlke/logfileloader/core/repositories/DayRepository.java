package org.woehlke.logfileloader.core.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.woehlke.logfileloader.core.entities.Day;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: tw
 * Date: 10.09.13
 * Time: 14:23
 * To change this template use File | Settings | File Templates.
 */
public interface DayRepository extends JpaRepository<Day,Long> {
    Day findByDay(Date day);
}
