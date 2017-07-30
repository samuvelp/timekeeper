package com.example.user.timekeeper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<String> userIds =new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn_next =(Button) findViewById(R.id.UI_BTN_next);
        final EditText et_userId= (EditText) findViewById(R.id.UI_ET_userId);
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userId = et_userId.getText().toString();
                userIds.add(userId);
                Intent intent = new Intent(MainActivity.this,HomeActivity.class);
                if(!userId.isEmpty())
                startActivity(intent);
                else
                Toast.makeText(getApplication(),"Enter userID",Toast.LENGTH_SHORT).show();
            }
        });

    }
}
