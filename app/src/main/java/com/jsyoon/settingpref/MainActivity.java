package com.jsyoon.settingpref;

import android.content.Context;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.preference.PreferenceManager;
import android.support.annotation.ColorInt;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.jsyoon.settingpref.Data.SettingData;
import com.jsyoon.settingpref.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements SharedPreferences.OnSharedPreferenceChangeListener{

    SettingData data=new SettingData();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this,R.layout.activity_main);

        SetSettingVal(data);
        //
        // <data>
        // <variable name="user" type="com.jsyoon.bindexam1.User"/>
        //</data>
        // 위의 선언에 대한 generated setter class 이다
        binding.setDa(data);
    }

    void SetSettingVal(SettingData data1) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        data1.setting1 = sharedPreferences.getBoolean(getString(R.string.key_setting1),true);
        data1.setting2 = sharedPreferences.getBoolean(getString(R.string.key_setting2),true);
        data1.TextColor = sharedPreferences.getString(getString(R.string.key_text_color), getString(R.string.color_red));
        //textcol.setTextColor(ckey);
        data1.TextSize = Integer.parseInt(sharedPreferences.getString(getString(R.string.key_text_size), getString(R.string.text_size_default)));

        // Register the listener
        sharedPreferences.registerOnSharedPreferenceChangeListener(this);
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
        if (key.equals(getString(R.string.key_setting1))) {
            data.setting1 = sharedPreferences.getBoolean(key,true);
        } else if (key.equals(getString(R.string.key_setting2))) {
            data.setting1 = sharedPreferences.getBoolean(key,true);
        } else if (key.equals(getString(R.string.key_text_color))) {
            data.TextColor = sharedPreferences.getString(key, getString(R.string.color_red));
        } else if (key.equals(getString(R.string.key_text_size))) {
            data.TextSize = Integer.parseInt(sharedPreferences.getString(getString(R.string.key_text_size), getString(R.string.text_size_default)));
        }
    }

    private int getColorIntFromColorString(String newColorKey) {

        @ColorInt
        int textColor;

        if (newColorKey.equals(getString(R.string.color_red))) {
            textColor = ContextCompat.getColor(this, R.color.myRed);
        } else if (newColorKey.equals(getString(R.string.color_blue))) {
            textColor = ContextCompat.getColor(this, R.color.myBlue);
        } else {
            textColor = ContextCompat.getColor(this, R.color.myGreen);
        }

        return textColor;
    }
}