package com.ai.heuristic;

import com.ai.model.GameState;
import com.util.Utility;

/**
 * Created by z on 12/2/16.
 */
public class EmptyTileAndLargeEdgeTileHeuristic implements Heuristic {
    @Override
    public double evaluate(GameState gameState) {
        double score = 0;
        double avgVal = gameState.getAverageTileValue();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
//                if (gameState.tileValues[i][j] > avgVal && Utility.isEdgePosition(i, j)) {
//                    score += gameState.tileValues[i][j] / avgVal;
//                } else
                if (gameState.tileValues[i][j] == 0) {
                    score += 1;
                }
            }
        }
        return Math.log(score);
    }

    @Override
    public double getWeight() {
        return 10.0;
    }
}
