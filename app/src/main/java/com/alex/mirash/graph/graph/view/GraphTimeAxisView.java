package com.alex.mirash.graph.graph.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alex.mirash.graph.R;
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
    }

    private TextView createLabel(TimeModel timeLabel) {
        TextView tv = (TextView) inflate(getContext(), params.getTimeAxisLabelLayoutId(), null);
        tv.setText(timeLabel.getLabel());
        return tv;
    }

    @Override
    public void update(GraphData graphData) {
        if (getChildCount() > 0) {
            removeAllViews();
        }
        int position = 0;
        for (TimeModel timeLabel : graphData.getTimeLabels()) {
            TextView label = createLabel(timeLabel);
            int width = getResources().getDimensionPixelSize(R.dimen.graph_pixels_in_week_period_week);
            LayoutParams lp = new LayoutParams(width, ViewGroup.LayoutParams.WRAP_CONTENT);
            lp.leftMargin = width * position;
            lp.gravity = Gravity.CENTER_VERTICAL;
            addView(label, lp);
            position++;
        }
    }
}
