package com.util;

/**
 * Created by z on 12/2/16.
 */
public class Utility {
    public static boolean isEdgePosition(int x, int y) {
        return x == 0 || x == 3 || y == 0 || y == 3;
    }

    public static boolean isCornerPosition(int x, int y) {
        return x == 0 && y == 0 || x == 0 && y == 3 || x == 3 && y == 0 || x == 3 && y == 3;
    }
}
