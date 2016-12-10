package com.ai.heuristic;

import com.ai.GameStateManager;
import com.ai.model.Direction;
import com.ai.model.GameState;

import java.util.EnumSet;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by z on 12/8/16.
 */
public class MergeableTilesHeuristic implements Heuristic {
    GameStateManager gameStateManager = new GameStateManager();

    @Override
    public double evaluate(GameState gameState) {
        int score = 0;
        int numOfNonZeroTiles = gameState.getNumOfNonZeroTiles();
        for (Direction direction : EnumSet.allOf(Direction.class)) {
            GameState nextGameState = gameStateManager.slide(gameState, direction);
            score += (numOfNonZeroTiles - nextGameState.getNumOfNonZeroTiles());
        }
        return score;
    }

    @Override
    public double getWeight() {
        return 1.0;
    }
}
