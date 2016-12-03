package com.ai.heuristic;

import com.ai.model.GameState;

/**
 * Created by Sylvie on 12/3/16.
 */
public class LargestNumberAtCorner implements Heuristic {
    @Override
    public int evaluate(GameState gameState) {
        int score = 0;
        int max = gameState.tileValues[0][0];
        int x = 0;
        int y = 0;
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                if(gameState.tileValues[0][0] > max){
                    x = i;
                    y = j;
                    max = gameState.tileValues[i][j];
                }
            }
        }

        if((x == 0 && y == 0) || (x == 0 && y == 3) || (x == 3 && y == 0) || (x == 3 && y == 3)){
            score += 4;
        }
        return score;
    }
}
