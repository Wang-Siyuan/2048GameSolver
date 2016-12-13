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
    long searchTimeMax = 1000;
    Heuristic[] heuristics =  new Heuristic[]{new EmptyTileAndLargeEdgeTileHeuristic(),
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
            int depth = gameState.getZeros() < 5 ? 7 : 5;
            double heuristicVal = heuristicEvaluator.evaluate(heuristics, gameState,
                    depth, MinimaxLevelType.Min, -1*Double.MAX_VALUE, Double.MAX_VALUE);
//            Log.getLogger(MinimaxGameManager.class.getName()).log(Level.INFO, Integer.toString(heuristicVal));
            if (heuristicVal > maxHeuristicValue || minHeuristicDirection == null) {
                minHeuristicDirection = allNextGameStateBySliding.get(gameState);
                maxHeuristicValue = heuristicVal;
            }
        }
        return minHeuristicDirection;
    }
}
