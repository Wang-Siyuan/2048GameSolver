package com.ai;

import com.ai.heuristic.*;
import com.ai.model.GameState;
import com.ai.model.MinimaxLevelType;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * Created by z on 12/13/16.
 */
public class HeuristicEvaluatorTest {
    HeuristicEvaluator heuristicEvaluator = new HeuristicEvaluator();
    Heuristic[] heuristics = new Heuristic[]{new EmptyTilesHeuristic(),
            new LargestNumberAtCorner(), new MonotonicityHeuristic(), new SmoothnessHeuristic(),
            new LosingStateHeuristic()};

    @Test
    public void testEvaluate() throws Exception {
        GameState g1 = new GameState("2 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0");
        GameState g2 = new GameState("2 2 0 0 0 0 0 0 0 0 0 0 0 0 0 0");
        GameState g3 = new GameState("2 2 4 0 0 0 0 0 0 0 0 0 0 0 0 0");
        GameState g4 = new GameState("2 2 4 8 0 0 0 0 0 0 0 0 0 0 0 0");
        GameState g5 = new GameState("2 2 4 8 2 0 0 0 0 0 0 0 0 0 0 0");
        GameState g6 = new GameState("2 2 4 8 2 16 0 0 0 0 0 0 0 0 0 0");
        GameState g7 = new GameState("2 2 4 8 2 16 16 0 0 0 0 0 0 0 0 0");
        GameState g8 = new GameState("2 2 4 8 2 16 16 32 0 0 0 0 0 0 0 0");
        GameState g9 = new GameState("2 2 4 8 2 16 16 32 8 0 0 0 0 0 0 0");
        GameState g10 = new GameState("2 2 4 8 2 16 16 32 8 4 0 0 0 0 0 0");
        GameState g11 = new GameState("2 2 4 8 2 16 16 32 8 4 16 0 0 0 0 0");
        GameState g12 = new GameState("2 2 4 8 2 16 16 32 8 4 16 8 0 0 0 0");
        GameState g13 = new GameState("2 2 4 8 2 16 16 32 8 4 16 8 128 0 0 0");
        GameState g14 = new GameState("2 2 4 8 2 16 16 32 8 4 16 8 128 256 0 0");
        GameState g15 = new GameState("2 2 4 8 2 16 16 32 8 4 16 8 128 256 8 0");
        List<GameState> gameStateList = Arrays.asList(g1,g2,g3,g4,g5,g6,g7,g8,g9,g10,g11,g12,g13,g14,g15);
        for (int i = 13; i < 15; i++) {
            GameState currentGameState = gameStateList.get(i);
            System.out.println("Game state " + i + " : ");
            for (int depth = 1; depth <= 15; depth++) {
                long startTime = System.currentTimeMillis();
                heuristicEvaluator.evaluate(heuristics, currentGameState,
                        depth, MinimaxLevelType.Min, -1 * Double.MAX_VALUE, Double.MAX_VALUE);
                long runTime = (System.currentTimeMillis() - startTime);
                System.out.print(runTime + " ");
                if (runTime > 5000l) {
                    break;
                }
            }
            System.out.println();
        }
    }
}