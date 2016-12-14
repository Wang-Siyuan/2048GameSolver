package com.ai.heuristic;

import com.ai.model.GameState;
import com.util.Utility;

/**
 * Created by z on 12/2/16.
 */
public class EmptyTilesHeuristic implements Heuristic {
    @Override
    public double evaluate(GameState gameState) {
        double score = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (gameState.tileValues[i][j] == 0) {
                    score += 1;
                }
            }
        }
        return Math.log(score);
    }

    @Override
    public double getWeight() {
        return 25.0;
    }
}
