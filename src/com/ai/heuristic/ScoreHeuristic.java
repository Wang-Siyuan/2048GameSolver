package com.ai.heuristic;

import com.ai.model.GameState;

/**
 * Created by z on 12/12/16.
 */
public class ScoreHeuristic implements Heuristic {
    @Override
    public double evaluate(GameState gameState) {
        double val = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (gameState.tileValues[i][j] != 0) {
                    val += Math.log(gameState.tileValues[i][j]) / Math.log(2);
                }
            }
        }
        return val;
    }

    @Override
    public double getWeight() {
        return 0.1;
    }
}
