package com.desirecode.rms;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class UserLogin extends AppCompatActivity {
    private EditText editTextUsername,editTextPassword;
    private Button buttonLogin;
    private ProgressDialog progressDialog;
    private TextView register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);

        //if user already logged in not see login activity
        if (SharedPrefManager.getInstance(this).isLoggedIn()){
            finish();
            startActivity(new Intent(this,ProfileActivity.class));
            return;
            //not execute things in bellow if user already logged in because we use the "return" key word above

        }
        register=findViewById(R.id.textViewRegister);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });

        editTextUsername=findViewById(R.id.username);
        editTextPassword=findViewById(R.id.password);
        buttonLogin=findViewById(R.id.login);

        progressDialog =new ProgressDialog(this);
        progressDialog.setMessage("Please wait......");

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userLogin();
            }
        });
    }

    private void userLogin(){
        final String username = editTextUsername.getText().toString().trim();
        final String password = editTextPassword.getText().toString().trim();

        if( TextUtils.isEmpty(username) ||TextUtils.isEmpty(password)) {
            Toast.makeText(UserLogin.this, "please fill both field", Toast.LENGTH_SHORT).show();
        }else {

            progressDialog.show();
            StringRequest stringRequest = new StringRequest(
                    Request.Method.POST,
                    Constant.URL_LOGIN,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            progressDialog.dismiss();
                            //convert this response objrct to JSON Object
                            try {
                                JSONObject object = new JSONObject(response);
                                //not true object.getBoolean("error") means user success fully authenticate
                                if (!object.getBoolean("error")) {

                                    //store the user data to shared preference
                                    SharedPrefManager.getInstance(getApplicationContext())
                                            .userLogin(
                                                    //in here should equal the parsing variables with php response
                                                    object.getInt("index"),
                                                    object.getString("user name"),
                                                    object.getString("dep")
                                            );
                                    Toast.makeText(
                                            getApplicationContext(),
                                            "User login success",
                                            Toast.LENGTH_LONG
                                    ).show();

                                    startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                                    finish();
                                } else {
                                    Toast.makeText(
                                            getApplicationContext(),
                                            //show the message coming from server
                                            object.getString("message"),
                                            Toast.LENGTH_SHORT
                                    ).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            progressDialog.dismiss();
                            Toast.makeText(
                                    getApplicationContext(),
                                    //error.getMessage(),
                                    "sdf",
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
                    params.put("username", username);
                    params.put("password", password);
                    return params;
                }
            };
            RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
        }
    }
}
