package com.example.levlog;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private LevViewPrinter levViewPrinter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化悬浮log窗口
        levViewPrinter = new LevViewPrinter(this);
        levViewPrinter.getViewProvider().showFloatingView();;
        LevLogManager.getInstance().addPrinter(levViewPrinter);

        findViewById(R.id.btn_log).setOnClickListener(view -> {
            printLog();
        });
    }

    private void printLog(){
        LevLog.log(new LevLogConfig() {
            @Override
            public boolean includeThread() {
                return true;
            }

            @Override
            public int stackTraceDepth() {
                return 0;
            }
        },LevLogType.E,"------","main");

        LevLog.a("printLog");
    }
}