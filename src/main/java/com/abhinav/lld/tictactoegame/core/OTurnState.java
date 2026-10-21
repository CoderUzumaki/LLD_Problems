package com.abhinav.lld.tictactoegame.core;

import com.abhinav.lld.tictactoegame.interfaces.GameState;

public class OTurnState implements GameState {
    @Override
    public void next(GameContext context) {
        context.setGameState(new XTurnState());
    }

    @Override
    public boolean isGameOver() {
        return false;
    }
}
