package org.woehlke.logfileloader.core.model;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: tw
 * Date: 29.09.13
 * Time: 11:53
 * To change this template use File | Settings | File Templates.
 */
public class IsPageOfTimelineDaysItem extends TypeSafeMatcher<Page<TimelineDaysItem>> {

    @Override
    public boolean matchesSafely(Page<TimelineDaysItem> timelineDaysItems) {
        List<TimelineDaysItem> list = timelineDaysItems.getContent();
        for(TimelineDaysItem item:list){
            boolean check = item instanceof TimelineDaysItem;
            if(!check){return false;};
        }
        return true;
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("is typeof Page<TimelineDaysItem>");
    }
}
