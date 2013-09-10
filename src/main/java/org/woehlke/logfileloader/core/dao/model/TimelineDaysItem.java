package org.woehlke.logfileloader.core.dao.model;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Fert
 * Date: 09.09.13
 * Time: 14:02
 * To change this template use File | Settings | File Templates.
 */
public class TimelineDaysItem {

    private long id;
    private Date day;
    private int nr;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDay() {
        return day;
    }

    public void setDay(Date day) {
        this.day = day;
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

        TimelineDaysItem that = (TimelineDaysItem) o;

        if (nr != that.nr) return false;
        if (!day.equals(that.day)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = day.hashCode();
        result = 31 * result + nr;
        return result;
    }

    @Override
    public String toString() {
        return "TimelineDaysItem{" +
                "id=" + id +
                ", day=" + day +
                ", nr=" + nr +
                '}';
    }
}
