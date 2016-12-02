package com.ai.model;

/**
 * Created by z on 12/2/16.
 */
public enum MinimaxLevelType {
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
