package com.ai.model;

/**
 * Created by z on 12/2/16.
 */
public enum Direction {
    Up(0),
    Right(1),
    Down(2),
    Left(3);

    Direction(int numVal) {
        this.numVal = numVal;
    }
    public int numVal;
}
