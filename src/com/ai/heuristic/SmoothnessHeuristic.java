package com.ai.heuristic;

import com.ai.model.GameState;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Sylvie on 12/3/16.
 */
public class SmoothnessHeuristic implements Heuristic {

//    @Override
//    public double evaluate(GameState gameState) {
//        double score = 10;
//        for(int i = 0; i < 4; i++) {
//            List<Integer> nonZeroRowValues = gameState.getNonZeroValues(gameState.getRowValues(i));
//            if (nonZeroRowValues.size() >= 2) {
//                for (int j = 0; j < nonZeroRowValues.size()-1; j++) {
//                    score -= Math.abs(Math.log(nonZeroRowValues.get(j+1))/Math.log(2) - Math.log(nonZeroRowValues.get(j))/Math.log(2));
//                }
//            }
//        }
//
//        for(int j = 0; j < 4; j++) {
//            List<Integer> nonZeroColumnValues = gameState.getNonZeroValues(gameState.getColumnValues(j));
//            if (nonZeroColumnValues.size() >= 2) {
//                for (int i = 0; i < nonZeroColumnValues.size() - 1; i++) {
//                    score -= Math.abs(Math.log(nonZeroColumnValues.get(i + 1))/Math.log(2) - Math.log(nonZeroColumnValues.get(i))/Math.log(2));
//                }
//            }
//        }
//        return score;
//    }
    
    @Override
    public double evaluate(GameState gameState) {
        double score = 0;
        for (int r = 0; r < gameState.tileValues.length; r++){
            for (int c = 0; c < gameState.tileValues[r].length; c++){
                if (gameState.tileValues[r][c] != 0){
                    double value = Math.log(gameState.tileValues[r][c]) / Math.log(2);
                    if (c != 3){
                        if (gameState.tileValues[r][c+1] != 0 && gameState.tileValues[r][c] != 0){
                            double targetValue = Math.log(gameState.tileValues[r][c+1]) / Math.log(2);
                            score -= Math.abs(value - targetValue);
                        }
                    }
                    if (r != 3){
                        if (gameState.tileValues[r+1][c] != 0 && gameState.tileValues[r][c] != 0){
                            double targetValue = Math.log(gameState.tileValues[r+1][c] / Math.log(2));
                            score -= Math.abs(value - targetValue);
                        }
                    }
                }
            }
        }
        return score;
    }

    @Override
    public double getWeight() {
        return 0.1;
    }
}
