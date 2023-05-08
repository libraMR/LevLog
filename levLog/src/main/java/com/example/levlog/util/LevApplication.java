package com.example.levlog.util;

import android.app.Application;

import java.lang.reflect.InvocationTargetException;

public class LevApplication {
    private Application mApplication = null;
    private LevApplication(){

    }

    private static class LevApplicationHolder{
        private static final LevApplication INSTANCE = new LevApplication();
    }

    public static LevApplication getLevApplication(){
        return LevApplicationHolder.INSTANCE;
    }


    public Application getApplication(){
        if(mApplication == null){
            try {
                mApplication = (Application) Class.forName("android.app.ActivityThread")
                        .getMethod("currentApplication")
                        .invoke(null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return mApplication;
    }
}
