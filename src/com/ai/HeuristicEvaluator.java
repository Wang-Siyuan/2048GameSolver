package com.ai;

import com.ai.heuristic.Heuristic;
import com.ai.model.GameTreeNode;

/**
 * Created by z on 12/2/16.
 */
public class HeuristicEvaluator {

    public int evaluate(Heuristic[] heuristic, GameTreeNode gameTreeNode, int depth, MinimaxLevelType levelType, int alpha, int beta) {
        if (depth == 0) {
            int val = 0;
            for(Heuristic h : heuristic){
                val += h.evaluate(gameTreeNode.gameState);
            }
            return val;
        }
        if(levelType == MinimaxLevelType.Max){
            for(GameTreeNode childNode : gameTreeNode.childNodes){
                int val = evaluate(heuristic, childNode, depth - 1, MinimaxLevelType.getOppositeType(levelType), alpha, beta);
                if(val > alpha){
                    alpha = val;
                }
                if(alpha >= beta){
                    break;
                }
            }
            return alpha;
        }

        else if(levelType == MinimaxLevelType.Min){
            for(GameTreeNode childNode : gameTreeNode.childNodes){
                int val = evaluate(heuristic, childNode, depth - 1, MinimaxLevelType.getOppositeType(levelType), alpha, beta);
                if(val < beta){
                    beta = val;
                }
                if(alpha >= beta){
                    break;
                }
            }
        }
        return beta;
    }

    enum MinimaxLevelType {
        Min,
        Max;

        public static MinimaxLevelType getOppositeType(MinimaxLevelType levelType) {
            if (levelType == Min) {
                return Max;
            } else {
                return Min;
            }
        }
    }
}
