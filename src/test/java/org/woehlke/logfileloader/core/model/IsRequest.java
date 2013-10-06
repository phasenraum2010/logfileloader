package org.woehlke.logfileloader.core.model;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.woehlke.logfileloader.core.entities.Request;

/**
 * Created with IntelliJ IDEA.
 * User: tw
 * Date: 06.10.13
 * Time: 14:08
 * To change this template use File | Settings | File Templates.
 */
public class IsRequest extends TypeSafeMatcher<Request> {

    @Override
    public boolean matchesSafely(Request request) {
        return request instanceof Request;
    }

    @Override
    public void describeTo(Description description) {
        description.appendText(" request instanceof Request");
    }
}
