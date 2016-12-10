package com.ai.heuristic;

import com.ai.model.GameState;

/**
 * Created by z on 12/10/16.
 */
public class LosingStateHeuristic implements Heuristic {
    @Override
    public double evaluate(GameState gameState) {
        if (gameState.isLost()) {
            return -100000.00;
        } else {
            return 0;
        }
    }

    @Override
    public double getWeight() {
        return 1.0;
    }
}
