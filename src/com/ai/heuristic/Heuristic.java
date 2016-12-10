package com.ai.heuristic;

import com.ai.model.GameState;

/**
 * Created by z on 12/2/16.
 */

/**
 * Each hueristic should have a maximum value of approximate 160(10 per each tile, 6.67 per each pair of tiles)
 */
public interface Heuristic {
    double evaluate(GameState gameState);
    double getWeight();
}
