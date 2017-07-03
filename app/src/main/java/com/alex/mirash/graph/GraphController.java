package com.alex.mirash.graph;

import android.os.AsyncTask;
import android.util.Log;

import com.alex.mirash.graph.graph.model.GraphModel;
import com.alex.mirash.graph.graph.model.ValueModel;
import com.alex.mirash.graph.graph.tool.GraphParams;
import com.alex.mirash.graph.graph.tool.GraphUtils;
import com.alex.mirash.graph.graph.view.GraphView;

import java.util.Calendar;

/**
 * @author Mirash
 */

public class GraphController {
    private GraphView graphView;
    private GraphModel graphModel;
    private GraphParams params;

    private AsyncTask task;

    public GraphController(GraphView graphView) {
        this.graphView = graphView;
        params = new GraphParams(graphView.getResources());
        graphView.setParams(params);
    }

    public void updateGraph() {
        task = new AsyncTask<Void, Void, GraphModel>() {

            @Override
            protected GraphModel doInBackground(Void... voids) {
                return retrieveGraphData();
            }

            @Override
            protected void onPostExecute(GraphModel model) {
                task = null;
                graphModel = model;
                graphView.post(new Runnable() {
                    @Override
                    public void run() {
                        graphView.update(graphModel);
                    }
                });
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    private GraphModel retrieveGraphData() {
        GraphModel model = generateTestData();
        applyMinMaxValues(model);
        calculateGraphAttributes(model);
        return model;
    }

    private void applyMinMaxValues(GraphModel graphModel) {
        graphModel.setValuesInterval(0, 100);
    }

    private void calculateGraphAttributes(GraphModel graphModel) {
        float width = GraphUtils.timeIntervalToPixels(graphModel.getTimeInterval());
        params.setValueWidth(width);
    }

    private GraphModel generateTestData() {
        Log.d("LOL", "generate test data");
        long startTime;
        long finishTime;

        Calendar calendar = Calendar.getInstance();
        finishTime = calendar.getTimeInMillis();
        calendar.add(Calendar.MONTH, -3);
        PeriodUtils.applyStartOfMonth(calendar);
        startTime = calendar.getTimeInMillis();

        GraphModel graphModel = new GraphModel();
        graphModel.setTimeInterval(startTime, finishTime);

 /*       int i = 0;
     for (long time = startTime + PeriodUtils.MILLIS_IN_HOUR * 12; time < finishTime; time += PeriodUtils.MILLIS_IN_DAY) {
            graphModel.addValue(new ValueModel(time, i));
            i++;
        }*/
        for (int i = 0; i <= 10; i++) {
            graphModel.addValue(new ValueModel(startTime + PeriodUtils.MILLIS_IN_DAY * i, i * 10));
        }
        return graphModel;
    }

    public void clear() {
        if (task != null) {
            task.cancel(true);
            task = null;
        }
    }
}
