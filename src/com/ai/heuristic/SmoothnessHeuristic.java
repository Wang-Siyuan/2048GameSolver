package com.ai.heuristic;

import com.ai.model.GameState;

/**
 * Created by Sylvie on 12/3/16.
 */
public class SmoothnessHeuristic implements Heuristic {
    @Override
    public double evaluate(GameState gameState) {
        double score = 0;
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 3; j++){
                 //if(gameState.tileValues[i][j] != 0 && gameState.tileValues[i][j+1] == gameState.tileValues[i][j]){
                   //  score++;
                 //}
                if(gameState.tileValues[i][j] != 2 && gameState.tileValues[i][j] != 0 && gameState.tileValues[i][j+1] == gameState.tileValues[i][j]){
                    score += 2;
                }

            }
        }
        for(int j = 0; j < 4; j++){
            for(int i = 0; i < 3; i++){

                //if(gameState.tileValues[i][j] != 0 && gameState.tileValues[i][j] == gameState.tileValues[i+1][j]){
                  //  score++;
                //}
                if(gameState.tileValues[i][j] != 2 && gameState.tileValues[i][j] != 0 && gameState.tileValues[i][j] == gameState.tileValues[i+1][j]){
                    score += 2;
                }

            }
        }
        return score-(16-gameState.getZeros())/4;
    }

    @Override
    public double getWeight() {
        return 2.0;
    }
}
