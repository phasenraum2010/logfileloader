package org.woehlke.logfileloader.core.model;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: tw
 * Date: 06.10.13
 * Time: 10:22
 * To change this template use File | Settings | File Templates.
 */
public class ProcessingStatus implements Serializable {

    private long sourceLinesToBeProcessed;
    private long allSourceLines;
    private long allTargetLineItems;

    public long getLinesInQueue(){
        long inProgress = allSourceLines-allTargetLineItems;
        long notYetInQueue = sourceLinesToBeProcessed;
        return inProgress-notYetInQueue;
    }

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

    public long getAllTargetLineItems() {
        return allTargetLineItems;
    }

    public void setAllTargetLineItems(long allTargetLineItems) {
        this.allTargetLineItems = allTargetLineItems;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProcessingStatus)) return false;

        ProcessingStatus that = (ProcessingStatus) o;

        if (allSourceLines != that.allSourceLines) return false;
        if (allTargetLineItems != that.allTargetLineItems) return false;
        if (sourceLinesToBeProcessed != that.sourceLinesToBeProcessed) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (sourceLinesToBeProcessed ^ (sourceLinesToBeProcessed >>> 32));
        result = 31 * result + (int) (allSourceLines ^ (allSourceLines >>> 32));
        result = 31 * result + (int) (allTargetLineItems ^ (allTargetLineItems >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "ProcessingStatus{" +
                "sourceLinesToBeProcessed=" + sourceLinesToBeProcessed +
                ", allSourceLines=" + allSourceLines +
                ", allTargetLineItems=" + allTargetLineItems +
                '}';
    }
}
