package app.test.com.testapp;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.util.Log;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.MPPointF;

import java.util.ArrayList;

import app.test.com.testapp.custom.BarMoneyY;

public class ChartAct extends BaseAct {
    protected String[] mParties = new String[]{
            "Party A", "Party B", "Party C", "Party D", "Party E", "Party F", "Party G", "Party H",
            "Party I", "Party J", "Party K", "Party L", "Party M", "Party N", "Party O", "Party P",
            "Party Q", "Party R", "Party S", "Party T", "Party U", "Party V", "Party W", "Party X",
            "Party Y", "Party Z"
    };


    private LineChart mChart;
    private BarChart mBarChart;
    private PieChart pieChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);

        initLinChart1();
        initBarChart();
        initPieChart();
    }


    private void initLinChart1() {
        mChart = (LineChart) findViewById(R.id.linchart);
        XAxis xAxis = mChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);

        YAxis leftAxis = mChart.getAxisLeft();
        leftAxis.removeAllLimitLines(); // reset all limit lines to avoid overlapping lines
        leftAxis.setTextSize(8f);
        leftAxis.setTextColor(Color.DKGRAY);
        //        leftAxis.setValueFormatter(new PercentFormatter());
        leftAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);//INSIDE_CHART
        leftAxis.setDrawGridLines(true);
        leftAxis.setGranularityEnabled(true);
        leftAxis.setAxisMinimum(0f);
        leftAxis.setAxisMaximum(200f);
        //        leftAxis.setYOffset(-9f);

        YAxis rightAxis = mChart.getAxisRight();
        rightAxis.removeAllLimitLines();
        rightAxis.setTextSize(8f);
        rightAxis.setTextColor(Color.DKGRAY);
        rightAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
        rightAxis.setDrawGridLines(true);
        rightAxis.setGranularityEnabled(true);
        rightAxis.setAxisMinimum(0f);
        rightAxis.setAxisMaximum(200f);

        // no description text
        //        mChart.getDescription().setEnabled(false);
        Description description = mChart.getDescription();
        description.setText("Dual YAxis");
        // enable touch gestures
        mChart.setTouchEnabled(true);
        mChart.setDragDecelerationFrictionCoef(0.9f);
        // enable scaling and dragging
        mChart.setDragEnabled(true);
        mChart.setScaleEnabled(true);
        mChart.setDrawGridBackground(false);
        mChart.setHighlightPerDragEnabled(true);
        // if disabled, scaling can be done on x- and y-axis separately
        mChart.setPinchZoom(true);
        // set an alternative background color
        mChart.setBackgroundColor(Color.LTGRAY);
        mChart.animateX(2000);

        makeLineData1(10, 200);
        //        mChart.invalidate();
    }

    private void makeLineData1(int count, float range) {
        ArrayList<Entry> yVals1 = new ArrayList<Entry>();
        for (int i = 0; i < count; i++) {
            float mult = range / 2f;
            float val = (float) (Math.random() * mult + 100);
            yVals1.add(new Entry(i, val));
        }

        ArrayList<Entry> yVals2 = new ArrayList<Entry>();
        for (int i = 0; i < count; i++) {
            float mult = range / 2;
            float val = (float) (Math.random() * mult);
            yVals2.add(new Entry(i, val));
        }

        LineDataSet set1, set2;
        if (mChart.getData() != null && mChart.getData().getDataSetCount() > 0) {
            set1 = (LineDataSet) mChart.getData().getDataSetByIndex(0);
            set2 = (LineDataSet) mChart.getData().getDataSetByIndex(1);
            set1.setValues(yVals1);
            set2.setValues(yVals2);
            mChart.getData().notifyDataChanged();
            mChart.notifyDataSetChanged();
        } else {
            set1 = new LineDataSet(yVals1, "DataSet 1");
            set1.setAxisDependency(YAxis.AxisDependency.LEFT);
            set1.setColor(ColorTemplate.getHoloBlue());
            set1.setCircleColor(Color.WHITE);
            set1.setLineWidth(2f);
            set1.setCircleRadius(3f);
            set1.setHighLightColor(Color.rgb(244, 117, 117));
            set1.setDrawCircleHole(false);
            //            set1.setDrawFilled(true);
            //            set1.setFillColor(Color.BLUE);
            //            set1.setFillAlpha(60);
            set1.setMode(LineDataSet.Mode.CUBIC_BEZIER);


            // create a dataset and give it a type
            set2 = new LineDataSet(yVals2, "DataSet 2");
            set2.setAxisDependency(YAxis.AxisDependency.RIGHT);
            set2.setColor(Color.RED);
            set2.setCircleColor(Color.WHITE);
            set2.setLineWidth(2f);
            set2.setCircleRadius(3f);
            set2.setDrawCircleHole(false);
            set2.setHighLightColor(Color.rgb(244, 130, 130));
            set2.setDrawCircleHole(false);
            set2.setDrawFilled(true);
            set2.setFillAlpha(65);
            set2.setFillColor(Color.RED);
            //set2.setFillFormatter(new MyFillFormatter(900f));
            set2.setMode(LineDataSet.Mode.CUBIC_BEZIER);

            LineData data = new LineData(set1, set2);
            data.setValueTextColor(Color.BLUE);
            data.setValueTextSize(9f);

            mChart.setData(data);
        }

    }


    //////////////////////////////////////////////////////////////////

    private void initBarChart() {
        mBarChart = (BarChart) findViewById(R.id.barChart);
        mBarChart.setDrawBarShadow(false);
        mBarChart.setDrawValueAboveBar(true);
        Description description = mBarChart.getDescription();
        description.setText("BarChart");
        mBarChart.setMaxVisibleValueCount(30);
        mBarChart.setPinchZoom(false);
        mBarChart.setDrawGridBackground(false);

        XAxis xAxis = mBarChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        //        xAxis.setTypeface(mTfLight);
        xAxis.setDrawGridLines(false);
        xAxis.setGranularity(1f); // only intervals of 1 day
        xAxis.setLabelCount(7);

        BarMoneyY barMoneyY = new BarMoneyY();
        YAxis leftAxis = mBarChart.getAxisLeft();
        //        leftAxis.setTypeface(mTfLight);
        leftAxis.setLabelCount(8, false);
        leftAxis.setValueFormatter(barMoneyY);
        leftAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
        leftAxis.setSpaceTop(15f);
        leftAxis.setAxisMinimum(0f);
        YAxis rightAxis = mBarChart.getAxisRight();
        rightAxis.setDrawGridLines(false);
        //        rightAxis.setTypeface(mTfLight);
        rightAxis.setLabelCount(8, false);
        rightAxis.setValueFormatter(barMoneyY);
        rightAxis.setSpaceTop(15f);
        rightAxis.setAxisMinimum(0f);

        Legend l = mBarChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(false);
        l.setForm(Legend.LegendForm.SQUARE);
        l.setFormSize(9f);
        l.setTextSize(11f);
        l.setXEntrySpace(4f);

        setData(10, 50);
    }

    private void setData(int count, float range) {

        float start = 1f;

        ArrayList<BarEntry> yVals1 = new ArrayList<BarEntry>();

        for (int i = (int) start; i < start + count + 1; i++) {
            float mult = (range + 1);
            float val = (float) (Math.random() * mult);

            if (Math.random() * 100 < 25) {
                yVals1.add(new BarEntry(i, val, getResources().getDrawable(R.drawable.star)));
            } else {
                yVals1.add(new BarEntry(i, val));
            }
        }

        BarDataSet set1;

        if (mBarChart.getData() != null &&
                mBarChart.getData().getDataSetCount() > 0) {
            set1 = (BarDataSet) mBarChart.getData().getDataSetByIndex(0);
            set1.setValues(yVals1);
            mBarChart.getData().notifyDataChanged();
            mBarChart.notifyDataSetChanged();
        } else {
            set1 = new BarDataSet(yVals1, "The year 2017");

            set1.setDrawIcons(false);

            set1.setColors(ColorTemplate.MATERIAL_COLORS);

            ArrayList<IBarDataSet> dataSets = new ArrayList<IBarDataSet>();
            dataSets.add(set1);

            BarData data = new BarData(dataSets);
            data.setValueTextSize(10f);
            //            data.setValueTypeface(mTfLight);
            data.setBarWidth(0.9f);

            mBarChart.setData(data);
        }
    }

    ////////////////////////////////////////////////////////////
    private void initPieChart() {
        pieChart = (PieChart) findViewById(R.id.pieChart);
        pieChart.setUsePercentValues(true);
        pieChart.getDescription().setEnabled(false);
        pieChart.setExtraOffsets(5, 10, 5, 5);
        pieChart.setDragDecelerationFrictionCoef(0.95f);
        pieChart.setCenterText(generateCenterSpannableText2());
        pieChart.setDrawHoleEnabled(true);
        pieChart.setHoleColor(Color.WHITE);
        pieChart.setTransparentCircleColor(Color.WHITE);
        pieChart.setTransparentCircleAlpha(110);
        pieChart.setHoleRadius(50f);
        pieChart.setTransparentCircleRadius(53f);
        pieChart.setDrawCenterText(true);
        pieChart.setRotationAngle(0);
        pieChart.setRotationEnabled(true);
        pieChart.setHighlightPerTapEnabled(true);
        pieChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {
                if (e == null)
                    return;
                Log.i("VAL SELECTED",
                        "Value: " + e.getY() + ", index: " + h.getX()
                                + ", DataSet index: " + h.getDataSetIndex());
            }

            @Override
            public void onNothingSelected() {
                Log.i("PieChart", "nothing selected");
            }
        });

        setPieChartData(5, 100);
        mChart.animateY(1400, Easing.EasingOption.EaseInOutQuad);

        Legend l = mChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setDrawInside(false);
        l.setXEntrySpace(7f);
        l.setYEntrySpace(0f);
        l.setYOffset(0f);
        // entry label styling
        pieChart.setEntryLabelColor(Color.RED);
        pieChart.setEntryLabelTextSize(12f);


    }

    private SpannableString generateCenterSpannableText() {
        //"MPAndroidChart\ndeveloped by Philipp Jahoda"
        SpannableString s = new SpannableString("MPAndroidChart\ndeveloped by Philipp Jahoda");
        s.setSpan(new RelativeSizeSpan(1.7f), 0, 14, 0);
        s.setSpan(new StyleSpan(Typeface.NORMAL), 14, s.length() - 15, 0);
        s.setSpan(new ForegroundColorSpan(Color.GRAY), 14, s.length() - 15, 0);
        s.setSpan(new RelativeSizeSpan(.8f), 14, s.length() - 15, 0);
        s.setSpan(new StyleSpan(Typeface.ITALIC), s.length() - 14, s.length(), 0);
        s.setSpan(new ForegroundColorSpan(ColorTemplate.getHoloBlue()), s.length() - 14, s.length(), 0);
        return s;
    }

    private SpannableString generateCenterSpannableText2() {
        SpannableString s = new SpannableString("PieChart\ndeveloped by Mr Yang");
        s.setSpan(new RelativeSizeSpan(1.7f), 0, 8, 0);
        s.setSpan(new StyleSpan(Typeface.NORMAL), 8, s.length() - 8, 0);
        s.setSpan(new ForegroundColorSpan(Color.RED), 8, s.length() - 8, 0);
        s.setSpan(new RelativeSizeSpan(.8f), 8, s.length() - 7, 0);
        s.setSpan(new StyleSpan(Typeface.ITALIC), s.length() - 7, s.length(), 0);
        s.setSpan(new ForegroundColorSpan(ColorTemplate.getHoloBlue()), s.length() - 7, s.length(), 0);
        return s;
    }

    private void setPieChartData(int count, float range) {

        float mult = range;

        ArrayList<PieEntry> entries = new ArrayList<PieEntry>();

        // NOTE: The order of the entries when being added to the entries array determines their position around the center of
        // the chart.
        for (int i = 0; i < count; i++) {
            entries.add(new PieEntry((float) ((Math.random() * mult) + mult / 5),
                    mParties[i % mParties.length],
                    getResources().getDrawable(R.drawable.star)));
        }

        PieDataSet dataSet = new PieDataSet(entries, "Election Results");

        dataSet.setDrawIcons(false);

        dataSet.setSliceSpace(3f);
        dataSet.setIconsOffset(new MPPointF(0, 40));
        dataSet.setSelectionShift(5f);

        // add a lot of colors

        ArrayList<Integer> colors = new ArrayList<Integer>();

        for (int c : ColorTemplate.VORDIPLOM_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.JOYFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.COLORFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.LIBERTY_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.PASTEL_COLORS)
            colors.add(c);

        colors.add(ColorTemplate.getHoloBlue());

        dataSet.setColors(colors);
        //dataSet.setSelectionShift(0f);

        PieData data = new PieData(dataSet);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(11f);
        data.setValueTextColor(Color.RED);
        pieChart.setData(data);

        // undo all highlights
        pieChart.highlightValues(null);
        pieChart.invalidate();
    }


}
