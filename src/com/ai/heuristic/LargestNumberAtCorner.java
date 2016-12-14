package com.ai.heuristic;

import com.ai.model.GameState;
import com.util.Utility;

/**
 * Created by Sylvie on 12/3/16.
 */
public class LargestNumberAtCorner implements Heuristic {
    @Override
    public double evaluate(GameState gameState) {
        double score = 0;
        int max = gameState.tileValues[0][0];
        int x = 0;
        int y = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (gameState.tileValues[i][j] > max || Utility.isCornerPosition(i, j) || !Utility.isCornerPosition(x,y) && Utility.isEdgePosition(i,j)) {
                    x = i;
                    y = j;
                    max = gameState.tileValues[i][j];
                }
                if (gameState.isGlobalMax(i,j)) {
                    if (Utility.isCornerPosition(i,j)) {
                        score += (Math.log(gameState.getMax()));
                    } else if (Utility.isEdgePosition(i,j)) {
                        score += Math.log(gameState.getMax())/3;
                    } else {
                        score -= Math.log(gameState.getMax());
                    }
                } else if (gameState.isRowMax(i,j) || gameState.isColumnMax(i,j)) {
                    if (Utility.isCornerPosition(i,j)) {
                        score += (Math.log(gameState.getMax()))/3;
                    } else if (Utility.isEdgePosition(i,j)) {
                        score += (Math.log(gameState.getMax()))/5;
                    } else {
                        score -= (Math.log(gameState.getMax()))/3;
                    }
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
        return score;
    }

    @Override
    public double getWeight() {
        return 2.5;
    }
}
