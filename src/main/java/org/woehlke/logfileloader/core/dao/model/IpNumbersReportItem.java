package org.woehlke.logfileloader.core.dao.model;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: tw
 * Date: 04.09.13
 * Time: 12:34
 * To change this template use File | Settings | File Templates.
 */
public class IpNumbersReportItem implements Serializable {

    private String ip;
    private int nr;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
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
        if (!(o instanceof IpNumbersReportItem)) return false;

        IpNumbersReportItem that = (IpNumbersReportItem) o;

        if (nr != that.nr) return false;
        if (!ip.equals(that.ip)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = ip.hashCode();
        result = 31 * result + nr;
        return result;
    }

    @Override
    public String toString() {
        return "IpNumbersReportItem{" +
                "ip='" + ip + '\'' +
                ", nr=" + nr +
                '}';
    }
}
