package com.ai;

import com.ai.heuristic.Heuristic;
import com.ai.model.Direction;
import com.ai.model.GameState;
import com.ai.model.GameTreeNode;
import com.ai.model.MinimaxLevelType;

import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by z on 12/2/16.
 */
public class HeuristicEvaluator {
    GameStateManager gameStateManager = new GameStateManager();
    public double evaluate(Heuristic[] heuristic, GameState gameState, int depth, MinimaxLevelType levelType, double alpha, double beta) {
        if (depth == 0) {
            double val = 0;
            String line = "";
//            Logger.getLogger(HeuristicEvaluator.class.getName()).log(Level.INFO, gameState.toString());
            for(Heuristic h : heuristic){
                double result = h.evaluate(gameState) * h.getWeight();
                line += h.getClass().getSimpleName() + result + " ";
                val += result;
//                Logger.getLogger(Heuristic.class.getName()).log(Level.INFO, "Heuristic " + h.getClass().getName() + " has result " + result);
            }
//            Logger.getLogger(HeuristicEvaluator.class.getName()).log(Level.INFO, "Heuristic " + line);
            return val;
        }
        if(levelType == MinimaxLevelType.Max){
            Map<GameState, Direction> allNextGameStateBySliding = gameStateManager.getAllNextGameStateBySliding(gameState);
            for(GameState childGameState : allNextGameStateBySliding.keySet()){
                double val = evaluate(heuristic, childGameState, depth - 1, MinimaxLevelType.getOppositeType(levelType), alpha, beta);
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
            Set<GameState> allNextGameStateByAddingNewTile = gameStateManager.getAllNextGameStateByAddingNewTile(gameState);
            for(GameState childGameState : allNextGameStateByAddingNewTile){
                double val = evaluate(heuristic, childGameState, depth - 1, MinimaxLevelType.getOppositeType(levelType), alpha, beta);
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
}
