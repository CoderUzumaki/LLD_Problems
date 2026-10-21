package com.abhinav.lld.tictactoegame.core;

import com.abhinav.lld.tictactoegame.enums.Symbol;
import com.abhinav.lld.tictactoegame.interfaces.PlayerStrategy;

public class Player {
    private final Symbol symbol;
    private final PlayerStrategy strategy;

    public Player(Symbol symbol, PlayerStrategy strategy) {
        this.symbol = symbol;
        this.strategy = strategy;
    }

    Position makeMove(Board board) {
        return strategy.makeMove(board);
    }

    Symbol getSymbol() {
        return symbol;
    }

    String getName() {
        return strategy.getPlayerName();
    }
}
