package com.alex.mirash.graph.graph.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.alex.mirash.graph.graph.model.GraphData;
import com.alex.mirash.graph.graph.tool.IGraphComponent;

/**
 * @author Mirash
 */

public class GraphNetView extends LinearLayout implements IGraphComponent {
    public GraphNetView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public GraphNetView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void update(GraphData graphData) {

    }
}
