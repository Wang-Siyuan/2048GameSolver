package com.ai.model;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * Created by z on 12/2/16.
 */
public class GameTreeTest {

    @Test
    public void testConstructor () {
        GameState currentGameState = new GameState(
                "4 2 0 2 " +
                "4 8 4 4 " +
                "8 8 2 4 " +
                "2 16 2 8");
        GameTree gameTree = new GameTree(currentGameState, 5, MinimaxLevelType.Max);
        GameTreeNode gameTreeRoot = gameTree.root;
        assertEquals(4, gameTreeRoot.childNodes.size()); //level 1

        Set<GameTreeNode> childNodesA = new HashSet<>();
        childNodesA.addAll(gameTreeRoot.childNodes);

        Set<GameTreeNode> childNodesB = new HashSet<>();
        for (GameTreeNode childNode : childNodesA) {
            assertEquals(childNode.gameState.getZeros()*2, childNode.childNodes.size()); //level 2
            childNodesB.addAll(childNode.childNodes);
        }

        childNodesA = new HashSet<>();
        for (GameTreeNode childNode : childNodesB) {
            childNodesA.addAll(childNode.childNodes);
        }
        assertTrue(4 * childNodesB.size() > childNodesA.size()); //level 3

        childNodesB = new HashSet<>();
        for (GameTreeNode childNode : childNodesA) {
            assertEquals(childNode.gameState.getZeros()*2, childNode.childNodes.size()); //level 4
            childNodesB.addAll(childNode.childNodes);
        }

        childNodesA = new HashSet<>();
        for (GameTreeNode childNode : childNodesB) {
            childNodesA.addAll(childNode.childNodes);
        }
        assertTrue(4 * childNodesB.size() > childNodesA.size()); //level 5

        for (GameTreeNode childNode : childNodesA) {
            assertTrue(childNode.childNodes.isEmpty());
        }
    }
}