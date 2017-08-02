package com.example.wow.coolweather;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.example.wow.coolweather.service.MyService;
import com.github.glomadrian.materialanimatedswitch.MaterialAnimatedSwitch;

public class SettingActivity extends AppCompatActivity {

    private Switch mSwitch;
    private boolean mChecked;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        mSwitch = (Switch) findViewById(R.id.pin);
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(SettingActivity.this);
        boolean isCheckedBoolean = prefs.getBoolean("isChecked",false);
        if (isCheckedBoolean != false){
            mSwitch.setChecked(isCheckedBoolean);
        }
        mSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    Intent intent = new Intent(SettingActivity.this, MyService.class);
                    startService(intent);
                    mChecked = isChecked;
                    Save(mChecked);
                }else {
                    Intent intent = new Intent(SettingActivity.this, MyService.class);
                    stopService(intent);
                    mChecked = isChecked;
                    Save(mChecked);
                }
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
