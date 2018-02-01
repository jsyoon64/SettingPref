package com.jsyoon.settingpref.Data;

import android.content.Context;
import android.content.SharedPreferences;
import android.databinding.Bindable;
import android.preference.PreferenceManager;
import android.databinding.BaseObservable;
import android.support.annotation.ColorInt;
import android.support.v4.content.ContextCompat;

import com.jsyoon.settingpref.R;

public class SettingData extends BaseObservable {
    Context context;

    private boolean setting1;
    private boolean setting2;
    private String textColor;
    private int textSize;

    @Bindable
    public boolean getSetting1() {
        return setting1;
    }
    @Bindable
    public boolean getSetting2() {
        return setting2;
    }
    @Bindable
    public String getTextColor() {
        return textColor;
    }
    @Bindable
    public String getTextSize() {
        return String.valueOf(textSize);
    }
    @Bindable
    public int getIntTextSize() {
        return textSize;
    }

    public void setSetting1(boolean val) {
        setting1 = val;
        notifyPropertyChanged(com.jsyoon.settingpref.BR.setting1);
    }
    public void setSetting2(boolean val) {
        setting2 = val;
        notifyPropertyChanged(com.jsyoon.settingpref.BR.setting2);
    }
    public void setTextColor(String str) {
        textColor = str;
        notifyPropertyChanged(com.jsyoon.settingpref.BR.textColor);
    }
    public void setTextSize(String str) {
        textSize = Integer.parseInt(str);
        notifyPropertyChanged(com.jsyoon.settingpref.BR.textSize);
    }
    public void setTextSize(int size) {
        textSize = size;
        notifyPropertyChanged(com.jsyoon.settingpref.BR.textSize);
    }

    public SettingData(Context context) {
        this.context = context;
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);

        setSetting1(sharedPreferences.getBoolean(context.getString(R.string.key_setting1), true));
        setSetting2(sharedPreferences.getBoolean(context.getString(R.string.key_setting2), true));
        setTextColor(sharedPreferences.getString(context.getString(R.string.key_text_color), context.getString(R.string.color_red)));
        setTextSize(Integer.parseInt(sharedPreferences.getString(context.getString(R.string.key_text_size), context.getString(R.string.text_size_default))));
    }

    public void update(SharedPreferences sharedPreferences, String key) {
        if (key.equals(context.getString(R.string.key_setting1))) {
            setSetting1(sharedPreferences.getBoolean(key, true));
        } else if (key.equals(context.getString(R.string.key_setting2))) {
            setSetting2(sharedPreferences.getBoolean(key, true));
        } else if (key.equals(context.getString(R.string.key_text_color))) {
            setTextColor(sharedPreferences.getString(key, context.getString(R.string.color_red)));
        } else if (key.equals(context.getString(R.string.key_text_size))) {
            setTextSize(Integer.parseInt(sharedPreferences.getString(
                    context.getString(R.string.key_text_size),
                    context.getString(R.string.text_size_default))));
        }
    }

    private int getColorIntFromColorString(String newColorKey) {

        @ColorInt
        int textColor;

        if (newColorKey.equals(context.getString(R.string.color_red))) {
            textColor = ContextCompat.getColor(context, R.color.myRed);
        } else if (newColorKey.equals(context.getString(R.string.color_blue))) {
            textColor = ContextCompat.getColor(context, R.color.myBlue);
        } else {
            textColor = ContextCompat.getColor(context, R.color.myGreen);
        }

        return textColor;
    }
}
