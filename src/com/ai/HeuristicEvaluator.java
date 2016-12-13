package com.ai;

import com.ai.heuristic.Heuristic;
import com.ai.heuristic.HeuristicEvaluation;
import com.ai.heuristic.HeuristicStats;
import com.ai.model.Direction;
import com.ai.model.GameState;
import com.ai.model.MinimaxLevelType;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by z on 12/2/16.
 */
public class HeuristicEvaluator {
    GameStateManager gameStateManager = new GameStateManager();
    HeuristicStats heuristicStats = new HeuristicStats();

    public double evaluate(Heuristic[] heuristic, GameState gameState, int depth, MinimaxLevelType levelType, double alpha, double beta) {
        if (depth == 0) {
            double val = 0;
            Map<Heuristic, HeuristicEvaluation> evaluationResult = new HashMap<>();
            for(Heuristic h : heuristic){
                double score = h.evaluate(gameState) * h.getWeight();
                evaluationResult.put(h, new HeuristicEvaluation(gameState, score));
                val += score;
            }
            heuristicStats.update(evaluationResult);
            return val;
        } else if(levelType == MinimaxLevelType.Max){
            Map<GameState, Direction> allNextGameStateBySliding = gameStateManager.getAllNextGameStateBySliding(gameState);
            for(GameState childGameState : allNextGameStateBySliding.keySet()){
                if (childGameState.equals(gameState)) {
                    continue;
                }
                double val = evaluate(heuristic, childGameState, depth - 1, MinimaxLevelType.getOppositeType(levelType), alpha, beta);
                if(val > alpha){
                    alpha = val;
                }
                if(alpha >= beta){
                    break;
                }
            }
            return alpha;
        } else {
            Set<GameState> allNextGameStateByAddingNewTile = gameStateManager.getAllNextGameStateByAddingNewTile(gameState);
            for(GameState childGameState : allNextGameStateByAddingNewTile){
                if (childGameState.equals(gameState)) {
                    continue;
                }
                double val = evaluate(heuristic, childGameState, depth - 1, MinimaxLevelType.getOppositeType(levelType), alpha, beta);
                if(val < beta){
                    beta = val;
                }
                if(alpha >= beta){
                    break;
                }
            }
            return beta;
        }
    }
}
