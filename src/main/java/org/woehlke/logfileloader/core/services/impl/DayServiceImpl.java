package org.woehlke.logfileloader.core.services.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.woehlke.logfileloader.core.entities.Day;
import org.woehlke.logfileloader.core.repositories.DayRepository;
import org.woehlke.logfileloader.core.services.DayService;

import javax.inject.Inject;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: tw
 * Date: 15.09.13
 * Time: 22:07
 * To change this template use File | Settings | File Templates.
 */
@Service
public class DayServiceImpl implements DayService {

    @Inject
    private DayRepository dayRepository;

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
    public Day createOrFetch(Date date) {
        Day day = new Day();
        day.setDay(date);
        return dayRepository.save(day);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = true)
    public Day find(Date date) {
        return dayRepository.findByDay(date);
    }
}
