package com.ai.heuristic;

import com.ai.model.GameState;

/**
 * Created by Sylvie on 12/3/16.
 */
public class LargestNumberAtCorner implements Heuristic {
    @Override
    public double evaluate(GameState gameState) {
        int score = 0;
        int max = gameState.tileValues[0][0];
        int x = 0;
        int y = 0;
        int gameStateMax = gameState.getMax();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if(gameState.tileValues[i][j] > max){
                    x = i;
                    y = j;
                    max = gameState.tileValues[i][j];
                }
//                boolean isCorner = isCorner(i, j);
//                boolean isColumnMax = gameState.isColumnMax(i, j);
//                boolean isRowMax = gameState.isRowMax(i, j);
//                boolean isEdge = isEdge(i, j);
//                if (isCorner && gameState.tileValues[i][j] == gameStateMax) {
//                    score += 10;
//                } else if (isCorner && (isColumnMax || isRowMax)) {
//                    score += 3;
//                } else if (isEdge && (isColumnMax || isRowMax)) {
//                    score += 2;
//                }
//                if (!isCorner && gameState.tileValues[i][j] == gameStateMax) {
//                    score -= 20;
//                }
//                if (!isCorner && !isEdge && gameState.tileValues[i][j] == gameStateMax) {
//                    score -= 50;
//                }
                if(gameState.tileValues[i][j] > max){
                    x = i;
                    y = j;
                    max = gameState.tileValues[i][j];
                }

                if(gameState.tileValues[i][j] == 64){
                    score += 3;
                }
                if(gameState.tileValues[i][j] == 128){
                    score += 7;
                }
                if(gameState.tileValues[i][j] == 256){
                    score += 15;
                }
                if(gameState.tileValues[i][j] == 512){
                    score += 31;
                }
                if(gameState.tileValues[i][j] == 1024){
                    score += 63;
                }
                if(gameState.tileValues[i][j] == 2048) {
                    score += 127;
                }
            }
        }

        if (x == 0 && y == 0) {
            if (gameState.tileValues[x + 1][y] == max / 2 || gameState.tileValues[x][y + 1] == max / 2) {
                score += 2;
            }
        } else if ((x == 0 && y == 1) || (x == 0 && y == 2)) {
            if (gameState.tileValues[x + 1][y] == max / 2 || gameState.tileValues[x][y + 1] == max / 2 || gameState.tileValues[x][y - 1] == max / 2) {
                score += 2;
            }
        } else if (x == 0 && y == 3) {
            if (gameState.tileValues[x + 1][y] == max / 2 || gameState.tileValues[x][y - 1] == max / 2) {
                score += 2;
            }
        } else if ((x == 1 && y == 0) || (x == 2 && y == 0)) {
            if (gameState.tileValues[x - 1][y] == max / 2 || gameState.tileValues[x][y + 1] == max / 2 || gameState.tileValues[x + 1][y] == max / 2) {
                score += 2;
            }
        } else if (x == 3 && y == 0) {
            if (gameState.tileValues[x - 1][y] == max / 2 || gameState.tileValues[x][y + 1] == max / 2) {
                score += 2;
            }
        } else if ((x == 3 && y == 1) || (x == 3 && y == 2)) {
            if (gameState.tileValues[x][y - 1] == max / 2 || gameState.tileValues[x][y + 1] == max / 2 || gameState.tileValues[x - 1][y] == max / 2) {
                score += 2;
            }
        } else if (x == 3 && y == 3) {
            if (gameState.tileValues[x - 1][y] == max / 2 || gameState.tileValues[x][y - 1] == max / 2) {
                score += 2;
            }
        } else if ((x == 1 && y == 3) || (x == 2 && y == 3)) {
            if (gameState.tileValues[x + 1][y] == max / 2 || gameState.tileValues[x][y - 1] == max / 2 || gameState.tileValues[x - 1][y] == max / 2) {
                score += 2;
            }
        } else if (gameState.tileValues[x + 1][y] == max / 2 || gameState.tileValues[x][y - 1] == max / 2 || gameState.tileValues[x - 1][y] == max / 2 || gameState.tileValues[x][y + 1] == max / 2) {
            score += 2;
        }
//        if((x == 0 && y == 0) || (x == 0 && y == 3) || (x == 3 && y == 0) || (x == 3 && y == 3)){
//            score ++;
//        }

        return score;
    }

//    @Override
//    public double evaluate(GameState gameState) {
//        int maxRow = 0;
//        int maxCol = 0;
//        int maxNum = 0;
//        for (int i = 0; i < gameState.tileValues.length; i++) {
//            for (int j = 0; j < gameState.tileValues[i].length; j++) {
//                if (gameState.tileValues[i][j] > maxNum || gameState.tileValues[i][j] == maxNum && isCorner(i, j)){
//                    maxRow = i;
//                    maxCol = j;
//                    maxNum = gameState.tileValues[i][j];
//                }
//            }
//        }
//        int[] maxLocations = new int[]{maxRow, maxCol, maxNum};
//        double factor = (Math.log(maxLocations[2]) / Math.log(2));
//        if (isCorner(maxLocations[0], maxLocations[1])) {
//            return factor;
//        } else {
//            return -1.00 * factor;
//        }
//    }

    @Override
    public double getWeight() {
        return 1.0;
    }

    boolean isCorner(int x, int y) {
        return x == 0 && y == 0 || x == 0 && y == 3 || x == 3 && y == 3 || x == 3 && y == 0;
    }

    boolean isEdge(int x, int y) {
        return x == 0 && y != 0 && y != 3
                || x == 3 && y != 0 && y != 3
                || y == 0 && x != 0 && x != 3
                || y == 3 && x != 0 && x != 3;
    }

}