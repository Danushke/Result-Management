package com.desirecode.rms;

import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.LinearLayoutManager;


import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResultOverView3 extends AppCompatActivity {

    List<ResultGetter> productList;
    ResultAdapter adapter; //@@@@@@@@@@

    RecyclerView recyclerView; //@@@@@@@@@@@
    TextView tict1301,tict1302,tict1303,tcmt1301,tcmt1303,tcml1201,tcmt1005;
    String sict1301,sict1302,sict1303,scmt1301,scmt1303,scml1201,scmt1005, index, t_name, result;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_over_view);

        productList=new ArrayList<>();
        recyclerView=findViewById(R.id.recycler);//@@@@@@@@
        recyclerView.setHasFixedSize(true);//@@@@@@@@
        recyclerView.setLayoutManager(new LinearLayoutManager(this));//@@@@@@@@

        //get value from profile activity
        String semester=getIntent().getStringExtra("SEMESTER");

        index=String.valueOf(SharedPrefManager.getInstance(this).getIndex());
        t_name=String.valueOf(SharedPrefManager.getInstance(this).getTableName());
        t_name=t_name+semester;





       /* tict1301=findViewById(R.id.textView4);
        tict1302=findViewById(R.id.textView5);
        tict1303=findViewById(R.id.textView6);
        tcmt1301=findViewById(R.id.textView7);
        tcmt1303=findViewById(R.id.textView8);
        tcml1201=findViewById(R.id.textView9);
        tcmt1005=findViewById(R.id.textView10);

        tcmt1301.setText(sict1301);
        tict1302.setText(sict1302);
        tict1303.setText(sict1303);
        tcmt1301.setText(scmt1301);
        tcmt1303.setText(scmt1303);
        tcml1201.setText(scml1201);
        tcmt1005.setText(scmt1005);*/

        result();
    }




    public void result(){
        //final String index=SharedPrefManager.getInstance(this).getIndex();
        //final String index="";
        StringRequest stringRequest=new StringRequest(
                Request.Method.POST,
                Constant.URL_RESULT,
                //"http://192.168.43.20/result/Android/v1/getResults.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONArray array=new JSONArray(response);
                            JSONObject object=new JSONObject(response);

                            if (!object.getBoolean("error")) {

                                //JSONArray[] arr ={array};
                                String arr[]={String.valueOf(array)};

                                ResultGetter resultGetter = new ResultGetter(sict1301, sict1302, sict1303, scmt1301, scmt1303, scml1201, scmt1005);
                                productList.add(resultGetter);

                                adapter = new ResultAdapter(ResultOverView3.this, productList); //@@@@@@@@
                                recyclerView.setAdapter(adapter);//@@@@@@@@
                            }else {
                                Toast.makeText(getApplicationContext(), object.getString("message"), Toast.LENGTH_SHORT).show();
                            }
                        }
                        /*try {
                            JSONArray array=new JSONArray(response);

                            //not true object.getBoolean("error") means user success fully authenticate
                            for (int i=0; i<array.length(); i++){

                                JSONObject productobject=array.getJSONObject(i);

                                //                  Toast.makeText(getApplicationContext()," For FOR.",Toast.LENGTH_SHORT).show();

                                String sict1301=productobject.getString("ICT1301");
                                String sict1302=productobject.getString("ICT1302");
                                String sict1303=productobject.getString("ICT1303");
                                String scmt1301=productobject.getString("CMT1301");
                                String scmt1303=productobject.getString("CMT1303");
                                ///  String scml1201=productobject.getString("cml1201");
                                String scmt1005=productobject.getString("CMT1005");


                                ResultGetter resultGetter=new ResultGetter(sict1301,sict1302,sict1303,scmt1301,scmt1303,scml1201,scmt1005);
                                productList.add(resultGetter);

                            }
                            adapter = new ResultAdapter(ResultOverView.this,productList); //@@@@@@@@
                            recyclerView.setAdapter(adapter);//@@@@@@@@
                        }*/
                        catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(),"message",Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(
                                getApplicationContext(),
                                error.getMessage(),
                                Toast.LENGTH_LONG
                        ).show();
                    }
                }
        ){
            //pass the username and password to stringRequest bellow
            //For that we override the method get params
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params=new HashMap<>();
                params.put("index_no",index);
                params.put("table_name",t_name);
                return params;
            }
        };
        //RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
        Volley.newRequestQueue(this).add(stringRequest);

        //whatever can use through above two
    }
}
