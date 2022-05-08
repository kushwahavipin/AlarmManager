package com.erhacker.alarmmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    static final int ALARM_CODE=100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText editText=findViewById(R.id.textView);
        Button button=findViewById(R.id.button);
        AlarmManager alarmManager=(AlarmManager) getSystemService(ALARM_SERVICE);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String time1=editText.getText().toString();
                if (!time1.isEmpty()){
                    int time=Integer.parseInt(time1);
                    long triggerTime=System.currentTimeMillis()+(time*1000);
                    Intent intent=new Intent(MainActivity.this,MyReceiver.class);
                    PendingIntent pendingIntent=PendingIntent.getBroadcast(MainActivity.this,ALARM_CODE,intent,PendingIntent.FLAG_IMMUTABLE);
                    alarmManager.set(AlarmManager.RTC_WAKEUP,triggerTime,pendingIntent);
                    Toast.makeText(MainActivity.this, "Save", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(MainActivity.this, "Please Enter Time in Sec", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}