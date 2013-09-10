package org.woehlke.logfileloader.core.entities;

import javax.persistence.*;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: tw
 * Date: 10.09.13
 * Time: 14:19
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "DAY")
public class Day {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    @Temporal(TemporalType.DATE)
    private Date day = new Date(70,0,1);

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDay() {
        return day;
    }

    public void setDay(Date day) {
        this.day = day;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Day)) return false;

        Day day1 = (Day) o;

        if (!day.equals(day1.day)) return false;
        if (id != null ? !id.equals(day1.id) : day1.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + day.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Day{" +
                "id=" + id +
                ", day=" + day +
                '}';
    }
}
