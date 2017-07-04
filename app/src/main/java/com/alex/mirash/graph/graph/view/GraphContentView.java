package com.alex.mirash.graph.graph.view;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import com.alex.mirash.graph.graph.model.GraphData;
import com.alex.mirash.graph.graph.model.TimeModel;
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
            setMeasuredDimension((int) params.getWidth(), height);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (graphData != null) {
            //draw vertical grid
            for (TimeModel timeModel : graphData.getTimeLabels()) {
                float x = timeToPosition(graphData, timeModel.getTimeFinish());
                canvas.drawLine(x, 0, x, canvas.getHeight(), params.getGridPaint());
            }
            //draw horizontal grid
            float step = (graphData.getValueInterval()) / (params.getValueAxisLabelsCount() - 1);
            float value = graphData.getMinValue();
            for (int i = 0; i < params.getValueAxisLabelsCount(); i++) {
                float y = valueToYPosition(graphData, value);
                value += step;
                canvas.drawLine(0, y, canvas.getWidth(), y, params.getGridPaint());
            }
            //draw goal
            Float goalValue = graphData.getGoalValue();
            if (goalValue != null) {
                float goalY = valueToYPosition(graphData, goalValue);
                canvas.drawLine(0, goalY, canvas.getWidth(), goalY, params.getGoalLinePaint());
            }
            //draw points and lines between them
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

    private View createPointView() {
        return inflate(getContext(), params.getPointLayoutId(), null);
    }

    private void addPoint(ValueModel valueModel) {
        float x = timeToPosition(graphData, valueModel.getTime());
        float y = valueToYPosition(graphData, valueModel.getValue());

        FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(params.getPointSize(), params.getPointSize());
        lp.leftMargin = (int) (x - params.getHalfPointSize());
        lp.topMargin = (int) (y - params.getHalfPointSize());
        View pointView = createPointView();
        addView(pointView, lp);

        graphData.addPoint(new GraphPoint(x, y));
    }
}
