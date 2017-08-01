package com.example.user.timekeeper;

import android.content.DialogInterface;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.ContextThemeWrapper;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

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

        final DBHelper dbHelper = new DBHelper(this);


        btn_checkIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkIns.size()==checkOuts.size()){//previously checkdOut or zero checkIns
                    //caputure current time and store it in checkIns arrayList
                    Calendar calendar = Calendar.getInstance();
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy h:mm a");
                    String checkInTime = dateFormat.format(calendar.getTime());
                    checkIns.add(checkInTime);//logic purpose doesn't affect memory
                    dbHelper.insertCheckIn(checkInTime);
                    GPSTracker gpsTracker = new GPSTracker(getApplication());
                    Location location = gpsTracker.getLocation();
                    if(location!=null){
                        Double latitude = location.getLatitude();
                        Double longitude = location.getLongitude();

                        try {
                            dbHelper.insertLocation(getAddress(latitude,longitude));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
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
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy h:mm a");
                    String checkOutTime = dateFormat.format(calendar.getTime());
                    checkOuts.add(checkOutTime);//logic purpose doesn't affect memory
                    dbHelper.insertCheckOut(checkOutTime);
                    GPSTracker gpsTracker = new GPSTracker(getApplication());
                    Location location = gpsTracker.getLocation();
                    if(location!=null){
                        Double latitude = location.getLatitude();
                        Double longitude = location.getLongitude();

                        try {
                            dbHelper.insertLocation(getAddress(latitude,longitude));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
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
                   // Toast.makeText(HomeActivity.this, "There is no history", Toast.LENGTH_SHORT).show();
                }

            }
        });


    }
    public String getAddress(Double latitude, Double longitude) throws IOException {
        Geocoder geoCoder = new Geocoder(this, Locale.getDefault());
        List<Address> addresses = geoCoder.getFromLocation(latitude,longitude,1);
        String addressLine1 = addresses.get(0).getAddressLine(0);
        String addressLine2 = addresses.get(0).getAddressLine(1);
        String addressLine3 = addresses.get(0).getAddressLine(2);

        String address = addressLine1 +" "+addressLine2 +"\n"+addressLine3;
        return address;
    }
}
