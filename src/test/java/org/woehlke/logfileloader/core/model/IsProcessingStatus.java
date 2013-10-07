package org.woehlke.logfileloader.core.model;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

/**
 * Created with IntelliJ IDEA.
 * User: tw
 * Date: 06.10.13
 * Time: 14:47
 * To change this template use File | Settings | File Templates.
 */
public class IsProcessingStatus extends TypeSafeMatcher<ProcessingStatus> {

    @Override
    public boolean matchesSafely(ProcessingStatus processingStatus) {
        return processingStatus instanceof ProcessingStatus;
    }

    @Override
    public void describeTo(Description description) {
        description.appendText(" processingStatus instanceof ProcessingStatus");
    }
}
