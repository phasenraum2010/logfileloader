package org.woehlke.logfileloader.core.model;

/**
 * Created with IntelliJ IDEA.
 * User: Fert
 * Date: 06.09.13
 * Time: 12:54
 * To change this template use File | Settings | File Templates.
 */
public class HttpCodeReportItem {

    private long id;
    private String httpCode;
    private int nr;

    public String getHttpCode() {
        return httpCode;
    }

    public void setHttpCode(String httpCode) {
        this.httpCode = httpCode;
    }

    public int getNr() {
        return nr;
    }

    public void setNr(int nr) {
        this.nr = nr;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HttpCodeReportItem that = (HttpCodeReportItem) o;

        if (id != that.id) return false;
        if (nr != that.nr) return false;
        if (!httpCode.equals(that.httpCode)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + httpCode.hashCode();
        result = 31 * result + nr;
        return result;
    }

    @Override
    public String toString() {
        return "HttpCodeReportItem{" +
                "id=" + id +
                ", httpCode='" + httpCode + '\'' +
                ", nr=" + nr +
                '}';
    }
}
