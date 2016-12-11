package com.ai.heuristic;

import com.ai.model.GameState;
import com.util.Utility;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
/**
 * Created by Sylvie on 12/3/16.
 */
public class MonotonicityHeuristic implements Heuristic {
    @Override
    public double evaluate(GameState gameState) {
        double increase = 0;
        double decrease = 0;
        for(int i = 0; i < 4; i++){
            List<Integer> list = new ArrayList<>();
            for(int j = 0; j < 4; j++){
                if(gameState.tileValues[i][j] != 0){
                    list.add(gameState.tileValues[i][j]);
                }
            }
            for(int k = 0; k < list.size() - 1; k++){
                int num1 = list.get(k);
                int num2 = list.get(k+1);
                double diff = Math.abs(Math.log(num1) / Math.log(2) - Math.log(num2) / Math.log(2));
                if(num1 < num2){
                    increase+=diff;
                } else if (num1 > num2){
                    decrease+=diff;
                }
            }
        }
        double increase2 = 0;
        double decrease2 = 0;
        for(int j = 0; j < 4; j++){
            List<Integer> list2 = new ArrayList<>();
            for(int i = 0; i < 4; i++){
                if(gameState.tileValues[i][j] != 0){
                    list2.add(gameState.tileValues[i][j]);
                }
            }
            for(int k = 0; k < list2.size() - 1; k++){
                int num1 = list2.get(k);
                int num2 = list2.get(k+1);
                double diff = Math.abs(Math.log(num1) / Math.log(2) - Math.log(num2) / Math.log(2));
                if(num1 < num2){
                    increase2+=diff;
                } else if (num2 > num2){
                    decrease2+=diff;
                }
            }
        }

        return Math.max(increase, decrease) + Math.max(increase2, decrease2);
    }

    @Override
    public double getWeight() {
        return 1.0;
    }

}
