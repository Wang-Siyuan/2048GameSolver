package com.ai.heuristic;

import com.ai.model.GameState;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Sylvie on 12/3/16.
 */
public class SmoothnessHeuristic implements Heuristic {

    @Override
    public double evaluate(GameState gameState) {
        double score = 0;
        for(int i = 0; i < 4; i++) {
            List<Integer> nonZeroRowValues = gameState.getNonZeroValues(gameState.getRowValues(i));
            if (nonZeroRowValues.size() >= 2) {
                for (int j = 0; j < nonZeroRowValues.size()-1; j++) {
                    score -= Math.abs(Math.log(nonZeroRowValues.get(j+1))/Math.log(2) - Math.log(nonZeroRowValues.get(j))/Math.log(2));
                }
            }
        }

        for(int j = 0; j < 4; j++) {
            List<Integer> nonZeroColumnValues = gameState.getNonZeroValues(gameState.getColumnValues(j));
            if (nonZeroColumnValues.size() >= 2) {
                for (int i = 0; i < nonZeroColumnValues.size() - 1; i++) {
                    score -= Math.abs(Math.log(nonZeroColumnValues.get(i + 1))/Math.log(2) - Math.log(nonZeroColumnValues.get(i))/Math.log(2));
                }
            }
        }
        return score;
    }

    @Override
    public double getWeight() {
        return 1.0;
    }
}
