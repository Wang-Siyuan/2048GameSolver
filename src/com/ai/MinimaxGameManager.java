package com.ai;

import com.ai.heuristic.EmptyTileAndLargeEdgeTileHeuristic;
import com.ai.heuristic.LargestNumberAtCorner;
import com.ai.heuristic.MonotonicityHeuristic;
import com.ai.heuristic.SmoothnessHeuristic;
import com.ai.heuristic.Heuristic;
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
    Heuristic[] heuristics =  new Heuristic[]{new EmptyTileAndLargeEdgeTileHeuristic(),
            new LargestNumberAtCorner(), new SmoothnessHeuristic(), new MonotonicityHeuristic()};

    public MinimaxGameManager() {
    }

    public Direction getNextBestMoveForUser(GameState currentGameState) {
        Map<GameState, Direction> allNextGameStateBySliding = gameStateManager
                .getAllNextGameStateBySliding(currentGameState);
        int minHeuristicValue = Integer.MAX_VALUE;
        Direction minHeuristicDirection = null;
        for (GameState gameState : allNextGameStateBySliding.keySet()) {
            if (gameState.equals(currentGameState)) {
                continue;
            }
            GameTree gameTree = new GameTree(gameState, 5, MinimaxLevelType.Max);
//            Logger.getLogger(MinimaxGameManager.class.getName()).log(Level.INFO, "game tree ready");
            GameTreeNode gameTreeNode = gameTree.root;
            int heuristicVal = heuristicEvaluator.evaluate(heuristics, gameTreeNode,
                    5, HeuristicEvaluator.MinimaxLevelType.Max, Integer.MIN_VALUE, Integer.MAX_VALUE);
//            Logger.getLogger(MinimaxGameManager.class.getName()).log(Level.INFO, Integer.toString(heuristicVal));
            if (heuristicVal < minHeuristicValue) {
                minHeuristicDirection = allNextGameStateBySliding.get(gameState);
                minHeuristicValue = heuristicVal;
            }
        }
        return minHeuristicDirection;
    }
}
