package com.ai.model;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by z on 12/2/16.
 */
public class GameStateTest {

    @Test
    public void testConstructor() {
        GameState gameState = new GameState("1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 0");
        assertEquals(1, gameState.tileValues[0][0]);
        assertEquals(2, gameState.tileValues[0][1]);
        assertEquals(3, gameState.tileValues[0][2]);
        assertEquals(4, gameState.tileValues[0][3]);
        assertEquals(5, gameState.tileValues[1][0]);
        assertEquals(6, gameState.tileValues[1][1]);
        assertEquals(7, gameState.tileValues[1][2]);
        assertEquals(8, gameState.tileValues[1][3]);
        assertEquals(9, gameState.tileValues[2][0]);
        assertEquals(10, gameState.tileValues[2][1]);
        assertEquals(11, gameState.tileValues[2][2]);
        assertEquals(12, gameState.tileValues[2][3]);
        assertEquals(13, gameState.tileValues[3][0]);
        assertEquals(14, gameState.tileValues[3][1]);
        assertEquals(15, gameState.tileValues[3][2]);
        assertEquals(0, gameState.tileValues[3][3]);
    }

    @Test
    public void testGetAverageTileValue() throws Exception {
        GameState gameState = new GameState("1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16");
        assertEquals(8.5, gameState.getAverageTileValue(), 0.01);
    }
}