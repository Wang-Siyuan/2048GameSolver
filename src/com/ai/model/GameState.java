package com.ai.model;

import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by z on 12/2/16.
 */
public class GameState {
    public int[][] tileValues = new int[4][4];

    public GameState(GameState src) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                this.tileValues[i][j] = src.tileValues[i][j];
            }
        }
    }

    public GameState(String concatenatedTileValues) {
        String[] vals = concatenatedTileValues.split(" ");
        if (vals.length != 16) {
            throw new RuntimeException("Constructor parameter for Game State must have all 16 tile values");
        }
//        Logger.getLogger(GameState.class.getName()).log(Level.INFO, Arrays.toString(vals));
        for (int i = 0; i < vals.length; i++) {
            int x = i/4;
            int y = i%4;
            int value = Integer.parseInt(vals[i]);
//            Logger.getLogger(GameState.class.getName()).log(Level.INFO, x + "," + y + ":" + value);
            tileValues[x][y] = value;
        }
    }

    public double getAverageTileValue() {
        double sum = 0.0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                sum += this.tileValues[i][j];
            }
        }
        return sum/16;
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(tileValues);
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append('\n');
        for (int i = 0; i < tileValues.length; i++) {
            for (int j = 0; j < tileValues[0].length; j++) {
                stringBuilder.append(tileValues[i][j] + " ");
            }
            stringBuilder.append('\n');
        }

        return stringBuilder.toString();
    }

    @Override
    public boolean equals(Object o) {
        GameState anotherGameState = (GameState) o;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (this.tileValues[i][j] != anotherGameState.tileValues[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    public int getZeros() {
        int count = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (this.tileValues[i][j] == 0) {
                    count++;
                }
            }
        }
        return count;
    }
}
