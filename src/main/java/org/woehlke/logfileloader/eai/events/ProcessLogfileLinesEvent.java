package org.woehlke.logfileloader.eai.events;

import org.woehlke.logfileloader.core.entities.LogfileLine;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: tw
 * Date: 02.09.13
 * Time: 15:33
 * To change this template use File | Settings | File Templates.
 */
public class ProcessLogfileLinesEvent implements Serializable {

    private LogfileLine line;

    public LogfileLine getLine() {
        return line;
    }

    public void setLine(LogfileLine line) {
        this.line = line;
    }

    @Override
    public String toString() {
        return "ProcessLogfileLinesEvent{" +
                "line=" + line +
                '}';
    }
}
