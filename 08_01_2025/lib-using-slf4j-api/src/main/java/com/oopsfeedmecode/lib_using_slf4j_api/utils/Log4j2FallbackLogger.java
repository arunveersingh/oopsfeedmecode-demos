package com.oopsfeedmecode.lib_using_slf4j_api.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public final class Log4j2FallbackLogger {

    private static final boolean log4jPresent;
    private static final Object log4jLogger;
    private static Method infoMethod;
    private static Method debugMethod;
    private static Method warnMethod;
    private static Method errorMethod;

    static {
        boolean found = false;
        Object loggerInstance = null;

        try {
            // Attempt to load Log4j2 classes via reflection
            Class<?> logManagerClass = Class.forName("org.apache.logging.log4j.LogManager");
            Class<?> loggerClass = Class.forName("org.apache.logging.log4j.Logger");

            // getLogger(String name)
            Method getLoggerMethod = logManagerClass.getMethod("getLogger", String.class);
            loggerInstance = getLoggerMethod.invoke(null, "EncryptionDecryptionUtil");

            // Cache reflection methods for logger.info/debug/warn/error
            infoMethod = loggerClass.getMethod("info", Object.class);
            debugMethod = loggerClass.getMethod("debug", Object.class);
            warnMethod = loggerClass.getMethod("warn", Object.class);
            errorMethod = loggerClass.getMethod("error", Object.class);

            found = true;
        } catch (ClassNotFoundException | NoSuchMethodException |
                 IllegalAccessException | InvocationTargetException e) {
            // Log4j2 not found or some reflection error - fallback
        }

        log4jPresent = found;
        log4jLogger = loggerInstance;
    }

    private Log4j2FallbackLogger() {
        // utility class
    }

    public static void info(String msg) {
        if (log4jPresent) {
            try {
                infoMethod.invoke(log4jLogger, msg);
            } catch (Exception e) {
                // fallback
                System.out.println("[INFO] " + msg);
            }
        } else {
            System.out.println("[INFO] " + msg);
        }
    }

    public static void debug(String msg) {
        if (log4jPresent) {
            try {
                debugMethod.invoke(log4jLogger, msg);
            } catch (Exception e) {
                System.out.println("[DEBUG] " + msg);
            }
        } else {
            System.out.println("[DEBUG] " + msg);
        }
    }

    public static void warn(String msg) {
        if (log4jPresent) {
            try {
                warnMethod.invoke(log4jLogger, msg);
            } catch (Exception e) {
                System.err.println("[WARN] " + msg);
            }
        } else {
            System.err.println("[WARN] " + msg);
        }
    }

    public static void error(String msg, Throwable t) {
        if (log4jPresent) {
            try {
                errorMethod.invoke(log4jLogger, msg, t);
            } catch (Exception e) {
                System.err.println("[ERROR] " + msg + " | Exception: " + t);
            }
        } else {
            System.err.println("[ERROR] " + msg + " | Exception: " + t);
        }
    }
}
