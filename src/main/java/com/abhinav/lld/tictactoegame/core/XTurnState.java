package com.abhinav.lld.tictactoegame.core;

import com.abhinav.lld.tictactoegame.interfaces.GameState;

public class XTurnState implements GameState {
    @Override
    public void next(GameContext context) {
        context.setGameState(new OTurnState());
    }

    @Override
    public boolean isGameOver() {
        return false;
    }
}
