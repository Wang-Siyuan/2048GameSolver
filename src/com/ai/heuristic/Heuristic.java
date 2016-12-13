package com.ai.heuristic;

import com.ai.model.GameState;

/**
 * Created by z on 12/2/16.
 */
public interface Heuristic {
    double evaluate(GameState gameState);
    double getWeight();
}
