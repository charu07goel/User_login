package com.example.charugoel.application;

import android.provider.Settings;

import javax.microedition.khronos.opengles.GL;

/**
 * Created by Charu Goel on 20-06-2018.
 */

public class Global {

    private static Global instance;
    private static String uname;

    public void set(String s){
        Global.uname = s;
    }
    public String get(){
        return Global.uname;
    }

    public static synchronized Global getInstance(){
        if (instance == null)
            instance = new Global();
        return instance;
    }
}
