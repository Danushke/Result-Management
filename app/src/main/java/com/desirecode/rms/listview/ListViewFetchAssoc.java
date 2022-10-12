package com.desirecode.rms.listview;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.desirecode.rms.Constant;
import com.desirecode.rms.R;
import com.desirecode.rms.RequestHandler;
import com.desirecode.rms.SharedPrefManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.appcompat.app.AppCompatActivity;

public class ListViewFetchAssoc extends AppCompatActivity {

    Button btn;
    EditText et;
    ListView listView;
    ArrayList<String> stringArrayList;
    ArrayAdapter<String> stringArrayAdapter;
    String index, t_name, result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        btn = findViewById(R.id.add_btn);
        et = findViewById(R.id.e_t_list);
        listView = findViewById(R.id.list_view2);

        stringArrayList = new ArrayList<String>();
       /* for (int i=0; i<5;i++){
            stringArrayList.add("Row"+i);
        }*/
        stringArrayAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, stringArrayList);
        listView.setAdapter(stringArrayAdapter);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stringArrayList.add(et.getText().toString());
                stringArrayAdapter.notifyDataSetChanged();

            }
        });

        String semester = getIntent().getStringExtra("SEMESTER");

        index = String.valueOf(SharedPrefManager.getInstance(this).getIndex());
        t_name = String.valueOf(SharedPrefManager.getInstance(this).getTableName());
        t_name = t_name + semester;
        result();
    }

    public void arraySetter(String[] ar) {
        for (int i = 0; i < ar.length; i++) {
            stringArrayList.add(ar[i]+ar[i]);
        }
    }

    public void concatArray(JSONArray ar) {
        Toast.makeText(getApplicationContext(), "method", Toast.LENGTH_SHORT).show();
        for (int i = 1; i < ar.length(); i++) {
            try {
                stringArrayList.add((String) ar.get(i));

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public void listArray(List<String> ar) {
        Toast.makeText(getApplicationContext(), "method2", Toast.LENGTH_SHORT).show();
        for (int i = 0; i < ar.toArray().length; i++) {
            stringArrayList.add(String.valueOf(ar));
        }

    }













    public void result() {
        //final String index=SharedPrefManager.getInstance(this).getIndex();
        //final String index="";
        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                Constant.URL_RESULT_OB,
                //"http://192.168.43.20/result/Android/v1/getResults.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            /**["436","B-","B","C","C+","C-","C",""]
                             * {"error":false,"ict1301":"B-","ict1302":"B","ict1303":"C","cmt1301":"C+","cmt1303":"C-","cmt1005":"C"}
                             * these two are take as objects in java*/

                            /**[{"day":"Monday","t1":"","t2":"","t3":"","t4":"","t5":"","t6":"","t7":"","t8":"ICT 3217 (T)","t9":"ICT 3217 (T)","t10":""}]
                             * this is a array*/
                            //JSONObject object = new JSONObject(response);
                            /**JSONArray array = object.getJSONArray("436");
                            //JSONArray array = object.names();*/

                            JSONArray array = new JSONArray(response);
                            List<String> list=new ArrayList<String>();
                            for (int i=0; i<array.length();i++){
                                list.add(array.getString(i));
                            }
                            listArray(list);


                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(), "catch", Toast.LENGTH_SHORT).show();
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
        ) {
            //pass the username and password to stringRequest bellow
            //For that we override the method get params
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("index_no", "436");
                params.put("table_name", "ict1617sem1");
//                params.put("timetable", "ict1617");
                return params;
            }
        };
        //Volley.newRequestQueue(this).add(stringRequest);
        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
    }
}