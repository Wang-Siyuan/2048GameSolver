package com.ai.model;

import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by z on 12/2/16.
 */
public class Grid {
    int[][] tileValues = new int[4][4];

    public Grid(String concatenatedTileValues) {
        String[] vals = concatenatedTileValues.split(" ");
        Logger.getLogger(Grid.class.getName()).log(Level.INFO, Arrays.toString(vals));
        for (int i = 0; i < vals.length; i++) {
            int x = i/4;
            int y = i%4;
            int value = Integer.parseInt(vals[i]);
            Logger.getLogger(Grid.class.getName()).log(Level.INFO, x + "," + y + ":" + value);
            tileValues[x][y] = value;
        }
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < tileValues.length; i++) {
            for (int j = 0; j < tileValues[0].length; j++) {
                stringBuilder.append(tileValues[i][j] + " ");
            }
            stringBuilder.append('\n');
        }
        return stringBuilder.toString();
    }
}
