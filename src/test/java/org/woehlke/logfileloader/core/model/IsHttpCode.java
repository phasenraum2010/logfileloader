package org.woehlke.logfileloader.core.model;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;
import org.woehlke.logfileloader.core.entities.HttpCode;

/**
 * Created with IntelliJ IDEA.
 * User: tw
 * Date: 06.10.13
 * Time: 14:20
 * To change this template use File | Settings | File Templates.
 */
public class IsHttpCode extends TypeSafeMatcher<HttpCode> {

    @Override
    public boolean matchesSafely(HttpCode httpCode) {
        return httpCode instanceof HttpCode;
    }

    @Override
    public void describeTo(Description description) {
        description.appendText(" httpCode instanceof HttpCode");
    }
}
