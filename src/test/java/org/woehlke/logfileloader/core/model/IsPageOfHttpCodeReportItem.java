package org.woehlke.logfileloader.core.model;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: tw
 * Date: 29.09.13
 * Time: 12:08
 * To change this template use File | Settings | File Templates.
 */
public class IsPageOfHttpCodeReportItem extends TypeSafeMatcher<Page<HttpCodeReportItem>> {


    @Override
    public boolean matchesSafely(Page<HttpCodeReportItem> httpCodeReportItems) {
        List<HttpCodeReportItem> list = httpCodeReportItems.getContent();
        for(HttpCodeReportItem item:list){
            boolean check = item instanceof HttpCodeReportItem;
            if(!check){return false;};
        }
        return true;
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("is typeof Page<HttpCodeReportItem>");
    }
}
