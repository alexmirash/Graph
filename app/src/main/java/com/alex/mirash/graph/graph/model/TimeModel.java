package com.alex.mirash.graph.graph.model;

/**
 * @author Mirash
 */

public class TimeModel {
    private long timeStart;
    private long timeFinish;
    private String label;

    public TimeModel(long timeStart, long timeFinish, String label) {
        this.timeStart = timeStart;
        this.timeFinish = timeFinish;
        this.label = label;
    }

    public long getTimeStart() {
        return timeStart;
    }

    public long getTimeFinish() {
        return timeFinish;
    }

    public long getTime() {
        return timeStart + (timeFinish - timeStart) / 2;
    }

    public String getLabel() {
        return label;
    }
}
