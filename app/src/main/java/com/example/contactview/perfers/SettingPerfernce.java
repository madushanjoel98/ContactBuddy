package com.example.contactview.perfers;
import android.content.Context;
import android.content.SharedPreferences;
public class SettingPerfernce {
    private static final String SHARED_PREFERENCES_NAME = "settingcontact";
    private static final String AUTH_KEY = "sAuth";
    private static final String IS_BIO = "isbio";
    private final SharedPreferences sharedPref;
    private static SettingPerfernce instance;

    public static SettingPerfernce getInstance(Context context) {
        if (instance == null) {
            instance = new SettingPerfernce(context);
        }
        return instance;
    }

    public SettingPerfernce(Context context) {
        this.sharedPref = context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
    }

    public void setAuthSetting(boolean isAuth) {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean(AUTH_KEY, isAuth);
        editor.apply(); // Apply the changes
    }

    public boolean getAuthSetting() {
        return sharedPref.getBoolean(AUTH_KEY, false);
    }

    public boolean isBioSupport() {
        return sharedPref.getBoolean(IS_BIO, false);
    }

    public void setBioSupport(boolean isSupport) {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean(IS_BIO, isSupport);
        editor.apply(); // Apply the changes
    }
}
