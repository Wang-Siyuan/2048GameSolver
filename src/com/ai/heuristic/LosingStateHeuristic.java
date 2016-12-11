package com.ai.heuristic;

import com.ai.model.GameState;
/**
 * Created by Sylvie on 12/10/16.
 */
public class LosingStateHeuristic implements Heuristic{
    @Override
     public int evaluate(GameState gameState) {
                if (gameState.isLost()) {
                        return -100000;
                    } else {
                        return 0;
                    }
            }

}
