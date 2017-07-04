package com.alex.mirash.graph.graph.control;

import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Paint;

import com.alex.mirash.graph.R;

/**
 * @author Mirash
 */

public class TestParamsProvider implements GraphParamsProvider {
    private Resources res;

    public TestParamsProvider(Resources resources) {
        res = resources;
    }

    @Override
    public float getPaddingTop() {
        return res.getDimension(R.dimen.graph_padding_top);
    }

    @Override
    public float getPaddingBottom() {
        return res.getDimension(R.dimen.graph_padding_bottom);
    }

    @Override
    public float getPaddingLeft() {
        return res.getDimension(R.dimen.graph_padding_left);
    }

    @Override
    public float getPaddingRight() {
        return res.getDimension(R.dimen.graph_padding_right);
    }

    @Override
    public int getPointSize() {
        return res.getDimensionPixelSize(R.dimen.graph_point_size);
    }

    @Override
    public int getValueAxisLabelCount() {
        return 11;
    }

    @Override
    public int getValueAxisLabelLayoutId() {
        return R.layout.graph_value_axis_label_layout;
    }

    @Override
    public int getTimeAxisLabelLayoutId() {
        return R.layout.graph_time_axis_label_layout;
    }

    @Override
    public Paint getGridPaint() {
        Paint gridPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        gridPaint.setStrokeWidth(res.getDimension(R.dimen.graph_line_width));
        gridPaint.setColor(Color.LTGRAY);
        return gridPaint;
    }

    @Override
    public Paint getPointLinePaint() {
        Paint pointLinePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        pointLinePaint.setStrokeWidth(res.getDimension(R.dimen.graph_line_width));
        pointLinePaint.setColor(res.getColor(R.color.graph_line_color));
        return pointLinePaint;
    }

    @Override
    public Paint getGoalLinePaint() {
        Paint goalLinePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        goalLinePaint.setStrokeWidth(res.getDimension(R.dimen.graph_line_width));
        goalLinePaint.setColor(Color.GREEN);
        return goalLinePaint;
    }
}
