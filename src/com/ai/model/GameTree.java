package com.ai.model;

import com.ai.GameStateManager;

import java.util.Set;

/**
 * Created by z on 12/2/16.
 */
public class GameTree {
    public GameStateManager gameStateManager = new GameStateManager();
    public GameTreeNode root;
    public int depth;

    public GameTree(GameState currentGameState, int depth, MinimaxLevelType levelType) {
        this.root = new GameTreeNode(currentGameState);
        this.depth = depth;
        if (depth > 0) {
            Set<GameState> childGameStateList;
            if (levelType == MinimaxLevelType.Max) {
                childGameStateList = gameStateManager.getAllNextGameStateBySliding(currentGameState).keySet();
            } else {
                childGameStateList = gameStateManager.getAllNextGameStateByAddingNewTile(currentGameState);
            }
            for (GameState childGameState : childGameStateList) {
                if (!childGameState.equals(currentGameState)) {
                    GameTree gameTree = new GameTree(childGameState, depth - 1, MinimaxLevelType.getOppositeType(levelType));
                    this.root.childNodes.add(gameTree.root);
                }
            }
        }
    }
}
