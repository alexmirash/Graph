package com.alex.mirash.graph.graph.view;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import com.alex.mirash.graph.R;
import com.alex.mirash.graph.graph.model.GraphData;
import com.alex.mirash.graph.graph.tool.GraphParams;

/**
 * @author Mirash
 */

public class GraphView extends BaseGraphView {

    private GraphContentView graphContentView;

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
    }

    @Override
    public void update(GraphData graphData) {
        graphContentView.update(graphData);
    }

    @Override
    public void setParams(GraphParams params) {
        this.params = params;
        graphContentView.setParams(params);
    }
}
