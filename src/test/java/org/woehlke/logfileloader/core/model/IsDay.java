package org.woehlke.logfileloader.core.model;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;
import org.woehlke.logfileloader.core.entities.Day;

/**
 * Created with IntelliJ IDEA.
 * User: tw
 * Date: 05.10.13
 * Time: 20:17
 * To change this template use File | Settings | File Templates.
 */
public class IsDay extends TypeSafeMatcher<Day> {

    @Override
    public boolean matchesSafely(Day timelineDaysItem) {
        return timelineDaysItem instanceof Day;
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("timelineDaysItem instanceof Day");
    }
}
