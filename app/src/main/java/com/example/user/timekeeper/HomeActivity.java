package com.example.user.timekeeper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class HomeActivity extends AppCompatActivity {
    ArrayList<String> checkIns = new ArrayList<>();
    ArrayList<String> checkOuts = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        //getting current time


        Button btn_checkIn = (Button) findViewById(R.id.UI_BTN_checkIn);
        Button btn_checkOut = (Button) findViewById(R.id.UI_BTN_checkOut);
        Button btn_status = (Button) findViewById(R.id.UI_BTN_status);

        btn_checkIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkIns.size()==checkOuts.size()){//previously checkdOut or zero checkIns
                    //caputure current time and store it in checkIns arrayList
                    Calendar calendar = Calendar.getInstance();
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy h:mm a");
                    String checkInTime = dateFormat.format(calendar.getTime());
                    checkIns.add(checkInTime);
                    Toast.makeText(HomeActivity.this,"Your check in time: "+checkInTime, Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(getApplication(), "You have already Checked in!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btn_checkOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkIns.size()==checkOuts.size()){
                    Toast.makeText(HomeActivity.this, "Please check in first", Toast.LENGTH_SHORT).show();
                }
                else{
                    Calendar calendar = Calendar.getInstance();
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy h:mm a");
                    String checkOutTime = dateFormat.format(calendar.getTime());
                    checkOuts.add(checkOutTime);
                    Toast.makeText(HomeActivity.this, "Your check out time: "+checkOutTime, Toast.LENGTH_SHORT).show();
                }
            }
        });

       btn_status.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this,StatusActivity.class);
                startActivity(intent);
                if(checkIns.isEmpty()){
                    Toast.makeText(HomeActivity.this, "There is no history", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}
