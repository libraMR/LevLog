package com.example.levlog;

public abstract class LevLogConfig {
    static int MAX_LEN = 512;
    static LevStackTraceFormatter HI_STACK_TRACE_FORMATTER = new LevStackTraceFormatter();
    static LevThreadFormatter HI_THREAD_FORMATTER = new LevThreadFormatter();

    public JsonParser injectJsonParser() {
        return null;
    }

    public String getGlobalTag() {
        return "LevLog";
    }

    public boolean enable() {
        return true;
    }

    public boolean includeThread() {
        return false;
    }

    public int stackTraceDepth() {
        return 5;
    }

    public LevLogPrinter[] printers() {
        return null;
    }

    public interface JsonParser {
        String toJson(Object src);
    }
}
