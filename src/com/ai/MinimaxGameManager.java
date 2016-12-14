package com.ai;

import com.ai.heuristic.*;
import com.ai.model.*;

import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by z on 12/2/16.
 */
public class MinimaxGameManager {
    GameStateManager gameStateManager = new GameStateManager();
    HeuristicEvaluator heuristicEvaluator = new HeuristicEvaluator();
    Heuristic[] heuristics = new Heuristic[]{new EmptyTileAndLargeEdgeTileHeuristic(),
            new LargestNumberAtCorner(), new MonotonicityHeuristic(), new SmoothnessHeuristic(),
            new LosingStateHeuristic()};

    public MinimaxGameManager() {
    }

    public Direction getNextBestMoveForUser(GameState currentGameState) {
        Map<GameState, Direction> allNextGameStateBySliding = gameStateManager
                .getAllNextGameStateBySliding(currentGameState);
        double maxHeuristicValue = -Double.MAX_VALUE;
        Direction minHeuristicDirection = null;
        for (GameState gameState : allNextGameStateBySliding.keySet()) {
            if (gameState.equals(currentGameState)) {
                continue;
            }
//            GameTree gameTree = new GameTree(gameState, 5, MinimaxLevelType.Max);
//            Log.getLogger(MinimaxGameManager.class.getName()).log(Level.INFO, "game tree ready");
//            GameTreeNode gameTreeNode = gameTree.root;
            int depth = 5;
            if (gameState.getZeros() <= 2) {
                depth = 9;
            } else if (gameState.getZeros() == 3) {
                depth = 8;
            } else if (gameState.getZeros() <= 5) {
                depth = 7;
            }
//            } else if (gameState.getZeros() <= 7) {
//                depth = 7;
//            } else if (gameState.getZeros() < 11) {
//                depth = 6;
//            }
            double heuristicVal = heuristicEvaluator.evaluate(heuristics, gameState,
                    depth, MinimaxLevelType.Min, -1 * Double.MAX_VALUE, Double.MAX_VALUE);
//            Log.getLogger(MinimaxGameManager.class.getName()).log(Level.INFO, Integer.toString(heuristicVal));
            if (heuristicVal > maxHeuristicValue || minHeuristicDirection == null) {
                minHeuristicDirection = allNextGameStateBySliding.get(gameState);
                maxHeuristicValue = heuristicVal;
            }
        }
        return minHeuristicDirection;
    }
}
