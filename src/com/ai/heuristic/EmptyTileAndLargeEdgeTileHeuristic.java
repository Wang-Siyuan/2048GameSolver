package com.ai.heuristic;

import com.ai.model.GameState;
import com.util.Utility;

/**
 * Created by z on 12/2/16.
 */
public class EmptyTileAndLargeEdgeTileHeuristic implements Heuristic {
    @Override
    public int evaluate(GameState gameState) {
        int score = 0;
        int gameStateAverageValue = gameState.getAverageTileValue()/16;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (gameState.tileValues[i][j] > gameStateAverageValue && Utility.isEdgePosition(i, j)
                        || gameState.tileValues[i][j] == 0) {
                    score++;
                } else if (gameState.tileValues[i][j] < gameStateAverageValue && Utility.isEdgePosition(i,j)) {
                    score--;
                }
            }
        }
        return score;
    }
}
