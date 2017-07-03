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
        params = GraphController.params;
        createLabels();
    }


    private void createLabels() {
        int labelsCount = params.getValueLabelsCount();
        labels = new ArrayList<>();
        for (int i = 0; i < labelsCount; i++) {
            TextView label = createLabel();
            addView(label);
            labels.add(label);
        }
    }

    private TextView createLabel() {
        TextView tv = new TextView(getContext());
        tv.setBackgroundColor(Color.CYAN);
        tv.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.graph_value_axis_label_text_size));
        LayoutParams lp = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.gravity = Gravity.CENTER_HORIZONTAL;
        tv.setLayoutParams(lp);
        return tv;
    }

    @Override
    public void update(GraphData graphData) {
        float step = (graphData.getValueInterval()) / (params.getValueLabelsCount() - 1);
        float value = graphData.getMaxValue();
        for (TextView label : labels) {
            label.setTranslationY(valueToYPosition(graphData, value) - label.getHeight() * 0.5f);
            label.setText(String.valueOf((int) value));
            value -= step;
        }
    }
}
