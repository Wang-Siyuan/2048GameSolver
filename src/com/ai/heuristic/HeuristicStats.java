package com.ai.heuristic;

import com.ai.model.GameState;
import com.util.Log;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by z on 12/12/16.
 */
public class HeuristicStats {
    int updateCounter = 0;
    Map<Heuristic, Integer> dominanceCounter = new HashMap<>();
    Map<Heuristic, HeuristicEvaluation> bestEvaluation = new HashMap<>();
    Map<Heuristic, HeuristicEvaluation> worstEvaluation = new HashMap<>();

    public HeuristicStats() {

    }

    public void update(Map<Heuristic, HeuristicEvaluation> evaluationResult) {
        Heuristic dominatingHeuristic = getDominatingHeuristic(evaluationResult);
        if (!dominanceCounter.containsKey(dominatingHeuristic)) {
            dominanceCounter.put(dominatingHeuristic, 0);
        }
        dominanceCounter.put(dominatingHeuristic, dominanceCounter.get(dominatingHeuristic) + 1);
        for (Heuristic heuristic : evaluationResult.keySet()) {
            GameState gameState = evaluationResult.get(heuristic).gameState;
            double score = evaluationResult.get(heuristic).score;
            if (!bestEvaluation.containsKey(heuristic) || score > bestEvaluation.get(heuristic).score) {
                bestEvaluation.put(heuristic, new HeuristicEvaluation(gameState, score));
            }
            if (!worstEvaluation.containsKey(heuristic) || score < worstEvaluation.get(heuristic).score) {
                worstEvaluation.put(heuristic, new HeuristicEvaluation(gameState, score));
            }
        }
        if (updateCounter++ % 5000000 == 0) {
            Log.log(this.toString());
//            dominanceCounter = new HashMap<>();
//            bestEvaluation = new HashMap<>();
//            worstEvaluation = new HashMap<>();
        }
    }

    Heuristic getDominatingHeuristic(Map<Heuristic, HeuristicEvaluation> evaluationResult) {
        Heuristic dominantHeuristic = null;
        double bestScore = -1 * Double.MAX_VALUE;
        for (Heuristic heuristic : evaluationResult.keySet()) {
//            if (heuristic instanceof EmptyTilesHeuristic) {
//                continue;
//            }
            if (dominantHeuristic == null || evaluationResult.get(heuristic).score > bestScore) {
                bestScore = evaluationResult.get(heuristic).score;
                dominantHeuristic = heuristic;
            }
        }
        return dominantHeuristic;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Made " + updateCounter + " evaluations.\n");
        for (Heuristic heuristic : dominanceCounter.keySet()) {
            sb.append(String.format("Heuristic %s dominated %s times \n", heuristic.getClass().getSimpleName(), dominanceCounter.get(heuristic)));
        }
        sb.append("\nBest evaluations:\n");
        for (Heuristic heuristic : bestEvaluation.keySet()) {
            sb.append(heuristic.getClass().getSimpleName() + ": " + bestEvaluation.get(heuristic).score);
            sb.append(bestEvaluation.get(heuristic).gameState.toString());
        }
        sb.append("\nWorst evaluations:\n");
        for (Heuristic heuristic : worstEvaluation.keySet()) {
            sb.append(heuristic.getClass().getSimpleName() + ": " + worstEvaluation.get(heuristic).score);
            sb.append(worstEvaluation.get(heuristic).gameState.toString());
        }
        return sb.toString();
    }

}
