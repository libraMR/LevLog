package com.example.levlog;

import androidx.annotation.Nullable;

import java.util.Arrays;
import java.util.List;

/*
 *
 *  Tips:
 *  1.打印堆栈信息
 *  2.File输出
 *  3.模拟控制台
 */
public class LevLog {
    private static final String HI_LOG_PACKAGE;

    static {
        String className = LevLog.class.getName();
        HI_LOG_PACKAGE = className.substring(0, className.lastIndexOf('.') + 1);
    }

    private static class StringBuildHolder {
        private static StringBuilder sb = new StringBuilder();
    }

    private static StringBuilder getStringBuilder() {
        return StringBuildHolder.sb;
    }

    private static class StringBuildParseHolder {
        private static StringBuilder sb = new StringBuilder();
    }

    private static StringBuilder getStringBuilderParse() {
        return StringBuildParseHolder.sb;
    }


    public static void v(Object... contents) {
        log(LevLogType.V, contents);
    }

    public static void vt(String tag, Object... contents) {
        log(LevLogType.V, tag, contents);
    }

    public static void d(Object... contents) {
        log(LevLogType.D, contents);
    }

    public static void dt(String tag, Object... contents) {
        log(LevLogType.D, tag, contents);
    }

    public static void i(Object... contents) {
        log(LevLogType.I, contents);
    }

    public static void it(String tag, Object... contents) {
        log(LevLogType.I, tag, contents);
    }

    public static void w(Object... contents) {
        log(LevLogType.W, contents);
    }

    public static void wt(String tag, Object... contents) {
        log(LevLogType.W, tag, contents);
    }

    public static void e(Object... contents) {
        log(LevLogType.E, contents);
    }

    public static void et(String tag, Object... contents) {
        log(LevLogType.E, tag, contents);
    }

    public static void a(Object... contents) {
        log(LevLogType.A, contents);
    }

    public static void at(String tag, Object... contents) {
        log(LevLogType.A, tag, contents);
    }


    public static void log(@LevLogType.TYPE int type, Object... contents) {
        log(type, LevLogManager.getInstance().getConfig().getGlobalTag(), contents);
    }

    public static void log(@LevLogType.TYPE int type, @Nullable String tag, Object... contents) {
        log(LevLogManager.getInstance().getConfig(), type, tag, contents);

    }

    public static void log(@Nullable LevLogConfig config, @LevLogType.TYPE int type, @Nullable String tag, Object... contents) {
        if (!config.enable()) {
            return;
        }


        if (config.includeThread()) {
            String threadInfo = LevLogConfig.HI_THREAD_FORMATTER.format(Thread.currentThread());
            getStringBuilder().append(threadInfo).append("\n");
        }

        if (config.stackTraceDepth() > 0) {
            String stackTrace = LevLogConfig.HI_STACK_TRACE_FORMATTER.format(LevStackTraceUtil.getCroppedRealStackTrace(new Throwable().getStackTrace(), HI_LOG_PACKAGE,config.stackTraceDepth()));
            getStringBuilder().append(stackTrace).append("\n");
        }

        String body = parseBody(contents, config);

        //body已经得到数据，清除getStringBuilderParse()数据，避免打印重复
        getStringBuilderParse().delete(0, getStringBuilder().length());
        if (body != null) {//替换转义字符\
            body = body.replace("\\\"", "\"");
        }
        getStringBuilder().append(body);

        //Log.println(type, tag, body);
        List<LevLogPrinter> printers = config.printers() != null ? Arrays.asList(config.printers()) : LevLogManager.getInstance().getPrinters();
        if (printers == null) {
            return;
        }

        //打印log
        for (LevLogPrinter printer : printers) {
            printer.print(config, type, tag, getStringBuilder().toString());
        }

        //打印完毕，清除getStringBuilder()数据，避免打印重复
        getStringBuilder().delete(0, getStringBuilder().length());

    }

    private static String parseBody(@Nullable Object[] contents, @Nullable LevLogConfig config) {

        if (config.injectJsonParser() != null) {
            return config.injectJsonParser().toJson(contents);
        }

        for (Object o : contents) {
            getStringBuilderParse().append(o.toString()).append(";");
        }

        if (getStringBuilderParse().length() > 0) {
            getStringBuilderParse().deleteCharAt(getStringBuilderParse().length() - 1);
        }
        return getStringBuilderParse().toString();
    }
}
