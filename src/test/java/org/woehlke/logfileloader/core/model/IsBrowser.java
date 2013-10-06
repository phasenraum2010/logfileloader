package org.woehlke.logfileloader.core.model;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;
import org.woehlke.logfileloader.core.entities.Browser;

/**
 * Created with IntelliJ IDEA.
 * User: tw
 * Date: 06.10.13
 * Time: 14:32
 * To change this template use File | Settings | File Templates.
 */
public class IsBrowser extends TypeSafeMatcher<Browser> {

    @Override
    public boolean matchesSafely(Browser browser) {
        return browser instanceof Browser;
    }

    @Override
    public void describeTo(Description description) {
        description.appendText(" browser instanceof Browser");
    }
}
