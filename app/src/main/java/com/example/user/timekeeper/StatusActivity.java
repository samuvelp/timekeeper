package com.example.user.timekeeper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

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
        HomeActivity homeActivity = new HomeActivity();
        ListView LV_status = (ListView) findViewById(R.id.UI_LV_status);
        ArrayList<HashMap<String,String>> attendanceList = new ArrayList<>();
        for(int i=0;i<homeActivity.checkIns.size();i++){
            HashMap<String,String> mapAttendance = new HashMap<>();
            mapAttendance.put("mapCheckIns",homeActivity.checkIns.get(i));
            mapAttendance.put("mapCheckOuts",homeActivity.checkOuts.get(i));
            attendanceList.add(mapAttendance);
        }
        String[] from = {"mapCheckIns","mapCheckOuts"};
        int[] to = {R.id.UI_TV_checkIn,R.id.UI_TV_checkOut};
        SimpleAdapter simpleAdapter = new SimpleAdapter(getApplication(),attendanceList,R.layout.singlelistview,from,to);
        LV_status.setAdapter(simpleAdapter);
    }
}
