package com.ai.heuristic;

import com.ai.model.GameState;

/**
 * Created by z on 12/10/16.
 */
public class MaxValueHeuristic implements Heuristic {
    @Override
    public double evaluate(GameState gameState) {
        return (Math.log(gameState.getMax())/Math.log(2));
//        return gameState.getMax();
    }

    @Override
    public double getWeight() {
        return 1.0;
    }
}
