package com.alex.mirash.graph.graph.view;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import com.alex.mirash.graph.graph.tool.GraphParams;
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
}
