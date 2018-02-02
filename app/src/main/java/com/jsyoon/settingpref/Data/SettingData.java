package com.jsyoon.settingpref.Data;

import android.content.Context;
import android.content.SharedPreferences;
import android.databinding.Bindable;
import android.preference.PreferenceManager;
import android.databinding.BaseObservable;
import android.support.annotation.ColorInt;
import android.support.v4.content.ContextCompat;

import com.jsyoon.settingpref.BR;
import com.jsyoon.settingpref.R;

public class SettingData extends BaseObservable {
    Context context;

    private boolean setting1;
    private boolean setting2;
    private String colorstring;
    private int textcolor;
    private int textsize;

    @Bindable
    public boolean getSetting1() {
        return setting1;
    }
    @Bindable
    public boolean getSetting2() {
        return setting2;
    }
    @Bindable
    public String getColorString() {
        return colorstring;
    }
    @Bindable
    public int getTextColor() {
        return textcolor;
    }
    @Bindable
    public int gettextSize() {
        return textsize;
    }

    public void setSetting1(boolean val) {
        setting1 = val;
        notifyPropertyChanged(BR.setting1);
    }
    public void setSetting2(boolean val) {
        setting2 = val;
        notifyPropertyChanged(BR.setting2);
    }
    public void setColorString(String col) {
        colorstring=col;
        notifyPropertyChanged(BR.colorString);
    }
    public void setTextColor(int color) {
        textcolor = color;
        notifyPropertyChanged(BR.textColor);
    }
    public void setTextSize(int size) {
        textsize = size;
        notifyPropertyChanged(BR.textSize);
    }

    public SettingData(Context context) {
        this.context = context;
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);

        setSetting1(sharedPreferences.getBoolean(context.getString(R.string.key_setting1), true));
        setSetting2(sharedPreferences.getBoolean(context.getString(R.string.key_setting2), true));
        String col = sharedPreferences.getString(context.getString(R.string.key_text_color), context.getString(R.string.color_red));
        setColorString(col);
        setTextColor(getColorIntFromColorString(col));
        setTextSize(Integer.parseInt(sharedPreferences.getString(
                context.getString(R.string.key_text_size),
                context.getString(R.string.text_size_default))));
    }

    public void update(SharedPreferences sharedPreferences, String key) {
        if (key.equals(context.getString(R.string.key_setting1))) {
            setSetting1(sharedPreferences.getBoolean(key, true));
        } else if (key.equals(context.getString(R.string.key_setting2))) {
            setSetting2(sharedPreferences.getBoolean(key, true));
        } else if (key.equals(context.getString(R.string.key_text_color))) {
            String col = sharedPreferences.getString(key, context.getString(R.string.color_red));
            setColorString(col);
            setTextColor(getColorIntFromColorString(col));
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
