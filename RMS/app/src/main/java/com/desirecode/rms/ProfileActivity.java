package com.desirecode.rms;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class ProfileActivity extends AppCompatActivity {

    private TextView textViewUsername, sem1, sem2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


        textViewUsername=findViewById(R.id.textViewUsername);

        textViewUsername.setText(SharedPrefManager.getInstance(this).getUsername());



        // if user id not logged in
        if (!SharedPrefManager.getInstance(this).isLoggedIn()){
            finish();
            startActivity(new Intent(this, UserLogin.class));
        }

        String m="fdgvfdgfd";
        sem2=findViewById(R.id.sem2);
        sem2.setText("your GPA"+m);
        sem2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               //  startActivity(new Intent(getApplicationContext(),ResultOverView2.class));
            }
        });

        sem1=findViewById(R.id.sem1);
        sem1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),ResultOverView.class));
            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id=item.getItemId();
        switch (id){
            case R.id.menuLogout:
                SharedPrefManager.getInstance(this).logout();
                finish();
                startActivity(new Intent(this,UserLogin.class));
                break;
            case R.id.menuSetting:
                Toast.makeText(this,"Still not set that Feature",Toast.LENGTH_LONG).show();
                break;
        }
        return true;
        //switch (item.getItemId()){}

    }
}
