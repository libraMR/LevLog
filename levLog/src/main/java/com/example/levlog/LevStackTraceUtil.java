package com.example.levlog;

public class LevStackTraceUtil {
    /*
     * 获取最终真实的堆栈信息
     *
     */
    public static StackTraceElement[] getCroppedRealStackTrace(StackTraceElement[] stackTrace,String ignorePackage,int maxDepth){
        return cropStackTrace(getRealStackTrace(stackTrace,ignorePackage),maxDepth);
    }

    /*
    * 获取除忽略包名之外的堆栈信息
    *
    */
    private static StackTraceElement[] getRealStackTrace(StackTraceElement[] stackTrace,String ignorePackage){
        int ignoreDepth = 0;//默认的忽略长度初始值0
        int allDepth = stackTrace.length;//获取堆栈信息的长度
        String className;//获取className
        for (int i = allDepth -1;i >= 0;i--){
            className = stackTrace[i].getClassName();
            if(ignorePackage !=null && className.startsWith(ignorePackage)){
                ignoreDepth = i+1;
                break;
            }
        }

        int realDepth = allDepth - ignoreDepth;
        StackTraceElement[] realStack = new StackTraceElement[realDepth];
        System.arraycopy(stackTrace,ignoreDepth,realStack,0,realDepth);
        return realStack;

    }


    /*
    * 裁剪堆栈信息
    *
    */
    private static StackTraceElement[] cropStackTrace(StackTraceElement[] callStack,int maxDepth){
        int realDepth = callStack.length;
        if(maxDepth  > 0){
            realDepth = Math.min(maxDepth,realDepth);
        }

        StackTraceElement[] realStack = new StackTraceElement[realDepth];
        System.arraycopy(callStack,0,realStack,0,realDepth);
        return realStack;
    }
}