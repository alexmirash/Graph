package com.alex.mirash.graph.graph.control;

import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Paint;

import com.alex.mirash.graph.R;

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

    public GraphParams(Resources res) {
        pointSize = res.getDimensionPixelSize(R.dimen.graph_point_size);

        paddingLeft = res.getDimension(R.dimen.graph_padding_left);
        paddingRight = res.getDimension(R.dimen.graph_padding_right);
        paddingTop = res.getDimension(R.dimen.graph_padding_top);
        paddingBottom = res.getDimension(R.dimen.graph_padding_bottom);

        valueAxisLabelsCount = 11;

        pointLinePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        pointLinePaint.setStrokeWidth(res.getDimension(R.dimen.graph_line_width));
        pointLinePaint.setColor(res.getColor(R.color.graph_line_color));

        goalLinePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        goalLinePaint.setStrokeWidth(res.getDimension(R.dimen.graph_line_width));
        goalLinePaint.setColor(Color.CYAN);
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

    public int getValueLabelsCount() {
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

    public Paint getGoalPaint() {
        return goalLinePaint;
    }
}
