package com.ai.heuristic;

import com.ai.model.GameState;
import com.util.Utility;

/**
 * Created by Sylvie on 12/3/16.
 */
public class MonotonicityHeuristic implements Heuristic {
    @Override
    public int evaluate(GameState gameState) {
        int score = 0;
        for(int i = 0; i < 4; i++){
            if(gameState.tileValues[i][0] >= gameState.tileValues[i][1]
                    && gameState.tileValues[i][1] >= gameState.tileValues[i][2]
                    && gameState.tileValues[i][2] >= gameState.tileValues[i][3]){
                score++;
            }
            else if(gameState.tileValues[i][0] <= gameState.tileValues[i][1]
                    && gameState.tileValues[i][1] <= gameState.tileValues[i][2]
                    && gameState.tileValues[i][2] <= gameState.tileValues[i][3]){
                score++;
            }
        }

        for(int j = 0; j < 4; j++){
            if(gameState.tileValues[0][j] >= gameState.tileValues[1][j]
                    && gameState.tileValues[1][j] >= gameState.tileValues[2][j]
                    && gameState.tileValues[2][j] >= gameState.tileValues[3][j]){
                score++;
            }
            else if(gameState.tileValues[0][j] <= gameState.tileValues[1][j]
                    && gameState.tileValues[1][j] <= gameState.tileValues[2][j]
                    && gameState.tileValues[2][j] <= gameState.tileValues[3][j]){
                score++;
            }
        }

        return score;
    }

}
