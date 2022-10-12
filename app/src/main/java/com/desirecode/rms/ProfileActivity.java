package com.desirecode.rms;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.desirecode.rms.listview.ListViewClass;

public class ProfileActivity extends AppCompatActivity {

    private TextView textViewUsername, sem1, sem2;
    CardView cardView1,cardView2,cardView3,cardView4,cardView5,cardView6;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


        textViewUsername=findViewById(R.id.textViewUsername);

        textViewUsername.setText(SharedPrefManager.getInstance(this).getShortname());



        // if user id not logged in
        if (!SharedPrefManager.getInstance(this).isLoggedIn()){
            finish();
            startActivity(new Intent(this, UserLogin.class));
        }

        cardView1=findViewById(R.id.c_v_1);
        cardView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               //  startActivity(new Intent(getApplicationContext(),ResultOverView2.class));
                startActivity(new Intent(getApplicationContext(),ListViewClass.class).putExtra("SEMESTER","sem1"));
            }
        });

        cardView2=findViewById(R.id.c_v_2);
        cardView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(getApplicationContext(),ResultOverView.class));
                startActivity(new Intent(getApplicationContext(), ListViewClass.class).putExtra("SEMESTER","sem2"));
                /*Intent intent =new Intent(getApplicationContext(),ResultOverView.class);
                intent.putExtra("SEMESTER","sem2");
                startActivity(intent);*/
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
