package com.example.josephmolina.getstarted;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;

import java.sql.Time;
import java.util.Calendar;

import butterknife.BindView;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.timePicker) TimePicker timePicker;
    @BindView(R.id.savedAlarmText) TextView alarmTime;
    @BindView(R.id.AlarmSwitch) Switch simpleSwitch;
    private Calendar calendar;
    private String format = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        simpleSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    simpleSwitch.setText(simpleSwitch.getTextOn());
                }

                if(!isChecked){
                    simpleSwitch.setText(simpleSwitch.getTextOff());
                }
            }
        });
        calendar = Calendar.getInstance();
    }

    public void setTime(View view){
        if(Build.VERSION.SDK_INT >= 23){
            int hour  = timePicker.getHour();
            int min = timePicker.getMinute();
            showTime(hour,min);

        }else{
            int hour = timePicker.getCurrentHour();
            int min = timePicker.getCurrentMinute();
            showTime(hour,min);
        }
    }

    private void showTime(int hour, int min) {
        if(hour == 0){
            hour += 12;
            format = "AM";
        }else if(hour == 12){
            format = "PM";
        }else if(hour > 12){
            hour -= 12;
            format = "PM";
        }else{
            format = "AM";
        }
        alarmTime.setText(new StringBuilder().append(hour).append(":").append(min)
                .append(" ").append(format));
    }
}
