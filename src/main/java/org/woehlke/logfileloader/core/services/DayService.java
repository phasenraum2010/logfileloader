package org.woehlke.logfileloader.core.services;

import org.woehlke.logfileloader.core.entities.Day;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: tw
 * Date: 15.09.13
 * Time: 22:04
 * To change this template use File | Settings | File Templates.
 */
public interface DayService {
    Day createOrFetch(Date date);

    Day find(Date date);
}
