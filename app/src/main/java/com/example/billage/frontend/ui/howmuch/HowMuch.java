package com.example.billage.frontend.ui.howmuch;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.billage.R;
import com.example.billage.backend.api.StatisticTransaction;
import com.example.billage.frontend.ui.home.subView.statistic.CustomMarker;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.warkiz.widget.IndicatorSeekBar;
import com.warkiz.widget.OnSeekChangeListener;
import com.warkiz.widget.SeekParams;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;

public class HowMuch extends AppCompatActivity {

    private HorizontalBarChart horizontalBarChart;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_how_much);

        IndicatorSeekBar seekBar = findViewById(R.id.seek_bar);

        TextView header = findViewById(R.id.how_much_header);
        seekBar.setIndicatorTextFormat("${TICK_TEXT}개월");

        ArrayList<Integer> usage = StatisticTransaction.monthly_statistic("출금");
        ArrayList<BarEntry> usage_entries = new ArrayList<>();

        for(int i=2;i<=2;i++){
            usage_entries.add(new BarEntry(i-1, usage.get(i-2)));
        }

        setBarChart(usage_entries);


        seekBar.setOnSeekChangeListener(new OnSeekChangeListener() {
            @Override
            public void onSeeking(SeekParams seekParams) {

                int cur_value = seekBar.getProgress();
                header.setText(cur_value + "개월간 총 지출은 200000원 입니다.");

                ArrayList<BarEntry> usage_entries = new ArrayList<>();

                for(int i=2;i<=cur_value+1;i++){
                    usage_entries.add(new BarEntry(i-1, usage.get(i-2)));
                }

                setBarChart(usage_entries);

            }

            @Override
            public void onStartTrackingTouch(IndicatorSeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(IndicatorSeekBar seekBar) {

            }
        });

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void setBarChart(ArrayList usage_entries) {

        BarDataSet depenses_usage = new BarDataSet(usage_entries, "수입");

        ArrayList<IBarDataSet> dataSets = new ArrayList<>();
        dataSets.add((IBarDataSet)depenses_usage);
        BarData data = new BarData(dataSets);

        horizontalBarChart = findViewById(R.id.horizontal_chart);

        depenses_usage.setBarBorderWidth(0.9f);
        depenses_usage.setColors(ColorTemplate.VORDIPLOM_COLORS);
        XAxis xAxis = horizontalBarChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);

        LocalDate now = LocalDate.now();
        final String[] months = new String[]{"",now.minusMonths(6).getMonth().getValue()+"월", now.minusMonths(5).getMonth().getValue()+"월", now.minusMonths(4).getMonth().getValue()+"월", now.minusMonths(3).getMonth().getValue()+"월", now.minusMonths(2).getMonth().getValue()+"월",now.minusMonths(1).getMonth().getValue()+"월"};
        IndexAxisValueFormatter formatter = new IndexAxisValueFormatter(months);
        xAxis.setGranularity(1f);
        xAxis.setValueFormatter(formatter);

        YAxis yAxis_right = horizontalBarChart.getAxisRight();
        yAxis_right.setDrawLabels(false);

        //custom marker view 설정
        CustomMarker marker = new CustomMarker(this,R.layout.custom_marker);
        horizontalBarChart.setMarker(marker);

        //value customizing
        data.setValueTextSize(0f);

        horizontalBarChart.setData(data);
        horizontalBarChart.animateXY(1000, 1000);
        horizontalBarChart.invalidate();
    }

}
