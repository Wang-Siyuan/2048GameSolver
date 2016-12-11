package com.ai.heuristic;

import com.ai.model.GameState;
import com.util.Utility;

/**
 * Created by z on 12/2/16.
 */
public class EmptyTileHeuristic implements Heuristic {
    @Override
    public double evaluate(GameState gameState) {
        int score = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if(gameState.tileValues[i][j] == 0){
                    score += 3;
                }
            }
        }
//        if (score < 5) {
//            score -= 500;
//        }
        return Math.pow(2, score);
    }

    @Override
    public double getWeight() {
        return 1.0;
    }
}
