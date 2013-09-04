package org.woehlke.logfileloader.core.dao.model;

/**
 * Created with IntelliJ IDEA.
 * User: tw
 * Date: 04.09.13
 * Time: 13:39
 * To change this template use File | Settings | File Templates.
 */
public class PageReportItem {

    private String request;
    private int nr;

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
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
        if (!(o instanceof PageReportItem)) return false;

        PageReportItem that = (PageReportItem) o;

        if (nr != that.nr) return false;
        if (!request.equals(that.request)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = request.hashCode();
        result = 31 * result + nr;
        return result;
    }

    @Override
    public String toString() {
        return "PageReportItem{" +
                "request='" + request + '\'' +
                ", nr=" + nr +
                '}';
    }
}
