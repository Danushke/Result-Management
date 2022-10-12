package com.desirecode.rms;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    private EditText shortName, regNo, index,email, mobile, userName, password;
    private Button registerBtn;
    private TextView textViewLogin;
    private ProgressDialog progressDialog;
    Spinner spinner;
    String department;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //#######  Array to spinner department #########


        if(SharedPrefManager.getInstance(this).isLoggedIn()){
            finish();
            startActivity(new Intent(this, ProfileActivity.class));
            return;
        }

        spinner=findViewById(R.id.spinner_s);
        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this,R.array.department,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        shortName = findViewById(R.id.e_t_name);
        regNo=findViewById(R.id.e_t_regno);
        index=findViewById(R.id.e_t_index);
        //department=findViewById(R.id.);
        //mobile=findViewById(R.id.mobileNoTxt);
        email=findViewById(R.id.e_t_email);
        userName=findViewById(R.id.e_t_username);
        password=findViewById(R.id.e_t_password);

        registerBtn=findViewById(R.id.register);
        textViewLogin=findViewById(R.id.t_v_already_reg);

        progressDialog = new ProgressDialog(this);
        //progressDialog.setMessage();

        registerBtn.setOnClickListener(this);
        textViewLogin.setOnClickListener(this);
        /*registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });*/

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        if (position > 0) {
            department=parent.getItemAtPosition(position).toString();
            Toast.makeText(parent.getContext(), department, Toast.LENGTH_SHORT).show();
        }
        if (position==0){
            ((TextView) view).setTextColor(ContextCompat.getColor(this,R.color.textGray));
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onClick(View v) {
        if (v == registerBtn){
            registerUser();
        }else if (v == textViewLogin)
            startActivity(new Intent(this,UserLogin.class));
    }

    private void registerUser(){
        final String shortname=shortName.getText().toString().trim();
        final String regno=regNo.getText().toString().trim();
        final String indexNo=index.getText().toString().trim();
        // int indexNo=Integer.parseInt(index.getText().toString());
        final String eMail=email.getText().toString().trim();
        //final String mobileNo=mobile.getText().toString().trim();
        // int mobileNo=Integer.parseInt(mobile.getText().toString());
        final String username=userName.getText().toString().trim();
        final String pwd=password.getText().toString().trim();
        String type="register";

        if(TextUtils.isEmpty(indexNo) || TextUtils.isEmpty(shortname) || TextUtils.isEmpty(department) || TextUtils.isEmpty(regno) || TextUtils.isEmpty(username) ||TextUtils.isEmpty(pwd)) {
            Toast.makeText(MainActivity.this, "some field not filled", Toast.LENGTH_SHORT).show();
        } else {
            if (TextUtils.isDigitsOnly(indexNo)){

                    progressDialog.setMessage("Registering User....");
                    progressDialog.show();
                    StringRequest stringRequest = new StringRequest(Request.Method.POST,
                            Constant.URL_REGISTER,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    progressDialog.dismiss();
                                    /*this is the JSONobject {"error":true,"message":"Invalid Requst"} to show this we create JSONobject
                                     * in here "error" is the key & "true" is value. same message & Invalid request*/
                                    try {
                                        JSONObject jsonObject=new JSONObject(response);
                                        if (!jsonObject.getBoolean("error")){
                                            Toast.makeText(getApplicationContext(),jsonObject.getString("message"),Toast.LENGTH_LONG).show();
                                            startActivity(new Intent(getApplicationContext(),UserLogin.class));
                                            finish();
                                        }else {
                                            Toast.makeText(getApplicationContext(),jsonObject.getString("message"),Toast.LENGTH_LONG).show();
                                        }

                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            progressDialog.hide();
                            Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_LONG).show();
                        }
                    }) {
                        @Override
                        protected Map<String, String> getParams() {
                            Map<String,String> params=new HashMap<>();
                            params.put("index_no",indexNo);
                            params.put("short_name",shortname);
                            params.put("reg_no",regno);
                            params.put("dep",department);
                            params.put("mobile",eMail);
                            //params.put("mobile",mobileNo);
                            params.put("username",username);
                            params.put("password",pwd);
                            return params;
                        }
                    };

                    RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
        /*RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);*/

            }else {
                Toast.makeText(MainActivity.this, "index should be number", Toast.LENGTH_SHORT).show();
            }

        }

    }

}