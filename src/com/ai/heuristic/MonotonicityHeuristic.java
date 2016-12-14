package com.ai.heuristic;

import com.ai.model.GameState;
import com.util.Utility;
import java.util.List;
import java.util.ArrayList;
/**
 * Created by Sylvie on 12/3/16.
 */
public class MonotonicityHeuristic implements Heuristic {
    @Override
    public double evaluate(GameState gameState) {
        return gameState.getMonoticity() + 25;
    }

    @Override
    public double getWeight() {
        return 2.5;
    }

}
