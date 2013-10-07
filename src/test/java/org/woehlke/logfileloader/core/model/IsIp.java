package org.woehlke.logfileloader.core.model;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;
import org.woehlke.logfileloader.core.entities.Ip;

/**
 * Created with IntelliJ IDEA.
 * User: tw
 * Date: 05.10.13
 * Time: 20:59
 * To change this template use File | Settings | File Templates.
 */
public class IsIp extends TypeSafeMatcher<Ip> {

    @Override
    public boolean matchesSafely(Ip ip) {
        return ip instanceof Ip;
    }

    @Override
    public void describeTo(Description description) {
        description.appendText(" ip instanceof Ip");
    }
}
