package com.example.covid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity3 extends AppCompatActivity {

    private RadioGroup rg;
    private RadioButton r1,r2;
    private RecyclerView programminglist;
    private RequestQueue mQueue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        mQueue = Volley.newRequestQueue(this);


        rg = findViewById(R.id.cs);
        r1 = findViewById(R.id.ind);
        r2 = findViewById(R.id.con);
        programminglist = findViewById(R.id.pl);
        programminglist.setLayoutManager(new LinearLayoutManager(this));




        String url= "https://api.covid19india.org/data.json";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("statewise");

                            String[] state = new String[jsonArray.length()];
                            String[] con = new String[jsonArray.length()];
                            String[] rec = new String[jsonArray.length()];
                            String[] dea = new String[jsonArray.length()];



                            for (int i=1;i<jsonArray.length();i++){

                                JSONObject employee = jsonArray.getJSONObject(i);

                                String f1 = employee.getString("state");
                                String f2 = employee.getString("confirmed");
                                String f3 = employee.getString("recovered");
                                String f4 = employee.getString("deaths");
                                state[i]=f1;
                                con[i]=f2;
                                rec[i]=f3;
                                dea[i]=f4;


                            }



                            programmingadapter adapter = new programmingadapter(state,con,rec,dea);
                            programminglist.setAdapter(adapter);
                            System.out.println("inhfjshfkshdfkshsssssssssssssssssssssssss");






//                            conf.setText(f1);
//                            rec.setText(f2);
//                            desc.setText(f3);


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







        if(r1.isChecked()){
//            jsonParse_ind();
        }
        else {
//            jsonParse_sta();
        }



        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if(r1.isChecked()){
//                    jsonParse_ind();
                    Intent intent = new Intent(MainActivity3.this,MainActivity2.class);
                    startActivity(intent);

                }
                else {
//                    jsonParse_sta();

                }

            }
        });


    }


}