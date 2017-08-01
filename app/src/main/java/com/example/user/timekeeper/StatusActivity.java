package com.example.user.timekeeper;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.SimpleCursorAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class StatusActivity extends AppCompatActivity {
    ArrayList<String> checkIns;
    ArrayList<String> checkOuts;

    public StatusActivity() {
    }

    /*public StatusActivity(ArrayList<String> checkIns, ArrayList<String> checkOuts) {
        this.checkIns = checkIns;
        this.checkOuts=checkOuts;
    }*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status);

        ListView LV_status = (ListView) findViewById(R.id.UI_LV_status);
        DBHelper dbHelper = new DBHelper(this);
        ArrayList<HashMap<String,String>> attendanceList = dbHelper.getAttendance();

        String[] from = {"checkInTime","checkoutTime","location"};
        int[] to = {R.id.UI_TV_checkIn,R.id.UI_TV_checkOut,R.id.UI_TV_Location};
        SimpleAdapter simpleAdapter = new SimpleAdapter(getApplication(),attendanceList,R.layout.singlelistview,from,to);
        LV_status.setAdapter(simpleAdapter);

    }
}
