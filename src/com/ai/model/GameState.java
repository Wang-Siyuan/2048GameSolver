package com.ai.model;

import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by z on 12/2/16.
 */
public class GameState {
    public int[][] tileValues = new int[4][4];
    public int[] columnMax = null;
    public int[] rowMax = null;
    public Integer globalMax = null;

    public GameState(GameState src) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                this.tileValues[i][j] = src.tileValues[i][j];
            }
        }
    }

    public void populateMaxValues() {
        if (columnMax == null || rowMax == null || globalMax == null) {
            columnMax = new int[4];
            rowMax = new int[4];
            globalMax = 0;
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    int tileValue = this.tileValues[i][j];
                    if (tileValue > columnMax[j]) {
                        columnMax[j] = tileValue;
                    }
                    if (tileValue > rowMax[i]) {
                        rowMax[i] = tileValue;
                    }
                    if (tileValue > globalMax) {
                        globalMax = tileValue;
                    }
                }
            }
        }
    }

    public boolean isColumnMax(int i, int j) {
        populateMaxValues();
        return this.tileValues[i][j] == columnMax[j];
    }

    public boolean isRowMax(int i, int j) {
        populateMaxValues();
        return this.tileValues[i][j] == rowMax[i];
    }

    public boolean isGlobalMax(int i, int j) {
        populateMaxValues();
        return this.tileValues[i][j] == globalMax;
    }

    public GameState(String concatenatedTileValues) {
        String[] vals = concatenatedTileValues.split(" ");
        if (vals.length != 16) {
            throw new RuntimeException("Constructor parameter for Game State must have all 16 tile values");
        }
//        Log.getLogger(GameState.class.getName()).log(Level.INFO, Arrays.toString(vals));
        for (int i = 0; i < vals.length; i++) {
            int x = i / 4;
            int y = i % 4;
            int value = Integer.parseInt(vals[i]);
//            Log.getLogger(GameState.class.getName()).log(Level.INFO, x + "," + y + ":" + value);
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
        return sum / 16;
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

    public boolean isLost() {
        int counter = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (tileValues[i][j] == 0) {
                    counter++;
                }
            }
        }
        return counter < 3;
    }

    public int getMax() {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (tileValues[i][j] > max) {
                    max = tileValues[i][j];
                }
            }
        }
        return max;
    }

    public double getMonoticity() {
        // scores for all four directions
        double[] totals = new double[4];
        for (int i = 0; i < 4; i++) {
            int current = 0;
            while (current < 3 && this.tileValues[i][current] == 0) {
                current++;
            }
            int next = current + 1;
            while (next < 4) {
                while (next < 4 && this.tileValues[i][next] == 0) {
                    next++;
                }
                if (next >= 4) {
                    break;
                }
                double currentValue = this.tileValues[i][current] != 0 ? Math.log(this.tileValues[i][current]) / Math.log(2) : 0;
                double nextValue = this.tileValues[i][next] != 0 ? Math.log(this.tileValues[i][next]) / Math.log(2) : 0;
                if (currentValue > nextValue) {
                    totals[0] +=  nextValue - currentValue;
                }
                if (nextValue > currentValue) {
                    totals[1] += currentValue - nextValue;
                }
                current = next;
                next++;
            }
        }

        for (int j = 0; j < 4; j++) {
            int current = 0;
            while (current < 3 && this.tileValues[current][j] == 0) {
                current++;
            }
            int next = current + 1;
            while (next < 4) {
                while (next < 4 && this.tileValues[next][j] == 0) {
                    next++;
                }
                if (next >= 4) {
                    break;
                }
                double currentValue = this.tileValues[current][j] != 0 ? Math.log(this.tileValues[current][j]) / Math.log(2) : 0;
                double nextValue = this.tileValues[next][j] != 0 ? Math.log(this.tileValues[next][j]) / Math.log(2) : 0;
                if (currentValue > nextValue) {
                    totals[2] += nextValue - currentValue;
                }
                if (nextValue > currentValue) {
                    totals[3] += currentValue - nextValue;
                }
                current = next;
                next++;
            }
        }

        return Math.max(totals[0], totals[1]) + Math.max(totals[2], totals[3]);
    }
}
