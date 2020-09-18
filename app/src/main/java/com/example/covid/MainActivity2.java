package com.example.covid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ViewPortHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {

    private RadioGroup rg,rg1;
    private RadioButton r1,r2,r3,r4,r5;
    private RequestQueue mQueue;
    private TextView conf,rec,desc;


    private LineChart mpLineChart;
    private int color = Color.argb(150,51,181,229);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        mQueue = Volley.newRequestQueue(this);
        rg = findViewById(R.id.cs);
        rg1 = findViewById(R.id.cs1);
        r1 = findViewById(R.id.ind);
        r2 = findViewById(R.id.con);
        r3 = findViewById(R.id.k1);
        r4 = findViewById(R.id.k2);
        r5 = findViewById(R.id.k3);
        conf = findViewById(R.id.p1);
        rec = findViewById(R.id.p2);
        desc = findViewById(R.id.p3);


        if(r1.isChecked()){
            jsonParse_ind();
        }
        else {

            jsonParse_sta();
        }

        if(r3.isChecked()){

            jsonParse_datap1();
        }
        else if(r4.isChecked()){
            jsonParse_datap2();

        }
        else {
            jsonParse_datap3();

        }


        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if(r1.isChecked()){
                    jsonParse_ind();

                }
                else {
                    Intent intent = new Intent(MainActivity2.this,MainActivity3.class);
                    startActivity(intent);
                    jsonParse_sta();

                }

            }
        });
        rg1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if(r3.isChecked()){
                    jsonParse_datap1();

                }
                else if(r4.isChecked()){
                    jsonParse_datap2();


                }
                else {
                    jsonParse_datap3();

                }


            }
        });




    }


    public void jsonParse_datap1(){
        String url= "https://api.covid19india.org/data.json";
        final ArrayList<Entry> values = new ArrayList<>();


        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("cases_time_series");



                            for (int i=0;i<jsonArray.length();i++){

                                JSONObject employee = jsonArray.getJSONObject(i);

                                float f1 = employee.getInt("totalconfirmed");
                                values.add(new Entry(i, f1));


                            }
                            mpLineChart = (LineChart) findViewById(R.id.chart);
                            mpLineChart.setBackground(getDrawable(R.drawable.kk1));
                            LineDataSet lineDataSet1 = new LineDataSet(values,"Data Set 1");
                            lineDataSet1.setLineWidth(2);
                            lineDataSet1.setDrawCircles(false);
                            lineDataSet1.setDrawValues(false);
                            lineDataSet1.setDrawHighlightIndicators(false);
                            lineDataSet1.setColor(Color.rgb(37,159,65));
                            ArrayList<ILineDataSet> dataSets = new ArrayList<>();
                            dataSets.add(lineDataSet1);
                            LineData data = new LineData(dataSets);
                            mpLineChart.setData(data);
                            mpLineChart.invalidate();
//                            mpLineChart.setBackgroundColor(Color.rgb(51,20,39)); //set whatever color you prefer
                            mpLineChart.setDrawGridBackground(false);
                            mpLineChart.getXAxis().setDrawGridLines(false);
                            XAxis xAxis = mpLineChart.getXAxis();
//                            xAxis.setEnabled(false);
//
                            YAxis yAxis = mpLineChart.getAxisLeft();
//                            yAxis.setEnabled(false);

                            YAxis yAxis2 = mpLineChart.getAxisRight();
//                            yAxis2.setEnabled(false);



                            mpLineChart.setPinchZoom(false);
                            mpLineChart.setDoubleTapToZoomEnabled(false);
                            mpLineChart.getAxisRight().setDrawLabels(false);
                            mpLineChart.getAxisLeft().setDrawLabels(false);
                            mpLineChart.getXAxis().setDrawLabels(false);
                            Legend l = mpLineChart.getLegend();
                            mpLineChart.setDescription(null);
                            l.setEnabled(false);


                        } catch (JSONException e) {
                            e.printStackTrace();

                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                System.out.println("it is in error block");
            }
        });

        mQueue.add(request);


    }

    private class myXaxisvalueformatter extends ValueFormatter{

        @Override
        public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
            return "man";
        }
    }

    public void jsonParse_datap2(){
        String url= "https://api.covid19india.org/data.json";
        final ArrayList<Entry> values = new ArrayList<>();


        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("cases_time_series");



                            for (int i=0;i<jsonArray.length();i++){

                                JSONObject employee = jsonArray.getJSONObject(i);

                                float f1 = employee.getInt("totaldeceased");
                                values.add(new Entry(i, f1));


                            }
                            mpLineChart = (LineChart) findViewById(R.id.chart);
                            mpLineChart.setBackground(getDrawable(R.drawable.kk2));
                            LineDataSet lineDataSet1 = new LineDataSet(values,"Data Set 1");
                            lineDataSet1.setLineWidth(2);
                            lineDataSet1.setDrawCircles(false);
                            lineDataSet1.setDrawValues(false);
                            lineDataSet1.setDrawHighlightIndicators(false);
                            lineDataSet1.setColor(Color.rgb(255,7,58));
                            ArrayList<ILineDataSet> dataSets = new ArrayList<>();
                            dataSets.add(lineDataSet1);
                            LineData data = new LineData(dataSets);
                            mpLineChart.setData(data);
                            mpLineChart.invalidate();
//                            mpLineChart.setBackgroundColor(Color.rgb(35,16,65)); //set whatever color you prefer
                            mpLineChart.setDrawGridBackground(false);
                            mpLineChart.getXAxis().setDrawGridLines(false);
                            XAxis xAxis = mpLineChart.getXAxis();
                            xAxis.setEnabled(false);

                            YAxis yAxis = mpLineChart.getAxisLeft();
                            yAxis.setEnabled(false);

                            YAxis yAxis2 = mpLineChart.getAxisRight();
                            yAxis2.setEnabled(false);

                            xAxis.setValueFormatter(new myXaxisvalueformatter());



                            mpLineChart.setPinchZoom(false);
                            mpLineChart.setDoubleTapToZoomEnabled(false);
                            mpLineChart.getAxisRight().setDrawLabels(false);
                            mpLineChart.getAxisLeft().setDrawLabels(false);
                            mpLineChart.getXAxis().setDrawLabels(false);
                            Legend l = mpLineChart.getLegend();
                            mpLineChart.setDescription(null);
                            l.setEnabled(false);


                        } catch (JSONException e) {
                            e.printStackTrace();

                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                System.out.println("it is in error block");
            }
        });

        mQueue.add(request);


    }

    public void jsonParse_datap3(){
        String url= "https://api.covid19india.org/data.json";
        final ArrayList<Entry> values = new ArrayList<>();


        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("cases_time_series");



                            for (int i=0;i<jsonArray.length();i++){

                                JSONObject employee = jsonArray.getJSONObject(i);

                                float f1 = employee.getInt("totalrecovered");
                                values.add(new Entry(i, f1));


                            }
                            mpLineChart = (LineChart) findViewById(R.id.chart);
                            mpLineChart.setBackground(getDrawable(R.drawable.kk3));
                            LineDataSet lineDataSet1 = new LineDataSet(values,"Data Set 1");
                            lineDataSet1.setLineWidth(2);
                            lineDataSet1.setDrawCircles(false);
                            lineDataSet1.setDrawValues(false);
                            lineDataSet1.setDrawHighlightIndicators(false);
                            lineDataSet1.setColor(Color.rgb(102,99,185));
                            ArrayList<ILineDataSet> dataSets = new ArrayList<>();
                            dataSets.add(lineDataSet1);
                            LineData data = new LineData(dataSets);
                            mpLineChart.setData(data);
                            mpLineChart.invalidate();
//                            mpLineChart.setBackgroundColor(Color.rgb(24,40,41)); //set whatever color you prefer
                            mpLineChart.setDrawGridBackground(false);
                            mpLineChart.getXAxis().setDrawGridLines(false);
                            XAxis xAxis = mpLineChart.getXAxis();
                            xAxis.setEnabled(false);

                            YAxis yAxis = mpLineChart.getAxisLeft();
                            yAxis.setEnabled(false);

                            YAxis yAxis2 = mpLineChart.getAxisRight();
                            yAxis2.setEnabled(false);



                            mpLineChart.setPinchZoom(false);
                            mpLineChart.setDoubleTapToZoomEnabled(false);
                            mpLineChart.getAxisRight().setDrawLabels(false);
                            mpLineChart.getAxisLeft().setDrawLabels(false);
                            mpLineChart.getXAxis().setDrawLabels(false);
                            Legend l = mpLineChart.getLegend();
                            mpLineChart.setDescription(null);
                            l.setEnabled(false);


                        } catch (JSONException e) {
                            e.printStackTrace();

                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                System.out.println("it is in error block");
            }
        });

        mQueue.add(request);


    }

//    public void jsonParse_datas1(){
//        String url= "https://api.covid19india.org/v4/timeseries.json";
//        final ArrayList<Entry> values = new ArrayList<>();
//
//
//        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
//                new Response.Listener<JSONObject>() {
//                    @Override
//                    public void onResponse(JSONObject response) {
//                        try {
//                            JSONArray jsonArray = response.getJSONArray("AN");
//                            JSONArray jsonArray1 = jsonArray.getJSONArray(0);
//                            System.out.println(jsonArray1);
//
//
//
////                            for (int i=0;i<jsonArray.length();i++){
////
////                                JSONObject employee = jsonArray.getJSONObject(i);
////
////                                float f1 = employee.getInt("confirmed");
////                                System.out.println("thisssssss "+f1);
////                                values.add(new Entry(i, f1));
////
////
////                            }
//                            mpLineChart = (LineChart) findViewById(R.id.chart);
//                            LineDataSet lineDataSet1 = new LineDataSet(values,"Data Set 1");
//                            lineDataSet1.setLineWidth(2);
//                            lineDataSet1.setDrawCircles(false);
//                            lineDataSet1.setDrawValues(false);
//                            lineDataSet1.setDrawHighlightIndicators(false);
//                            lineDataSet1.setColor(Color.rgb(76,217,123));
//                            ArrayList<ILineDataSet> dataSets = new ArrayList<>();
//                            dataSets.add(lineDataSet1);
//                            LineData data = new LineData(dataSets);
//                            mpLineChart.setData(data);
//                            mpLineChart.invalidate();
////                            mpLineChart.setBackgroundColor(Color.TRANSPARENT); //set whatever color you prefer
//                            mpLineChart.setDrawGridBackground(false);
//                            mpLineChart.getXAxis().setDrawGridLines(false);
//                            XAxis xAxis = mpLineChart.getXAxis();
//                            xAxis.setEnabled(false);
//
//                            YAxis yAxis = mpLineChart.getAxisLeft();
//                            yAxis.setEnabled(false);
//
//                            YAxis yAxis2 = mpLineChart.getAxisRight();
//                            yAxis2.setEnabled(false);
//
//
//
//                            mpLineChart.setPinchZoom(false);
//                            mpLineChart.setDoubleTapToZoomEnabled(false);
//                            mpLineChart.getAxisRight().setDrawLabels(false);
//                            mpLineChart.getAxisLeft().setDrawLabels(false);
//                            mpLineChart.getXAxis().setDrawLabels(false);
//                            Legend l = mpLineChart.getLegend();
//                            mpLineChart.setDescription(null);
//                            l.setEnabled(false);
//
//
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//
//                        }
//
//                    }
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                error.printStackTrace();
//                System.out.println("it is in error block");
//            }
//        });
//
//        mQueue.add(request);
//
//
//    }

    private void jsonParse_ind(){
        String url= "https://api.covid19india.org/data.json";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("statewise");


                            JSONObject employee = jsonArray.getJSONObject(0);

                            String f1 = employee.getString("confirmed");
                            String f2 = employee.getString("recovered");
                            String f3 = employee.getString("deaths");



                            conf.setText(f1);
                            rec.setText(f2);
                            desc.setText(f3);


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                System.out.println("it is in error block");
            }
        });

        mQueue.add(request);



    }

    private void jsonParse_sta(){
        String url= "https://api.covid19india.org/data.json";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("statewise");


                            JSONObject employee = jsonArray.getJSONObject(1);

                            String f1 = employee.getString("confirmed");
                            String f2 = employee.getString("recovered");
                            String f3 = employee.getString("deaths");



                            conf.setText(f1);
                            rec.setText(f2);
                            desc.setText(f3);


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                System.out.println("it is in error block");
            }
        });

        mQueue.add(request);


    }


}