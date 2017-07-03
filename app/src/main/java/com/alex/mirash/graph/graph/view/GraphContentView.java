package com.alex.mirash.graph.graph.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.FrameLayout;

import com.alex.mirash.graph.graph.model.GraphData;
import com.alex.mirash.graph.graph.model.ValueModel;
import com.alex.mirash.graph.graph.tool.GraphPoint;

import java.util.List;

/**
 * @author Mirash
 */

public class GraphContentView extends BaseGraphView {
    private GraphData graphData;

    public GraphContentView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public GraphContentView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    protected void init() {
        setWillNotDraw(false);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int height = getMeasuredHeight();
        params.setHeight(height);
        if (graphData != null) {
            Log.d("LOL", "onMeasure " + params.getWidth() + " " + height);
            setMeasuredDimension((int) params.getWidth(), height);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.d("LOL", "onDraw " + canvas.getWidth() + " " + canvas.getHeight());
        canvas.drawColor(Color.WHITE);
        if (graphData != null) {
            List<GraphPoint> pointList = graphData.getPoints();
            for (int i = 0; i < pointList.size() - 1; i++) {
                GraphPoint point = pointList.get(i);
                GraphPoint pointNext = pointList.get(i + 1);
                canvas.drawLine(point.getX(), point.getY(), pointNext.getX(), pointNext.getY(), params.getPointLinePaint());
            }
        }
    }

    private void clear() {
        if (graphData != null) {
            removeAllViews();
        }
    }

    @Override
    public void update(final GraphData graphData) {
        clear();
        GraphContentView.this.graphData = graphData;
        for (ValueModel valueModel : graphData.getValues()) {
            addPoint(valueModel);
        }
    }

    private void addPoint(ValueModel valueModel) {
        float x = timeToPosition(valueModel.getTime());
        float y = valueToYPosition(valueModel.getValue());

        Log.d("LOL", "x, y " + x + "; " + y);

        FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(params.getPointSize(), params.getPointSize());
        lp.leftMargin = (int) (x - params.getHalfPointSize());
        lp.topMargin = (int) (y - params.getHalfPointSize());
        GraphPointView pointView = new GraphPointView(getContext());
        addView(pointView, lp);

        graphData.addPoint(new GraphPoint(x, y));
    }

    private float valueToYPosition(float value) {
        return params.getPaddingTop() + params.getValueHeight() * (1 - (value - graphData.getMinValue()) / graphData.getValueInterval());
    }

    private float timeToPosition(float time) {
        return params.getPaddingLeft() + (time - graphData.getStartTime()) / (graphData.getTimeInterval()) * params.getValueWidth();
    }
}
