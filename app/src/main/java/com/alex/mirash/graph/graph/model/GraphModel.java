package com.alex.mirash.graph.graph.model;

import java.util.ArrayList;

/**
 * @author Mirash
 */

public class GraphModel extends GraphData {

    public GraphModel() {
        values = new ArrayList<>();
        points = new ArrayList<>();
        timeLabels = new ArrayList<>();
    }

    public GraphModel(int capacity) {
        values = new ArrayList<>(capacity);
        points = new ArrayList<>(capacity);
        timeLabels = new ArrayList<>(capacity);
    }

    public void setMinValue(float minValue) {
        this.minValue = minValue;
    }

    public void setMaxValue(float maxValue) {
        this.maxValue = maxValue;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public void setFinishTime(long finishTime) {
        this.finishTime = finishTime;
    }

    public void addValue(ValueModel valueModel) {
        values.add(valueModel);
    }


    public void addTimeLabel(TimeModel label) {
        timeLabels.add(label);
    }

    public void setTimeInterval(long startTime, long finishTime) {
        setStartTime(startTime);
        setFinishTime(finishTime);
    }

    public void setValuesInterval(float minValue, float maxValue) {
        setMinValue(minValue);
        setMaxValue(maxValue);
    }
}
