package com.alex.mirash.graph.graph.model;

import com.alex.mirash.graph.graph.tool.GraphPoint;

import java.util.List;

/**
 * @author Mirash
 */

public class GraphData {
    protected float minValue;
    protected float maxValue;

    protected long startTime;
    protected long finishTime;

    protected List<ValueModel> values;
    protected List<GraphPoint> points;

    public float getMinValue() {
        return minValue;
    }

    public float getMaxValue() {
        return maxValue;
    }

    public long getStartTime() {
        return startTime;
    }

    public long getFinishTime() {
        return finishTime;
    }

    public List<ValueModel> getValues() {
        return values;
    }

    public long getTimeInterval() {
        return finishTime - startTime;
    }

    public float getValueInterval() {
        return maxValue - minValue;
    }

    public int size() {
        return values.size();
    }

    public List<GraphPoint> getPoints() {
        return points;
    }

    public void addPoint(GraphPoint point) {
        points.add(point);
    }
}
