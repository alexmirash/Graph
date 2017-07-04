package com.alex.mirash.graph.graph.control;

import android.os.AsyncTask;

import com.alex.mirash.graph.GraphApp;
import com.alex.mirash.graph.PeriodUtils;
import com.alex.mirash.graph.graph.model.GraphModel;
import com.alex.mirash.graph.graph.model.TimeModel;
import com.alex.mirash.graph.graph.model.ValueModel;
import com.alex.mirash.graph.graph.tool.GraphUtils;
import com.alex.mirash.graph.graph.view.GraphView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * @author Mirash
 */

public class GraphController {
    private GraphView graphView;
    private GraphModel graphModel;
    private GraphParams params;

    private AsyncTask task;

    private DateFormat dateFormat = new SimpleDateFormat("yyyy, MMM dd", Locale.getDefault());

    public GraphController(GraphView graphView) {
        this.graphView = graphView;
        params = new GraphParams(GraphApp.getInstance().getResources(), new TestParamsProvider(graphView.getResources()));
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
        calculateTimeLabels(model);
        return model;
    }

    private void applyMinMaxValues(GraphModel graphModel) {
        graphModel.setValuesInterval(0, 100);
    }

    private void calculateGraphAttributes(GraphModel graphModel) {
        float width = GraphUtils.timeIntervalToPixels(graphModel.getTimeInterval());
        params.setValueWidth(width);
    }

    private void calculateTimeLabels(GraphModel graphModel) {
        long startTime = graphModel.getStartTime();
        long endTime = graphModel.getFinishTime();
        Calendar calendar = Calendar.getInstance();
        for (long time = startTime; time <= endTime; time += PeriodUtils.MILLIS_IN_WEEK) {
            calendar.setTimeInMillis(time);
            String startLabel = dateFormat.format(calendar.getTime());
            calendar.add(Calendar.DAY_OF_YEAR, 7);
            calendar.add(Calendar.MILLISECOND, -1);
            String endLabel = dateFormat.format(calendar.getTime());
            graphModel.addTimeLabel(new TimeModel(time, time + PeriodUtils.MILLIS_IN_WEEK, startLabel + "\n" + endLabel));
        }
    }

    private GraphModel generateTestData() {
        long startTime;
        long finishTime;

        Calendar calendar = Calendar.getInstance();
        PeriodUtils.applyEndOfWeek(calendar);
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
        graphModel.addValue(new ValueModel(System.currentTimeMillis(), 50));
        calendar = Calendar.getInstance();
        PeriodUtils.applyEndOfWeek(calendar);
        graphModel.addValue(new ValueModel(calendar.getTimeInMillis(), 50));

        graphModel.setGoalValue(55f);
        return graphModel;
    }

    public void clear() {
        if (task != null) {
            task.cancel(true);
            task = null;
        }
    }
}
