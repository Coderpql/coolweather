package com.example.wow.coolweather;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.Toast;

import com.example.wow.coolweather.service.MyService;
import com.github.glomadrian.materialanimatedswitch.MaterialAnimatedSwitch;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SettingActivity extends AppCompatActivity {

    private Switch mSwitch;

    private boolean mChecked;

    private EditText timeText;

    private Button okButton;

    private LinearLayout autoLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        mSwitch = (Switch) findViewById(R.id.pin);
        timeText = (EditText) findViewById(R.id.update_edit);
        okButton = (Button) findViewById(R.id.ok);
        timeText = (EditText) findViewById(R.id.update_edit);
        autoLayout = (LinearLayout) findViewById(R.id.auto_layout);
        autoLayout.setVisibility(View.INVISIBLE);
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(SettingActivity.this);
        boolean isCheckedBoolean = prefs.getBoolean("isChecked",false);
        if (isCheckedBoolean != false){
            mSwitch.setChecked(isCheckedBoolean);
            autoLayout.setVisibility(View.VISIBLE);
            Long anHour = prefs.getLong("updateTime",0);
            String prefsTime = anHour.toString();
            timeText.setText(prefsTime);
        }
        mSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    autoLayout.setVisibility(View.VISIBLE);
                    Intent intent = new Intent(SettingActivity.this, MyService.class);
                    startService(intent);
                    mChecked = isChecked;
                    Save(mChecked);
                }else {
                    autoLayout.setVisibility(View.INVISIBLE);
                    Intent intent = new Intent(SettingActivity.this, MyService.class);
                    stopService(intent);
                    mChecked = isChecked;
                    Save(mChecked);
                }
            }
        });
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Long updateTime = Long.parseLong(timeText.getText().toString());
                Log.d("Time",updateTime.toString());
                SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(SettingActivity.this).edit();
                editor.putLong("updateTime", updateTime);
                editor.apply();
                Intent intent = new Intent(SettingActivity.this, MyService.class);
                stopService(intent);
                startService(intent);
                Toast.makeText(SettingActivity.this,"设置成功",Toast.LENGTH_LONG).show();
            }
        });
    }

    public void Save(boolean mChecked){
        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(SettingActivity.this).edit();
        editor.putBoolean("isChecked",mChecked);
        editor.apply();
    }

    /*@Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            moveTaskToBack(true);
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }*/

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("SettingActivity.this","onDestroy");
    }
}
