package com.ai.model;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by z on 12/2/16.
 */
public class GameStateTest {

    @Test
    public void testGetAverageTileValue() throws Exception {
        GameState gameState = new GameState("1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16");
        assertEquals(8.5, gameState.getAverageTileValue(), 0.01);
    }
}