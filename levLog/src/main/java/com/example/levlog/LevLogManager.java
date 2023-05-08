package com.example.levlog;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LevLogManager {
    private LevLogConfig config;
    private static LevLogManager instance;
    private List<LevLogPrinter> printers = new ArrayList<>();//保存打印器

    private LevLogManager(LevLogConfig config,LevLogPrinter[] printers){
        this.config = config;
        this.printers.addAll(Arrays.asList(printers));
    }

    public static LevLogManager getInstance(){
        return instance;
    }

    public static void init(@Nullable LevLogConfig config,LevLogPrinter... printers){
        instance = new LevLogManager(config,printers);
    }

    public LevLogConfig getConfig(){
        return config;
    }

    public List<LevLogPrinter> getPrinters(){
        return printers;
    }

    public void addPrinter(LevLogPrinter printer){
        printers.add(printer);
    }

    public void removePrinter(LevLogPrinter printer){
        if(printers != null){
            printers.remove(printer);
        }
    }



}
