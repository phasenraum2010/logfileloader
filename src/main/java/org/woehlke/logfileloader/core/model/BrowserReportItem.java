package org.woehlke.logfileloader.core.model;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: tw
 * Date: 04.09.13
 * Time: 13:38
 * To change this template use File | Settings | File Templates.
 */
public class BrowserReportItem implements Serializable {

    private long id;
    private String browser;
    private int nr;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBrowser() {
        return browser;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }

    public int getNr() {
        return nr;
    }

    public void setNr(int nr) {
        this.nr = nr;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BrowserReportItem that = (BrowserReportItem) o;

        if (id != that.id) return false;
        if (nr != that.nr) return false;
        if (!browser.equals(that.browser)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + browser.hashCode();
        result = 31 * result + nr;
        return result;
    }

    @Override
    public String toString() {
        return "BrowserReportItem{" +
                "id=" + id +
                ", browser='" + browser + '\'' +
                ", nr=" + nr +
                '}';
    }
}
