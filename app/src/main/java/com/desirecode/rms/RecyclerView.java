package com.desirecode.rms;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class RecyclerView extends AppCompatActivity {

    String jsonString;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        jsonString=getIntent().getExtras().getString("json_data");


    }
}
