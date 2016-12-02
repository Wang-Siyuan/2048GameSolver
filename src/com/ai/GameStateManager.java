package com.ai;

import com.ai.model.Direction;
import com.ai.model.GameState;

import java.util.*;

/**
 * Created by z on 12/2/16.
 */
public class GameStateManager {
    List<Integer> POSSIBLE_NEW_TILE_VALUES = Arrays.asList(2, 4);

    public Set<GameState> getAllNextGameStateByAddingNewTile(GameState current) {
        Set<GameState> gameStates = new HashSet<>();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                for (int tileVal : POSSIBLE_NEW_TILE_VALUES) {
                    if (current.tileValues[i][j] == 0) {
                        GameState newGameState = addNewTileValue(current, tileVal, i, j);
                        gameStates.add(newGameState);
                    }
                }
            }
        }
        return gameStates;
    }

    public Map<GameState, Direction> getAllNextGameStateBySliding(GameState current) {
        Map<GameState, Direction> gameStates = new HashMap<>();
        for (Direction direction : EnumSet.allOf(Direction.class)) {
            gameStates.put(slide(current, direction), direction);
        }
        return gameStates;
    }

    public GameState addNewTileValue(GameState current, int val, int x, int y) {
        GameState newGameState = new GameState(current);
        if (newGameState.tileValues[x][y] != 0) {
            throw new RuntimeException("Cannot new tile on a grid position that already has a tile.");
        }
        newGameState.tileValues[x][y] = val;
        return newGameState;
    }

    public GameState slide(GameState current, Direction direction) {
        GameState ret = current;
        if (direction == Direction.Up) {
            for (int j = 0; j < 4; j++) {
                for (int i = 0; i < 4; i++) {
                    ret = slideSingleTileAndMerge(ret, i, j, direction);
                }
            }
        } else if (direction == Direction.Down) {
            for (int j = 0; j < 4; j++) {
                for (int i = 3; i >= 0; i--) {
                    ret = slideSingleTileAndMerge(ret, i, j, direction);
                }
            }
        } else if (direction == Direction.Left) {
            for (int i = 0; i < 4; i++) {
                for (int j = 3; j >= 0; j--) {
                    ret = slideSingleTileAndMerge(ret, i, j, direction);
                }
            }
        } else {
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    ret = slideSingleTileAndMerge(ret, i, j, direction);
                }
            }
        }
        return ret;
    }

    GameState slideSingleTileAndMerge(GameState current, int x, int y, Direction direction) {
        GameState newGameState = new GameState(current);
        int newPos = y;
        if (direction == Direction.Up) {
            for (int j = y - 1; j >= 0; j--) {
                if (newGameState.tileValues[x][j] == 0) {
                    newPos = j;
                } else if (newGameState.tileValues[x][j] == newGameState.tileValues[x][y]) {
                    newGameState.tileValues[x][j] = 2 * newGameState.tileValues[x][j];
                    newGameState.tileValues[x][y] = 0;
                    return newGameState;
                } else {
                    newGameState.tileValues[x][newPos] = newGameState.tileValues[x][y];
                    newGameState.tileValues[x][y] = 0;
                    return newGameState;
                }
            }
            newGameState.tileValues[x][0] = newGameState.tileValues[x][y];
            newGameState.tileValues[x][y] = 0;
        } else if (direction == Direction.Down) {
            for (int j = y + 1; j < 4; j++) {
                if (newGameState.tileValues[x][j] == 0) {
                    newPos = j;
                } else if (newGameState.tileValues[x][j] == newGameState.tileValues[x][y]) {
                    newGameState.tileValues[x][j] = 2 * newGameState.tileValues[x][j];
                    newGameState.tileValues[x][y] = 0;
                    return newGameState;
                } else {
                    newGameState.tileValues[x][newPos] = newGameState.tileValues[x][y];
                    newGameState.tileValues[x][y] = 0;
                    return newGameState;
                }
            }
            newGameState.tileValues[x][3] = newGameState.tileValues[x][y];
            newGameState.tileValues[x][y] = 0;
        } else if (direction == Direction.Left) {
            for (int i = x - 1; i >= 0; i--) {
                if (newGameState.tileValues[i][y] == 0) {
                    newPos = i;
                } else if (newGameState.tileValues[i][y] == newGameState.tileValues[x][y]) {
                    newGameState.tileValues[i][y] = 2 * newGameState.tileValues[i][y];
                    newGameState.tileValues[x][y] = 0;
                    return newGameState;
                } else {
                    newGameState.tileValues[newPos][y] = newGameState.tileValues[x][y];
                    newGameState.tileValues[x][y] = 0;
                    return newGameState;
                }
            }
            newGameState.tileValues[0][y] = newGameState.tileValues[x][y];
            newGameState.tileValues[x][y] = 0;
        } else {
            for (int i = x + 1; i < 4; i++) {
                if (newGameState.tileValues[i][y] == 0) {
                    newPos = i;
                } else if (newGameState.tileValues[i][y] == newGameState.tileValues[x][y]) {
                    newGameState.tileValues[i][y] = 2 * newGameState.tileValues[i][y];
                    newGameState.tileValues[x][y] = 0;
                    return newGameState;
                } else {
                    newGameState.tileValues[newPos][y] = newGameState.tileValues[x][y];
                    newGameState.tileValues[x][y] = 0;
                    return newGameState;
                }
            }
            newGameState.tileValues[3][y] = newGameState.tileValues[x][y];
            newGameState.tileValues[x][y] = 0;
        }
        return newGameState;
    }
}
