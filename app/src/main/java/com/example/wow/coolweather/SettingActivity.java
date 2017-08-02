package com.example.wow.coolweather;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;

import com.example.wow.coolweather.service.MyService;
import com.github.glomadrian.materialanimatedswitch.MaterialAnimatedSwitch;

public class SettingActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        MaterialAnimatedSwitch mSwitch;
        mSwitch = (MaterialAnimatedSwitch) findViewById(R.id.pin);
        mSwitch.setOnCheckedChangeListener(new MaterialAnimatedSwitch.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(boolean isChecked) {
                if (isChecked){
                    Intent intent = new Intent(SettingActivity.this, MyService.class);
                    startService(intent);
                }else {
                    Intent intent = new Intent(SettingActivity.this, MyService.class);
                    stopService(intent);
                }
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            moveTaskToBack(true);
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("SettingActivity.this","onDestroy");
    }
}
