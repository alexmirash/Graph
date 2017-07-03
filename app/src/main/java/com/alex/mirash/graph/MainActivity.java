package com.alex.mirash.graph;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.alex.mirash.graph.graph.control.GraphController;
import com.alex.mirash.graph.graph.view.GraphView;

public class MainActivity extends AppCompatActivity {
    private GraphView graphView;
    private GraphController graphController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        graphView = (GraphView) findViewById(R.id.graph_view);
        graphController = new GraphController(graphView);

        test();
    }

    private void test() {
        graphController.updateGraph();
    }
}
