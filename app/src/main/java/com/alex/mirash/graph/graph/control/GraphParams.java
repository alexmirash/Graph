package com.alex.mirash.graph.graph.control;

import android.graphics.Paint;

/**
 * @author Mirash
 */

public class GraphParams {
    private float paddingLeft;
    private float paddingRight;
    private float paddingTop;
    private float paddingBottom;

    private float width;
    private float height;

    private float valueWidth;
    private float valueHeight;

    private int valueAxisLabelsCount;

    private int pointSize;

    private Paint pointLinePaint;
    private Paint goalLinePaint;
    private Paint gridPaint;

    private int valueAxisLabelLayoutId;
    private int timeAxisLabelLayoutId;

    private float timeIntervalInPixel;
    private int pointLayoutId;

    public GraphParams(GraphParamsProvider paramsProvider) {
        timeIntervalInPixel = paramsProvider.getTimeIntervalInPixel();

        pointSize = paramsProvider.getPointSize();

        paddingLeft = paramsProvider.getPaddingLeft();
        paddingRight = paramsProvider.getPaddingRight();
        paddingTop = paramsProvider.getPaddingTop();
        paddingBottom = paramsProvider.getPaddingBottom();

        valueAxisLabelsCount = paramsProvider.getValueAxisLabelCount();

        pointLinePaint = paramsProvider.getPointLinePaint();

        goalLinePaint = paramsProvider.getGoalLinePaint();

        gridPaint = paramsProvider.getGridPaint();

        valueAxisLabelLayoutId = paramsProvider.getValueAxisLabelLayoutId();
        timeAxisLabelLayoutId = paramsProvider.getTimeAxisLabelLayoutId();
        pointLayoutId = paramsProvider.getPointLayoutId();
    }

    public float getPaddingLeft() {
        return paddingLeft;
    }

    public float getPaddingRight() {
        return paddingRight;
    }

    public float getPaddingTop() {
        return paddingTop;
    }

    public float getPaddingBottom() {
        return paddingBottom;
    }

    public int getValueAxisLabelsCount() {
        return valueAxisLabelsCount;
    }

    public int getPointSize() {
        return pointSize;
    }

    public float getHalfPointSize() {
        return pointSize * 0.5f;
    }

    public float getWidth() {
        return width;
    }

    private void setWidth(float width) {
        this.width = width;
    }

    public void setValueWidth(float valueWidth) {
        this.valueWidth = valueWidth;
        setWidth(valueWidth + paddingLeft + paddingRight);
    }

    public float getValueWidth() {
        return valueWidth;
    }

    public float getValueHeight() {
        return valueHeight;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
        setValueHeight(height - paddingTop - paddingBottom);
    }

    private void setValueHeight(float valueHeight) {
        this.valueHeight = valueHeight;
    }

    public Paint getPointLinePaint() {
        return pointLinePaint;
    }

    public Paint getGoalLinePaint() {
        return goalLinePaint;
    }

    public Paint getGridPaint() {
        return gridPaint;
    }

    public int getValueAxisLabelLayoutId() {
        return valueAxisLabelLayoutId;
    }

    public int getTimeAxisLabelLayoutId() {
        return timeAxisLabelLayoutId;
    }

    public float getTimeIntervalInPixel() {
        return timeIntervalInPixel;
    }

    public int getPointLayoutId() {
        return pointLayoutId;
    }

    public void setPointLayoutId(int pointLayoutId) {
        this.pointLayoutId = pointLayoutId;
    }
}
