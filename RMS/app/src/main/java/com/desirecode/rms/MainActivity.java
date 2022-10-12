package com.desirecode.rms;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.text.TextUtils;
import android.view.View;
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

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText shortName, regNo, department, index, mobile, userName, password;
    private Button registerBtn;
    private TextView textViewLogin;
    private ProgressDialog progressDialog;
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //#######  Array to spinner department #########
        spinner=findViewById(R.id.spinner);


        if(SharedPrefManager.getInstance(this).isLoggedIn()){
            finish();
            startActivity(new Intent(this, ProfileActivity.class));
            return;
        }


        shortName = findViewById(R.id.nameTxt);
        regNo=findViewById(R.id.regNoTxt);
        index=findViewById(R.id.indexTxt);
       // department=findViewById(R.id.);
        mobile=findViewById(R.id.mobileNoTxt);
        userName=findViewById(R.id.userNameTxt);
        password=findViewById(R.id.passwordTxt);

        registerBtn=findViewById(R.id.regBtn);
        textViewLogin=findViewById(R.id.textViewLogin);

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

        final String mobileNo=mobile.getText().toString().trim();
        // int mobileNo=Integer.parseInt(mobile.getText().toString());
        final String username=userName.getText().toString().trim();
        final String pwd=password.getText().toString().trim();
        String type="register";

        if(TextUtils.isEmpty(indexNo) || TextUtils.isEmpty(shortname) || TextUtils.isEmpty(regno) || TextUtils.isEmpty(username) ||TextUtils.isEmpty(pwd)) {
            Toast.makeText(MainActivity.this, "some field not filled", Toast.LENGTH_SHORT).show();
        } else {
            if (TextUtils.isDigitsOnly(indexNo)){
                if (TextUtils.isDigitsOnly(mobileNo)){

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
                            // params.put("dep",dep);
                            params.put("mobile",mobileNo);
                            params.put("username",username);
                            params.put("password",pwd);
                            return params;
                        }
                    };

                    RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
        /*RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);*/

                }else {
                    Toast.makeText(MainActivity.this, "mobile number should be integer", Toast.LENGTH_SHORT).show();
                }
            }else {
                Toast.makeText(MainActivity.this, "index should be number", Toast.LENGTH_SHORT).show();
            }



        }


    }


}