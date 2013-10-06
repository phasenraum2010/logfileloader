package org.woehlke.logfileloader.eai.events;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: tw
 * Date: 07.09.13
 * Time: 10:07
 * To change this template use File | Settings | File Templates.
 */
public class TriggerProcessLogfileLinesEvent implements Serializable {

    long sourceLinesToBeProcessed;
    long allSourceLines;

    public long getSourceLinesToBeProcessed() {
        return sourceLinesToBeProcessed;
    }

    public void setSourceLinesToBeProcessed(long sourceLinesToBeProcessed) {
        this.sourceLinesToBeProcessed = sourceLinesToBeProcessed;
    }

    public long getAllSourceLines() {
        return allSourceLines;
    }

    public void setAllSourceLines(long allSourceLines) {
        this.allSourceLines = allSourceLines;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TriggerProcessLogfileLinesEvent)) return false;

        TriggerProcessLogfileLinesEvent that = (TriggerProcessLogfileLinesEvent) o;

        if (allSourceLines != that.allSourceLines) return false;
        if (sourceLinesToBeProcessed != that.sourceLinesToBeProcessed) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (sourceLinesToBeProcessed ^ (sourceLinesToBeProcessed >>> 32));
        result = 31 * result + (int) (allSourceLines ^ (allSourceLines >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "TriggerProcessLogfileLinesEvent{" +
                "sourceLinesToBeProcessed=" + sourceLinesToBeProcessed +
                ", allSourceLines=" + allSourceLines +
                '}';
    }
}
