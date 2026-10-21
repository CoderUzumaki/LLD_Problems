package com.abhinav.lld.tictactoegame.core;

import com.abhinav.lld.tictactoegame.enums.Symbol;
import com.abhinav.lld.tictactoegame.interfaces.BoardGame;
import com.abhinav.lld.tictactoegame.interfaces.GameState;
import com.abhinav.lld.tictactoegame.interfaces.PlayerStrategy;

public class TicTacToeGame implements BoardGame {
    private Board board;
    private Player playerX;
    private Player playerO;
    private Player currentPlayer;
    private GameContext gameContext;

    public TicTacToeGame(PlayerStrategy xStrategy, PlayerStrategy oStrategy, int row, int column) {
        this.board = new Board(row, column);
        this.playerX = new Player(Symbol.X, xStrategy);
        this.playerO = new Player(Symbol.O, oStrategy);
        this.currentPlayer = playerX;
        this.gameContext = new GameContext();
    }

    @Override
    public void play() {
        board.renderBoard();

        do {
            Position move = currentPlayer.makeMove(board);
            board.registerMove(move, currentPlayer.getSymbol());
            board.renderBoard();
            board.checkGameState(gameContext);

            if (!gameContext.isGameOver()) {
                switchPlayer();
            }
        } while (!gameContext.isGameOver());

        anounceResult();
    }

    @Override
    public Player switchPlayer() {
        if (currentPlayer == playerX) {
            currentPlayer = playerO;
        } else {
            currentPlayer = playerX;
        }

        return currentPlayer;
    }

    @Override
    public void anounceResult() {
        if (!gameContext.isGameOver()) return;

        GameState currentState = gameContext.getCurrentState();

        if (currentState instanceof XWonState) {
            System.out.printf("%s (X) Won!\n", playerX.getName());
        } else if (currentState instanceof OWonState) {
            System.out.printf("%s (O) Won!\n", playerO.getName());
        } else {
            System.out.println("It's a tie!");
        }
    }
}
