package com.abhinav.lld.tictactoegame.interfaces;

import com.abhinav.lld.tictactoegame.core.GameContext;

public interface GameState {
    void next(GameContext context);
    boolean isGameOver();
}
