package com.alex.mirash.graph.graph.control;

import android.content.res.Resources;
import android.os.AsyncTask;

import com.alex.mirash.graph.PeriodUtils;
import com.alex.mirash.graph.graph.model.GraphModel;
import com.alex.mirash.graph.graph.model.TimeModel;
import com.alex.mirash.graph.graph.model.ValueModel;
import com.alex.mirash.graph.graph.view.GraphView;

import java.util.Calendar;

/**
 * @author Mirash
 */

public class GraphController extends GraphBaseController {
    private AsyncTask task;

    public GraphController(GraphView graphView) {
        super(graphView);
    }

    @Override
    protected GraphParamsProvider provideParams(Resources res) {
        return new TestParamsProvider(res);
    }

    @Override
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
                updateGraphView();
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    @Override
    protected GraphModel retrieveGraphData() {
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
        float width = timeIntervalToPixels(graphModel.getTimeInterval());
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
