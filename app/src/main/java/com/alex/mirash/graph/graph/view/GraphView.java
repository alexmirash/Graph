package com.alex.mirash.graph.graph.view;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import com.alex.mirash.graph.R;
import com.alex.mirash.graph.graph.control.GraphParams;
import com.alex.mirash.graph.graph.model.GraphData;

/**
 * @author Mirash
 */

public class GraphView extends BaseGraphView {

    private GraphContentView graphContentView;
    private GraphValueAxisView valueAxisView;
    private GraphTimeAxisView timeAxisView;

    public GraphView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public GraphView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void init() {
        inflate(getContext(), R.layout.graph_view, this);
        graphContentView = findViewById(R.id.graph_content_view);
        valueAxisView = findViewById(R.id.graph_value_axis);
        timeAxisView = findViewById(R.id.graph_time_axis);
    }

    @Override
    public void update(GraphData graphData) {
        graphContentView.update(graphData);
        valueAxisView.update(graphData);
        timeAxisView.update(graphData);
    }

    @Override
    public void setParams(GraphParams params) {
        super.setParams(params);
        graphContentView.setParams(params);
        valueAxisView.setParams(params);
        timeAxisView.setParams(params);
    }
}
