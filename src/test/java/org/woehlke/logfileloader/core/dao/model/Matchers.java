package org.woehlke.logfileloader.core.dao.model;

import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.springframework.data.domain.Page;
import org.woehlke.logfileloader.core.entities.Day;
import org.woehlke.logfileloader.core.entities.Ip;

/**
 * Created with IntelliJ IDEA.
 * User: tw
 * Date: 29.09.13
 * Time: 11:51
 * To change this template use File | Settings | File Templates.
 */
public class Matchers {

    @Factory
    public static <T> Matcher<Page<TimelineDaysItem>> isPageOfTimelineDaysItem() {
        return new IsPageOfTimelineDaysItem();
    }

    @Factory
    public static <T> Matcher<Page<HttpCodeReportItem>> isPageOfHttpCodeReportItem() {
        return new IsPageOfHttpCodeReportItem();
    }

    @Factory
    public static <T> Matcher<Page<PageReportItem>> isPageOfPageReportItem() {
        return new IsPageOfPageReportItem();
    }

    @Factory
    public static <T> Matcher<Page<BrowserReportItem>> isPageOfBrowserReportItem() {
        return new IsPageOfBrowserReportItem();
    }

    @Factory
    public static <T> Matcher<Page<IpNumbersReportItem>> isPageOfIpNumbersReportItem() {
        return new IsPageOfIpNumbersReportItem();
    }

    @Factory
    public static <T> Matcher<Day> isDay(){
        return new IsDay();
    }

    @Factory
    public static <T> Matcher<Ip> isIp(){
        return new IsIp();
    }
}
