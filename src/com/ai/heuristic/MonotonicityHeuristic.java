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
    public int evaluate(GameState gameState) {
        int score = 0;
        for(int i = 0; i < 4; i++){
            List<Integer> list = new ArrayList<>();
            for(int j = 0; j < 4; j++){
                if(gameState.tileValues[i][j] != 0){
                    list.add(gameState.tileValues[i][j]);
                }
            }
            int increase = 0;
            int decrease = 0;
            for(int k = 0; k < list.size() - 1; k++){
                if(list.get(k) < list.get(k + 1)){
                    increase++;
                }
                if(list.get(k) > list.get(k+1)){
                    decrease++;
                }
            }
            if(increase == list.size() || decrease == list.size()) {
                score += 10;
            }
        }

        for(int j = 0; j < 4; j++){
            List<Integer> list2 = new ArrayList<>();
            for(int i = 0; i < 4; i++){
                if(gameState.tileValues[i][j] != 0){
                    list2.add(gameState.tileValues[i][j]);
                }
            }
            int increase2 = 0;
            int decrease2 = 0;
            for(int k = 0; k < list2.size() - 1; k++){
                if(list2.get(k) < list2.get(k + 1)){
                    increase2++;
                }
                if(list2.get(k) > list2.get(k+1)){
                    decrease2++;
                }
            }
            if(increase2 == list2.size() || decrease2 == list2.size()) {
                score += 10;
            }
        }

        return score;
    }

}
