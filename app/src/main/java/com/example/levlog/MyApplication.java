package com.example.levlog;

import android.app.Application;

import com.google.gson.Gson;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        LevLogManager.init(new LevLogConfig() {
            @Override
            public JsonParser injectJsonParser() {
                return src -> new Gson().toJson(src);
            }

            @Override
            public String getGlobalTag() {
                return "MyApplication";
            }

            @Override
            public boolean enable() {
                return true;
            }

            @Override
            public boolean includeThread() {
                return super.includeThread();
            }

            @Override
            public int stackTraceDepth() {
                return super.stackTraceDepth();
            }

            @Override
            public LevLogPrinter[] printers() {
                return super.printers();
            }
        }, new LevConsolePrinter(), LevFilePrinter.getInstance(getApplicationContext().getCacheDir().getAbsolutePath(),0));
    }
}
