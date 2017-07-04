package com.alex.mirash.graph.graph.control;

import android.content.res.Resources;

import com.alex.mirash.graph.graph.model.GraphModel;
import com.alex.mirash.graph.graph.view.GraphView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * @author Mirash
 */

public abstract class GraphBaseController {
    protected GraphView graphView;
    protected GraphModel graphModel;
    protected GraphParams params;

    protected DateFormat dateFormat;

    public GraphBaseController(GraphView graphView) {
        this.graphView = graphView;
        params = new GraphParams(provideParams(graphView.getResources()));
        graphView.setParams(params);

        dateFormat = createDateFormat();
    }


    protected DateFormat createDateFormat() {
        return new SimpleDateFormat("yyyy, MMM dd", Locale.getDefault());
    }

    protected abstract GraphParamsProvider provideParams(Resources resources);

    public abstract void updateGraph();

    protected abstract GraphModel retrieveGraphData();

    public void updateGraphView() {
        graphView.post(new Runnable() {
            @Override
            public void run() {
                graphView.update(graphModel);
            }
        });
    }

}
