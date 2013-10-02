package org.woehlke.logfileloader.core.dao.model;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: tw
 * Date: 29.09.13
 * Time: 12:12
 * To change this template use File | Settings | File Templates.
 */
public class IsPageOfPageReportItem extends TypeSafeMatcher<Page<PageReportItem>> {

    @Override
    public boolean matchesSafely(Page<PageReportItem> pageReportItems) {
        List<PageReportItem> list = pageReportItems.getContent();
        for(PageReportItem item:list){
            boolean check = item instanceof PageReportItem;
            if(!check){return false;};
        }
        return true;
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("is typeof Page<PageReportItem>");
    }
}
