package org.woehlke.logfileloader.core.entities;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: tw
 * Date: 02.09.13
 * Time: 08:53
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name="LINE")
public class LogfileLine {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, unique = true, length=1000)
    private String line;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LogfileLine that = (LogfileLine) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (!line.equals(that.line)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + line.hashCode();
        return result;
    }
}
