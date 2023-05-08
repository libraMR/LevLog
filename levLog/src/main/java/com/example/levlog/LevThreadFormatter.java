package com.example.levlog;

public class LevThreadFormatter implements LevLogFormatter<Thread> {
    @Override
    public String format(Thread data) {
        return "Thread:" + data.getName();
    }
}