package com.xiaoma.designstudy;

import android.content.Context;
import android.content.SharedPreferences;

import com.xiaoma.hometest.BaseApp;

/**
 * Created by xiaoma on 2017/5/17.
 */

public class PreferenceUtil {

    private String SP_NAME = "xiaoma";
    private SharedPreferences.Editor edit;
    private SharedPreferences sp;

    private PreferenceUtil() {
        sp = BaseApp.getContext().getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        edit = sp.edit();
    }

    private static PreferenceUtil mPreferenceUtils;

    public static PreferenceUtil getInstance() {
        if (mPreferenceUtils == null) {
            synchronized (PreferenceUtil.class) {
                if (mPreferenceUtils == null) {
                    mPreferenceUtils = new PreferenceUtil();
                }
            }
        }
        return mPreferenceUtils;
    }

    public String getToken(String key) {
        return sp.getString("token", null);
    }

    public void savaToken(String token) {
        edit.putString("token", token).commit();
    }

    public void clearToken() {
        edit.putString("token", null).commit();
    }
}
