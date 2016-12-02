package com.ai;

import com.ai.model.Direction;
import com.ai.model.GameState;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * Created by z on 12/2/16.
 */
public class GameStateManagerTest {
    GameStateManager gameStateManager = new GameStateManager();
    boolean[][] merged;
    
    @Before
    public void init() {
        merged = new boolean[4][4];
    }

    @Test
    public void testGetAllNextGameStateByAddingNewTile() throws Exception {
        GameState currentGameState = new GameState("1 0 0 2 3 4 5 6 7 0 9 0 10 8 3 2");
        Set<GameState> allNextGameStateByAddingNewTile = gameStateManager
                .getAllNextGameStateByAddingNewTile(currentGameState);
        assertEquals(8, allNextGameStateByAddingNewTile.size());
        for (GameState gameState : allNextGameStateByAddingNewTile) {
            assertTrue(offByOne(currentGameState, gameState));
            assertTrue(GameStateManager.POSSIBLE_NEW_TILE_VALUES.contains(getTheDiffTileValueOffset(currentGameState, gameState)));
        }
    }

    @Test
    public void testGetAllNextGameStateBySliding() throws Exception {
        GameState currentGameState = new GameState(
                        "4 2 0 2 " +
                        "4 8 4 4 " +
                        "8 8 2 4 " +
                        "2 16 2 8");
        Map<GameState, Direction> allNextGameStateBySliding = gameStateManager.getAllNextGameStateBySliding(currentGameState);
        assertEquals(4, allNextGameStateBySliding.size());
        GameState left = new GameState(
                        "4 4 0 0 " +
                        "4 8 8 0 " +
                        "16 2 4 0 " +
                        "2 16 2 8");

        GameState right = new GameState(
                        "0 0 4 4 " +
                        "0 4 8 8 " +
                        "0 16 2 4 " +
                        "2 16 2 8");

        GameState up = new GameState(
                        "8 2 4 2 " +
                        "8 16 4 8 " +
                        "2 16 0 8 " +
                        "0 0 0 0");

        GameState down = new GameState(
                        "0 0 0 0 " +
                        "8 2 0 2 " +
                        "8 16 4 8 " +
                        "2 16 4 8");
        assertTrue(allNextGameStateBySliding.containsKey(left));
        assertTrue(allNextGameStateBySliding.containsKey(right));
        assertTrue(allNextGameStateBySliding.containsKey(up));
        assertTrue(allNextGameStateBySliding.containsKey(down));
    }

    @Test
    public void testAddNewTileValue() throws Exception {
        GameState currentGameState = new GameState("1 0 0 2 3 4 5 6 7 0 9 0 10 8 3 2");
        GameState nextState = gameStateManager.addNewTileValue(currentGameState, 2, 0, 1);
        GameState expectedState = new GameState("1 2 0 2 3 4 5 6 7 0 9 0 10 8 3 2");
        assertEquals(expectedState, nextState);
    }

    @Test
    public void testSlide() throws Exception {
        GameState currentGameState = new GameState(
                "4 2 0 2 " +
                "4 8 4 4 " +
                "8 8 2 4 " +
                "2 16 2 8");
        GameState next = gameStateManager.slide(currentGameState, Direction.Right);
        GameState expectedNext = new GameState(
                "0 0 4 4 " +
                "0 4 8 8 " +
                "0 16 2 4 " +
                "2 16 2 8");
        assertEquals(expectedNext, next);

        next = gameStateManager.slide(expectedNext, Direction.Left);
        expectedNext = new GameState(
                        "8 0 0 0 " +
                        "4 16 0 0 " +
                        "16 2 4 0 " +
                        "2 16 2 8");
        assertEquals(expectedNext, next);

        next = gameStateManager.slide(currentGameState, Direction.Left);
        expectedNext = new GameState(
                        "4 4 0 0 " +
                        "4 8 8 0 " +
                        "16 2 4 0 " +
                        "2 16 2 8");
        assertEquals(expectedNext, next);

        next = gameStateManager.slide(currentGameState, Direction.Down);
        expectedNext = new GameState(
                        "0 0 0 0 " +
                        "8 2 0 2 " +
                        "8 16 4 8 " +
                        "2 16 4 8");
        assertEquals(expectedNext, next);

        next = gameStateManager.slide(currentGameState, Direction.Up);
        expectedNext = new GameState(
                        "8 2 4 2 " +
                        "8 16 4 8 " +
                        "2 16 0 8 " +
                        "0 0 0 0");
        assertEquals(expectedNext, next);
    }

    @Test
    public void testSlideSingleTileAndMerge() throws Exception {
        // Right
        merged = new boolean[4][4];
        GameState currentGameState = new GameState("2 0 2 2 0 0 0 0 0 0 0 0 0 0 0 0");
        GameState next = gameStateManager.slideSingleTileAndMerge(currentGameState, 0, 3, Direction.Right, merged);
        assertEquals(currentGameState, next);

        merged = new boolean[4][4];
        currentGameState = new GameState("2 2 4 0 0 0 0 0 0 0 0 0 0 0 0 0");
        next = gameStateManager.slideSingleTileAndMerge(currentGameState, 0, 2, Direction.Right, merged);
        GameState expectedNext = new GameState("2 2 0 4 0 0 0 0 0 0 0 0 0 0 0 0");
        assertEquals(expectedNext, next);

        merged = new boolean[4][4];
        currentGameState = new GameState("2 2 0 4 0 0 0 0 0 0 0 0 0 0 0 0");
        next = gameStateManager.slideSingleTileAndMerge(currentGameState, 0, 1, Direction.Right, merged);
        expectedNext = new GameState("2 0 2 4 0 0 0 0 0 0 0 0 0 0 0 0");
        assertEquals(expectedNext, next);

        merged = new boolean[4][4];
        currentGameState = new GameState("2 0 2 4 0 0 0 0 0 0 0 0 0 0 0 0");
        next = gameStateManager.slideSingleTileAndMerge(currentGameState, 0, 0, Direction.Right, merged);
        expectedNext = new GameState("0 0 4 4 0 0 0 0 0 0 0 0 0 0 0 0");
        assertEquals(expectedNext, next);

        merged = new boolean[4][4];
        currentGameState = new GameState("2 0 2 4 0 0 0 0 0 0 0 0 0 0 0 0");
        next = gameStateManager.slideSingleTileAndMerge(currentGameState, 0, 1, Direction.Right, merged);
        expectedNext = new GameState("2 0 2 4 0 0 0 0 0 0 0 0 0 0 0 0");
        assertEquals(expectedNext, next);

        merged = new boolean[4][4];
        currentGameState = new GameState("8 8 2 4 0 0 0 0 0 0 0 0 0 0 0 0");
        next = gameStateManager.slideSingleTileAndMerge(currentGameState, 0, 3, Direction.Right, merged);
        expectedNext = new GameState("8 8 2 4 0 0 0 0 0 0 0 0 0 0 0 0");
        assertEquals(expectedNext, next);

        next = gameStateManager.slideSingleTileAndMerge(next, 0, 2, Direction.Right, merged);
        expectedNext = new GameState("8 8 2 4 0 0 0 0 0 0 0 0 0 0 0 0");
        assertEquals(expectedNext, next);

        // Left
        merged = new boolean[4][4];
        currentGameState = new GameState("2 0 2 2 0 0 0 0 0 0 0 0 0 0 0 0");
        next = gameStateManager.slideSingleTileAndMerge(currentGameState, 0, 0, Direction.Left, merged);
        assertEquals(currentGameState, next);

        merged = new boolean[4][4];
        currentGameState = new GameState("0 2 2 4 0 0 0 0 0 0 0 0 0 0 0 0");
        next = gameStateManager.slideSingleTileAndMerge(currentGameState, 0, 1, Direction.Left, merged);
        expectedNext = new GameState("2 0 2 4 0 0 0 0 0 0 0 0 0 0 0 0");
        assertEquals(expectedNext, next);

        merged = new boolean[4][4];
        currentGameState = new GameState("2 0 2 4 0 0 0 0 0 0 0 0 0 0 0 0");
        next = gameStateManager.slideSingleTileAndMerge(currentGameState, 0, 2, Direction.Left, merged);
        expectedNext = new GameState("4 0 0 4 0 0 0 0 0 0 0 0 0 0 0 0");
        assertEquals(expectedNext, next);

        merged = new boolean[4][4];
        currentGameState = new GameState("8 0 2 4 0 0 0 0 0 0 0 0 0 0 0 0");
        next = gameStateManager.slideSingleTileAndMerge(currentGameState, 0, 2, Direction.Left, merged);
        expectedNext = new GameState("8 2 0 4 0 0 0 0 0 0 0 0 0 0 0 0");
        assertEquals(expectedNext, next);

        merged = new boolean[4][4];
        currentGameState = new GameState("4 2 2 0 0 0 0 0 0 0 0 0 0 0 0 0");
        next = gameStateManager.slideSingleTileAndMerge(currentGameState, 0, 2, Direction.Left, merged);
        expectedNext = new GameState("4 4 0 0 0 0 0 0 0 0 0 0 0 0 0 0");
        assertEquals(expectedNext, next);

        merged = new boolean[4][4];
        currentGameState = new GameState("4 4 0 0 0 0 0 0 0 0 0 0 0 0 0 0");
        next = gameStateManager.slideSingleTileAndMerge(currentGameState, 0, 1, Direction.Left, merged);
        expectedNext = new GameState("8 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0");
        assertEquals(expectedNext, next);

        merged = new boolean[4][4];
        currentGameState = new GameState("4 8 8 0 0 0 0 0 0 0 0 0 0 0 0 0");
        next = gameStateManager.slideSingleTileAndMerge(currentGameState, 0, 2, Direction.Left, merged);
        expectedNext = new GameState("4 16 0 0 0 0 0 0 0 0 0 0 0 0 0 0");
        assertEquals(expectedNext, next);

        //Down
        merged = new boolean[4][4];
        currentGameState = new GameState("2 9 9 9 0 9 9 9 2 9 9 9 2 9 9 9");
        next = gameStateManager.slideSingleTileAndMerge(currentGameState, 3, 0, Direction.Down, merged);
        assertEquals(currentGameState, next);

        merged = new boolean[4][4];
        currentGameState = new GameState("2 9 9 9 2 9 9 9 2 9 9 9 0 9 9 9");
        next = gameStateManager.slideSingleTileAndMerge(currentGameState, 2, 0, Direction.Down, merged);
        expectedNext = new GameState("2 9 9 9 2 9 9 9 0 9 9 9 2 9 9 9");
        assertEquals(expectedNext, next);

        merged = new boolean[4][4];
        currentGameState = new GameState("2 9 9 9 2 9 9 9 2 9 9 9 2 9 9 9");
        next = gameStateManager.slideSingleTileAndMerge(currentGameState, 2, 0, Direction.Down, merged);
        expectedNext = new GameState("2 9 9 9 2 9 9 9 0 9 9 9 4 9 9 9");
        assertEquals(expectedNext, next);

        merged = new boolean[4][4];
        currentGameState = new GameState("2 9 9 9 2 9 9 9 0 9 9 9 4 9 9 9");
        next = gameStateManager.slideSingleTileAndMerge(currentGameState, 1, 0, Direction.Down, merged);
        expectedNext = new GameState("2 9 9 9 0 9 9 9 2 9 9 9 4 9 9 9");
        assertEquals(expectedNext, next);

        merged = new boolean[4][4];
        currentGameState = new GameState("2 9 9 9 0 9 9 9 2 9 9 9 4 9 9 9");
        next = gameStateManager.slideSingleTileAndMerge(currentGameState, 0, 0, Direction.Down, merged);
        expectedNext = new GameState("0 9 9 9 0 9 9 9 4 9 9 9 4 9 9 9");
        assertEquals(expectedNext, next);

        merged = new boolean[4][4];
        currentGameState = new GameState("0 9 9 9 0 9 9 9 4 9 9 9 4 9 9 9");
        next = gameStateManager.slideSingleTileAndMerge(currentGameState, 2, 0, Direction.Down, merged);
        expectedNext = new GameState("0 9 9 9 0 9 9 9 0 9 9 9 8 9 9 9");
        assertEquals(expectedNext, next);

        //Up
        merged = new boolean[4][4];
        currentGameState = new GameState("2 9 9 9 0 9 9 9 2 9 9 9 2 9 9 9");
        next = gameStateManager.slideSingleTileAndMerge(currentGameState, 1, 0, Direction.Up, merged);
        assertEquals(currentGameState, next);

        merged = new boolean[4][4];
        currentGameState = new GameState("2 9 9 9 2 9 9 9 2 9 9 9 0 9 9 9");
        next = gameStateManager.slideSingleTileAndMerge(currentGameState, 1, 0, Direction.Up, merged);
        expectedNext = new GameState("4 9 9 9 0 9 9 9 2 9 9 9 0 9 9 9");
        assertEquals(expectedNext, next);

        merged = new boolean[4][4];
        currentGameState = new GameState("0 9 9 9 2 9 9 9 2 9 9 9 2 9 9 9");
        next = gameStateManager.slideSingleTileAndMerge(currentGameState, 1, 0, Direction.Up, merged);
        expectedNext = new GameState("2 9 9 9 0 9 9 9 2 9 9 9 2 9 9 9");
        assertEquals(expectedNext, next);

        merged = new boolean[4][4];
        currentGameState = new GameState("4 9 9 9 2 9 9 9 0 9 9 9 2 9 9 9");
        next = gameStateManager.slideSingleTileAndMerge(currentGameState, 3, 0, Direction.Up, merged);
        expectedNext = new GameState("4 9 9 9 4 9 9 9 0 9 9 9 0 9 9 9");
        assertEquals(expectedNext, next);

        merged = new boolean[4][4];
        currentGameState = new GameState("4 9 9 9 4 9 9 9 2 9 9 9 4 9 9 9");
        next = gameStateManager.slideSingleTileAndMerge(currentGameState, 1, 0, Direction.Up, merged);
        expectedNext = new GameState("8 9 9 9 0 9 9 9 2 9 9 9 4 9 9 9");
        assertEquals(expectedNext, next);
        
    }

    boolean offByOne(GameState g1, GameState g2) {
        int counter = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (g1.tileValues[i][j] != g2.tileValues[i][j]) {
                    counter++;
                    if (counter > 1) {
                        return false;
                    }
                }
            }
        }
        return counter == 1;
    }

    int getTheDiffTileValueOffset(GameState g1, GameState g2) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (g1.tileValues[i][j] != g2.tileValues[i][j]) {
                    return Math.abs(g1.tileValues[i][j] - g2.tileValues[i][j]);
                }
            }
        }
        return -1;
    }
}