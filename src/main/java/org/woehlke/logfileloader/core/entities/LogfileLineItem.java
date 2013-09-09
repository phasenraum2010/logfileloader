package org.woehlke.logfileloader.core.entities;

import javax.persistence.*;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: tw
 * Date: 03.09.13
 * Time: 15:23
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "LINEITEM")
public class LogfileLineItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, columnDefinition = "Text(1000)")
    private String line;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date day;

    @Column(nullable = false)
    @Temporal(TemporalType.TIME)
    private Date time;

    @ManyToOne
    private Ip ip;

    @ManyToOne
    private Request request;

    @ManyToOne
    private HttpCode httpCode;

    @ManyToOne
    private Browser browser;

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

    public Ip getIp() {
        return ip;
    }

    public void setIp(Ip ip) {
        this.ip = ip;
    }

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public HttpCode getHttpCode() {
        return httpCode;
    }

    public void setHttpCode(HttpCode httpCode) {
        this.httpCode = httpCode;
    }

    public Browser getBrowser() {
        return browser;
    }

    public void setBrowser(Browser browser) {
        this.browser = browser;
    }

    public Date getDay() {
        return day;
    }

    public void setDay(Date day) {
        this.day = day;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LogfileLineItem)) return false;

        LogfileLineItem that = (LogfileLineItem) o;

        if (!line.equals(that.line)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return line.hashCode();
    }
}
