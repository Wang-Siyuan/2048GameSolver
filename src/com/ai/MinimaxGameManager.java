package com.ai;

import com.ai.heuristic.EmptyTileAndLargeEdgeTileHeuristic;
import com.ai.heuristic.Heuristic;
import com.ai.model.Direction;
import com.ai.model.GameState;
import com.ai.model.GameTree;
import com.ai.model.GameTreeNode;

import java.util.Map;

/**
 * Created by z on 12/2/16.
 */
public class MinimaxGameManager {
    GameStateManager gameStateManager = new GameStateManager();
    HeuristicEvaluator heuristicEvaluator = new HeuristicEvaluator();
    Heuristic emptyTIleAndLargeEdgeTileHeuristic = new EmptyTileAndLargeEdgeTileHeuristic();

    public MinimaxGameManager() {
    }

    public Direction getNextBestMoveForUser(GameState currentGameState) {
        Map<GameState, Direction> allNextGameStateBySliding = gameStateManager
                .getAllNextGameStateBySliding(currentGameState);
        int minHeuristicValue = Integer.MAX_VALUE;
        Direction minHeuristicDirection = null;
        for (GameState gameState : allNextGameStateBySliding.keySet()) {
            GameTree gameTree = new GameTree(gameState, 5);
            GameTreeNode gameTreeNode = gameTree.root;
            int heuristicVal = heuristicEvaluator.evaluate(emptyTIleAndLargeEdgeTileHeuristic, gameTreeNode,
                    3, HeuristicEvaluator.MinimaxLevelType.Max);
            if (heuristicVal < minHeuristicValue) {
                minHeuristicDirection = allNextGameStateBySliding.get(gameState);
                minHeuristicValue = heuristicVal;
            }
        }
        return minHeuristicDirection;
    }
}
