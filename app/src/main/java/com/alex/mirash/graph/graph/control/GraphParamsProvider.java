package com.alex.mirash.graph.graph.control;

import android.graphics.Paint;

/**
 * @author Mirash
 */

public interface GraphParamsProvider {
    float getPaddingTop();

    float getPaddingBottom();

    float getPaddingLeft();

    float getPaddingRight();

    int getPointSize();

    int getValueAxisLabelCount();

    int getValueAxisLabelLayoutId();

    int getTimeAxisLabelLayoutId();

    Paint getGridPaint();

    Paint getPointLinePaint();

    Paint getGoalLinePaint();
}
