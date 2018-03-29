package com.example.hamza.imageupload;

import android.graphics.Color;
import android.os.Bundle;
import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.view.LineChartView;

public class ChartActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);

        List<PointValue> values = new ArrayList<PointValue>();
        values.add(new PointValue(0, (float) 0.3));
        values.add(new PointValue(1, (float) 0.9));
        values.add(new PointValue(2, (float) 1.7));
        values.add(new PointValue(3, (float) 1.8));

        //In most cased you can call data model methods in builder-pattern-like manner.
        Line line = new Line(values).setColor(Color.BLUE).setCubic(true);
        List<Line> lines = new ArrayList<Line>();
        lines.add(line);

        LineChartData data = new LineChartData();
        data.setLines(lines);

        LineChartView chart = (LineChartView) findViewById(R.id.chart);
        chart.setLineChartData(data);

    }

}
