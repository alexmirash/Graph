package com.alex.mirash.graph.graph.model;

/**
 * @author Mirash
 */

public class TimeModel {
    private long time;
    private String label;

    public TimeModel(long time, String label) {
        this.time = time;
        this.label = label;
    }

    public long getTime() {
        return time;
    }

    public String getLabel() {
        return label;
    }
}
