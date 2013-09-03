package org.woehlke.logfileloader.eai.events;

import org.woehlke.logfileloader.core.entities.LogfileLine;

import java.io.Serializable;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: tw
 * Date: 02.09.13
 * Time: 15:33
 * To change this template use File | Settings | File Templates.
 */
public class ProcessLogfileLinesEvent implements Serializable {

    private LogfileLine line;

    private String ip;

    private Date datetime;

    private String requestLine;

    private String httpCode;

    private String browser;

    public LogfileLine getLine() {
        return line;
    }

    public void setLine(LogfileLine line) {
        this.line = line;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

    public String getRequestLine() {
        return requestLine;
    }

    public void setRequestLine(String requestLine) {
        this.requestLine = requestLine;
    }

    public String getHttpCode() {
        return httpCode;
    }

    public void setHttpCode(String httpCode) {
        this.httpCode = httpCode;
    }

    public String getBrowser() {
        return browser;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }

    @Override
    public String toString() {
        return "ProcessLogfileLinesEvent{" +
                "line=" + line +
                ", ip='" + ip + '\'' +
                ", datetime=" + datetime +
                ", requestLine='" + requestLine + '\'' +
                ", httpCode='" + httpCode + '\'' +
                ", browser='" + browser + '\'' +
                '}';
    }
}
