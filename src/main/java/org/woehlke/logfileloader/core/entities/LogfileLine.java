package org.woehlke.logfileloader.core.entities;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: tw
 * Date: 02.09.13
 * Time: 08:53
 * To change this template use File | Settings | File Templates.
 */
@Entity()
@Table(name = "LINE")
public class LogfileLine {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, columnDefinition = "Text(1000)")
    private String line;

    @Column
    private boolean processed;

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

    public boolean isProcessed() {
        return processed;
    }

    public void setProcessed(boolean processed) {
        this.processed = processed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LogfileLine)) return false;

        LogfileLine that = (LogfileLine) o;

        if (!line.equals(that.line)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return line.hashCode();
    }

    @Override
    public String toString() {
        return "LogfileLine{" +
                "id=" + id +
                ", line='" + line + '\'' +
                ", processed=" + processed +
                '}';
    }
}
