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

    Heuristic[] heuristics =  new Heuristic[]{new SmoothnessHeuristic(), new MonotonicityHeuristic(),
            new EmptyTileHeuristic(), new MaxValueHeuristic(), new LosingStateHeuristic(), new MergeableTilesHeuristic()};

    public MinimaxGameManager() {
    }

    public Direction getNextBestMoveForUser(GameState currentGameState) {
        Map<GameState, Direction> allNextGameStateBySliding = gameStateManager
                .getAllNextGameStateBySliding(currentGameState);
        double maxHeuristicValue = Double.MIN_VALUE;
        Direction minHeuristicDirection = null;
        for (GameState gameState : allNextGameStateBySliding.keySet()) {
            if (gameState.equals(currentGameState)) {
                continue;
            }
//            long startTime = System.currentTimeMillis();
//            GameTree gameTree = new GameTree(gameState, 7, MinimaxLevelType.Max);
//            GameTreeNode gameTreeNode = gameTree.root;
            double heuristicVal = heuristicEvaluator.evaluate(heuristics, gameState,
                    5, MinimaxLevelType.Max, -Double.MAX_VALUE, Double.MAX_VALUE);
//            Logger.getLogger(MinimaxGameManager.class.getName()).log(Level.INFO, "Time spent evaluating heuristic: " + (System.currentTimeMillis() - startTime));

//            Logger.getLogger(MinimaxGameManager.class.getName()).log(Level.INFO, Integer.toString(heuristicVal));
            if (heuristicVal > maxHeuristicValue) {
                minHeuristicDirection = allNextGameStateBySliding.get(gameState);
                maxHeuristicValue = heuristicVal;
            }
        }
        return minHeuristicDirection;
    }
}
