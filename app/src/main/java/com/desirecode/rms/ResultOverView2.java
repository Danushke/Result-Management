package com.desirecode.rms;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class ResultOverView2 extends AppCompatActivity {

    String JSON_STRING, jsonsString;
    Button button;
    TextView txt;
    String result;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_over_view2);

        txt = findViewById(R.id.textView12);
        result=String.valueOf(SharedPrefManager.getInstance(this).getIndex());
    }

    //new BackgroundTask().execute();


//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//             // new  BackgroundTask().execute();
//            }
//        });





    class BackgroundTask extends AsyncTask<String,Void,String>{


        String json_url;

        @Override
        protected void onPreExecute() {
            json_url="http://192.168.100.100/result/Android/v1/jsonArray.php";

        }

        @Override
        protected String doInBackground(String...strings) {

            String index=strings[0];

            try {
                URL url=new URL(json_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoInput(true);
                httpURLConnection.setDoOutput(true);
                OutputStream outputStream=httpURLConnection.getOutputStream();
                BufferedWriter writer=new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String data= URLEncoder.encode("index", "UTF-8")+"="+URLEncoder.encode(index, "UTF-8");

                writer.write(data);
                writer.flush();
                writer.close();
                outputStream.close();


                InputStream inputStream=httpURLConnection.getInputStream();
                BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream,"ISO-8859-1"));
                StringBuilder stringBuilder=new StringBuilder();
                while ((JSON_STRING=bufferedReader.readLine())!=null){
                    stringBuilder.append(JSON_STRING+"\n");
                }

                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return stringBuilder.toString().trim();

            }catch (MalformedURLException e){
                e.printStackTrace();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }



        @Override
        protected void onPostExecute(String result) {
            TextView textView=findViewById(R.id.textView11);
            textView.setTextColor(Color.parseColor("#235411"));
            textView.setText(result);
           // jsonsString = result;
           // Toast.makeText(getApplicationContext(),index,Toast.LENGTH_LONG).show();
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }
    }

       public void viewResult(View view){
          /* if (jsonsString==null){
               Toast.makeText(getApplicationContext(),"dgvdcfvgf",Toast.LENGTH_LONG).show();
           }else {
            Intent intent=new Intent(this,ListView.class);
            intent.putExtra("json_data",jsonsString);
            startActivity(intent);
        }*/
           BackgroundTask obt=new BackgroundTask();
           obt.execute(result);
           //new BackgroundTask();

    }
}