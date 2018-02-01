package com.jsyoon.settingpref;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.preference.PreferenceManager;
import android.support.annotation.ColorInt;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.jsyoon.settingpref.Data.SettingData;
import com.jsyoon.settingpref.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements SharedPreferences.OnSharedPreferenceChangeListener {

    SettingData data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        data = new SettingData(this);

        //
        // <data>
        // <variable name="user" type="com.jsyoon.bindexam1.User"/>
        //</data>
        // 위의 선언에 대한 generated setter class 이다
        binding.setDa(data);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        // Register the listener
        sharedPreferences.registerOnSharedPreferenceChangeListener(this);

        binding.option.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View arg0) {
                Intent i = new Intent(MainActivity.this, SettingActivity.class);
                startActivity(i);
            }
        });
    }

    // Deregister the listener
    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Unregister VisualizerActivity as an OnPreferenceChangedListener to avoid any memory leaks.
        PreferenceManager.getDefaultSharedPreferences(this)
                .unregisterOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        data.update(sharedPreferences, key);
    }
}