package com.alex.mirash.graph.graph.view;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import com.alex.mirash.graph.graph.control.GraphParams;
import com.alex.mirash.graph.graph.model.GraphData;
import com.alex.mirash.graph.graph.tool.IGraphComponent;

/**
 * @author Mirash
 */

public abstract class BaseGraphView extends FrameLayout implements IGraphComponent {
    protected GraphParams params;

    public BaseGraphView(@NonNull Context context) {
        super(context);
        init();
    }

    public BaseGraphView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BaseGraphView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    protected abstract void init();

    public void setParams(GraphParams params) {
        this.params = params;
    }

    protected float valueToYPosition(GraphData graphData, float value) {
        return params.getPaddingTop() + params.getValueHeight() * (1 - (value - graphData.getMinValue()) / graphData.getValueInterval());
    }

    protected float timeToPosition(GraphData graphData, float time) {
        return params.getPaddingLeft() + (time - graphData.getStartTime()) / (graphData.getTimeInterval()) * params.getValueWidth();
    }
}
