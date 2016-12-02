package com.ai.model;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by z on 12/2/16.
 */
public class GameTreeNode {
    public GameState gameState;
    public Set<GameTreeNode> childNodes;

    public GameTreeNode(GameState gameState) {
        this.gameState = gameState;
        this.childNodes = new HashSet<>();
    }
}
