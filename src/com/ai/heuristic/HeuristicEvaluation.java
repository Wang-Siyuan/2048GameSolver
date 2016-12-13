package com.ai.heuristic;

import com.ai.model.GameState;

/**
 * Created by z on 12/12/16.
 */
public class HeuristicEvaluation {
    public GameState gameState;
    public Double score;

    public HeuristicEvaluation(GameState gameState, Double score) {
        this.gameState = gameState;
        this.score = score;
    }
}
