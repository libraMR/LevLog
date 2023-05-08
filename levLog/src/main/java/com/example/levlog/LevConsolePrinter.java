package com.example.levlog;


import static com.example.levlog.LevLogConfig.MAX_LEN;

import android.util.Log;

import androidx.annotation.Nullable;

public class LevConsolePrinter implements LevLogPrinter {
    @Override
    public void print(@Nullable LevLogConfig config, int level, String tag, @Nullable String printString) {
        int len = printString.length();//总字数
        int countOfSub = len / MAX_LEN;//总字数／每行最多打印字数 = 行数
        if (countOfSub > 0) {//如果行数大于0
            int index = 0;//记录当前打印的总字数
            for (int i = 0; i < countOfSub; i++) {//遍历每行，并打印出来
                Log.println(level, tag, printString.substring(index, index + MAX_LEN));
                index += MAX_LEN;//每次打印完，index加上每次每行打印的字数=当前打印的总字数
            }

            //如果打印类countOfSub之后，index的长度还是不等于总长度，就要把剩下的打印出来
            if (index != len) {
                Log.println(level, tag, printString.substring(index, len));
            }
        }else {
            Log.println(level,tag,printString);
        }
    }
}