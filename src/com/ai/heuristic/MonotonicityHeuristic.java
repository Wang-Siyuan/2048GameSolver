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
//    @Override
//    public double evaluate(GameState gameState) {
//        double increase = 0;
//        double decrease = 0;
//        for(int i = 0; i < 4; i++){
//            List<Integer> list = new ArrayList<>();
//            for(int j = 0; j < 4; j++){
//                if(gameState.tileValues[i][j] != 0){
//                    list.add(gameState.tileValues[i][j]);
//                }
//            }
//            for(int k = 0; k < list.size() - 1; k++){
//                int num1 = list.get(k);
//                int num2 = list.get(k+1);
////                double diff = Math.abs(Math.log(num1) / Math.log(2) - Math.log(num2) / Math.log(2));
//                if(num1 < num2){
//                    increase+=1;
//                    decrease-=10;
//                } else if (num1 > num2){
//                    increase-=10;
//                    decrease+=1;
//                }
//            }
//        }
//        double increase2 = 0;
//        double decrease2 = 0;
//        for(int j = 0; j < 4; j++){
//            List<Integer> list2 = new ArrayList<>();
//            for(int i = 0; i < 4; i++){
//                if(gameState.tileValues[i][j] != 0){
//                    list2.add(gameState.tileValues[i][j]);
//                }
//            }
//            for(int k = 0; k < list2.size() - 1; k++){
//                int num1 = list2.get(k);
//                int num2 = list2.get(k+1);
////                double diff = Math.abs(Math.log(num1) / Math.log(2) - Math.log(num2) / Math.log(2));
//                if(num1 < num2){
//                    increase2+=1;
//                    decrease2-=10;
//                } else if (num2 > num2){
//                    increase2-=10;
//                    decrease2+=1;
//                }
//            }
//        }
//
//        double result = Math.max(increase, decrease) + Math.max(increase2, decrease2);
//        return result;
//    }

    public double evaluate(GameState gameState){
        int[] totals = new int[]{0, 0, 0, 0};
        //Left Right
        for (int c = 0; c < 4; c++){
            int current = 0;
            int nextRow = current + 1;
            while (nextRow < 4){
                while (nextRow < 4 && gameState.tileValues[nextRow][c] == 0)
                    nextRow++;
                if (nextRow == 4)
                    nextRow--;
                int currentValue = 0, nextValue = 0;
                if (gameState.tileValues[current][c] != 0){
                    currentValue = (int) (Math.log(gameState.tileValues[current][c]) / Math.log(2));
                }
                if (gameState.tileValues[nextRow][c] != 0){
                    nextValue = (int) (Math.log(gameState.tileValues[nextRow][c]) / Math.log(2));
                }
                if (currentValue > nextValue) {
                    totals[0] += nextValue - currentValue;
                } else if (nextValue > currentValue) {
                    totals[1] += currentValue - nextValue;
                }
                current = nextRow;
                nextRow++;
            }
        }
        //Up Down
        for (int r = 0; r < 4; r++){
            int current=0;
            int nextCol = current+1;
            while (nextCol < 4){
                while (nextCol < 4 && gameState.tileValues[r][nextCol] == 0)
                    nextCol++;
                if (nextCol == 4)
                    nextCol--;
                int currentValue = 0, nextValue = 0;
                if (gameState.tileValues[r][current] != 0){
                    currentValue = (int) (Math.log(gameState.tileValues[r][current]) / Math.log(2));
                }
                if (gameState.tileValues[r][nextCol] != 0){
                    nextValue = (int) (Math.log(gameState.tileValues[r][nextCol]) / Math.log(2));
                }
                if (currentValue > nextValue) {
                    totals[2] += nextValue - currentValue;
                } else if (nextValue > currentValue) {
                    totals[3] += currentValue - nextValue;
                }
                current = nextCol;
                nextCol++;
            }
        }
        return Math.max(totals[0], totals[1]) + Math.max(totals[2], totals[3]);
    }
    
    @Override
    public double getWeight() {
        return 1.0;
    }

}
