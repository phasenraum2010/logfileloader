package org.woehlke.logfileloader.core.model;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: tw
 * Date: 29.09.13
 * Time: 12:15
 * To change this template use File | Settings | File Templates.
 */
public class IsPageOfBrowserReportItem extends TypeSafeMatcher<Page<BrowserReportItem>> {

    @Override
    public boolean matchesSafely(Page<BrowserReportItem> browserReportItems) {
        List<BrowserReportItem> list = browserReportItems.getContent();
        for(BrowserReportItem item:list){
            boolean check = item instanceof BrowserReportItem;
            if(!check){return false;};
        }
        return true;
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("is typeof Page<HttpCodeReportItem>");
    }
}
