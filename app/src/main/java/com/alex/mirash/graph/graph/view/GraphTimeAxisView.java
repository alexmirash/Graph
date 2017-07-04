package com.alex.mirash.graph.graph.view;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alex.mirash.graph.R;
import com.alex.mirash.graph.graph.control.GraphController;
import com.alex.mirash.graph.graph.model.GraphData;
import com.alex.mirash.graph.graph.model.TimeModel;

/**
 * @author Mirash
 */

public class GraphTimeAxisView extends BaseGraphView {

    public GraphTimeAxisView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public GraphTimeAxisView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void init() {
        params = GraphController.params;
    }

    private TextView createLabel(GraphData graphData, TimeModel timeLabel) {
        TextView tv = new TextView(getContext());
        tv.setBackgroundColor(Color.CYAN);
        tv.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.graph_time_axis_label_text_size));
        LayoutParams lp = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.leftMargin = (int) timeToPosition(graphData, timeLabel.getTime());
        lp.gravity = Gravity.CENTER_VERTICAL;
        tv.setLayoutParams(lp);
        tv.setText(timeLabel.getLabel());
        return tv;
    }

    @Override
    public void update(GraphData graphData) {
        for (TimeModel timeLabel : graphData.getTimeLabels()) {
            TextView label = createLabel(graphData, timeLabel);
            addView(label);
        }
    }
}
