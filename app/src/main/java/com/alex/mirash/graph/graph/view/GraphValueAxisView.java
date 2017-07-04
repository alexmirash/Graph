package com.alex.mirash.graph.graph.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alex.mirash.graph.graph.control.GraphParams;
import com.alex.mirash.graph.graph.model.GraphData;

import java.util.ArrayList;
import java.util.List;


/**
 * @author Mirash
 */
public class GraphValueAxisView extends BaseGraphView {
    private List<TextView> labels;

    public GraphValueAxisView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public GraphValueAxisView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void init() {
    }


    private void createLabels() {
        int labelsCount = params.getValueAxisLabelsCount();
        labels = new ArrayList<>();
        for (int i = 0; i < labelsCount; i++) {
            TextView label = createLabel();
            addView(label);
            labels.add(label);
        }
    }

    private TextView createLabel() {
        TextView tv = (TextView) inflate(getContext(), params.getValueAxisLabelLayoutId(), null);
        LayoutParams lp = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.gravity = Gravity.CENTER_HORIZONTAL;
        tv.setLayoutParams(lp);
        return tv;
    }


    @Override
    public void setParams(GraphParams params) {
        super.setParams(params);
        createLabels();
    }

    @Override
    public void update(GraphData graphData) {
        float step = (graphData.getValueInterval()) / (params.getValueAxisLabelsCount() - 1);
        float value = graphData.getMaxValue();
        for (TextView label : labels) {
            label.setTranslationY(valueToYPosition(graphData, value) - label.getHeight() / 2);
            label.setText(String.valueOf((int) value));
            value -= step;
        }
    }
}
