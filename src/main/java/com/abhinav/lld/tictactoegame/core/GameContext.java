package com.abhinav.lld.tictactoegame.core;

import com.abhinav.lld.tictactoegame.interfaces.GameState;

public class GameContext {
    private GameState currentState;

    public GameContext() {
        currentState = new XTurnState();
    }

    public GameState setGameState(GameState gameState) {
        this.currentState = gameState;
        return currentState;
    }

    public void next() {
        currentState.next(this);
    }

    public boolean isGameOver() {
        return currentState.isGameOver();
    }

    public GameState getCurrentState() {
        return currentState;
    }
}
