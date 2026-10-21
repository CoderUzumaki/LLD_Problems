package com.abhinav.lld.tictactoegame.core;

import com.abhinav.lld.tictactoegame.interfaces.GameState;

public class OWonState implements GameState {
    @Override
    public void next(GameContext context) {}

    @Override
    public boolean isGameOver() {
        return true;
    }
}