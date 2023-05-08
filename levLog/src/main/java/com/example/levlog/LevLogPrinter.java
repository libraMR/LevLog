package com.example.levlog;

import androidx.annotation.Nullable;

public interface LevLogPrinter {
    void print(@Nullable LevLogConfig config,int level,String tag,@Nullable String printString);
}