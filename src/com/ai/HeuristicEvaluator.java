package com.ai;

import com.ai.heuristic.Heuristic;
import com.ai.model.GameTreeNode;

/**
 * Created by z on 12/2/16.
 */
public class HeuristicEvaluator {

    public int evaluate(Heuristic heuristic, GameTreeNode gameTreeNode, int depth, MinimaxLevelType levelType) {
        if (depth == 0) {
            return heuristic.evaluate(gameTreeNode.gameState);
        }
        int ret = (levelType == MinimaxLevelType.Min) ? Integer.MAX_VALUE : Integer.MIN_VALUE;
        for (GameTreeNode childNode : gameTreeNode.childNodes) {
            int val = evaluate(heuristic, childNode, depth - 1, MinimaxLevelType.getOppositeType(levelType));
            ret = (levelType == MinimaxLevelType.Min) ? Math.min(ret, val) : Math.max(ret, val);
        }
        return ret;
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
