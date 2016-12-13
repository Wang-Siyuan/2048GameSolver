package com.util;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by z on 12/12/16.
 */
public class Log {
    public static void log(String string) {
        Logger.getLogger(Log.class.getName()).log(Level.INFO, string);
    }
}
