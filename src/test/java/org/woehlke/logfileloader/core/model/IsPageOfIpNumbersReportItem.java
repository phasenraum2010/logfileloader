package org.woehlke.logfileloader.core.model;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: tw
 * Date: 29.09.13
 * Time: 13:10
 * To change this template use File | Settings | File Templates.
 */
public class IsPageOfIpNumbersReportItem extends TypeSafeMatcher<Page<IpNumbersReportItem>> {

    @Override
    public boolean matchesSafely(Page<IpNumbersReportItem> ipNumbersReportItems) {
        List<IpNumbersReportItem> list = ipNumbersReportItems.getContent();
        for(IpNumbersReportItem item:list){
            boolean check = item instanceof IpNumbersReportItem;
            if(!check){return false;};
        }
        return true;
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("is typeof Page<IpNumbersReportItem>");
    }
}
