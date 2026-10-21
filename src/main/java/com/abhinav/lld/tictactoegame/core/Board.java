package com.abhinav.lld.tictactoegame.core;

import com.abhinav.lld.tictactoegame.enums.Symbol;

public class Board {
    private final int rows;
    private final int columns;
    private Symbol[][] grid;

    public Board(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;

        grid = new Symbol[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                grid[i][j] = Symbol.EMPTY;
            }
        }
    }

    public boolean isValidMove(Position position) {
        int row = position.getRow();
        int col = position.getCol();

        // Position is out of the playable grid
        if (row < 0 || row >= rows || col < 0 || col >= columns) {
            return false;
        }

        return grid[row][col] == Symbol.EMPTY;
    }

    public void registerMove(Position position, Symbol symbol) {
        int row = position.getRow();
        int col = position.getCol();
        grid[row][col] = symbol;
    }

    public void renderBoard() {
        for (int i = 0; i < rows; i++) {
            System.out.print(" ");
            for (int j = 0; j < columns; j++) {
                String printChar = switch (grid[i][j]) {
                    case EMPTY -> " ";
                    case X -> "X";
                    case O -> "O";
                };
                System.out.print(printChar);
                if (j != columns-1) System.out.print(" | ");
            }
            System.out.println();

            for (int j = 0; j < columns && i != rows-1; j++) {
                System.out.print("---");
                if (j != columns-1) System.out.print("|");
            }
            System.out.println();
        }
    }

    public void checkGameState(GameContext gameContext) {
        boolean playerWon = false;

        // Horizontal Win:
        for (int i = 0; i < rows; i++) {
            if (isWinningLine(grid[i])) {
                playerWon = true;
                break;
            }
        }

        // Vertical Win:
        for (int j = 0; j < columns && !playerWon; j++) {
            Symbol[] line = new Symbol[rows];
            for (int i = 0; i < rows; i++) {
                line[i] = grid[i][j];
            }

            if (isWinningLine(line)) {
                playerWon = true;
                break;
            }
        }

        // Diagonal Win:
        Symbol[] leftDiagonal = new Symbol[rows];
        Symbol[] rightDiagonal = new Symbol[rows];

        for (int i = 0; i < rows; i++) {
            leftDiagonal[i] = grid[i][i];
            rightDiagonal[i] = grid[i][columns - 1 - i];
        }

        if ( isWinningLine(leftDiagonal) || isWinningLine(rightDiagonal) ) playerWon = true;

        // Draw
        if (!playerWon) {
            // In Progress
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns; j++) {
                    if (grid[i][j] == Symbol.EMPTY) {
                        gameContext.next();
                        return;
                    }
                }
            }
            gameContext.setGameState(new DrawState());
            return;
        }

        if (gameContext.getCurrentState() instanceof XTurnState) {
            gameContext.setGameState(new XWonState());
        } else {
            gameContext.setGameState(new OWonState());
        }
    }

    public boolean isWinningLine(Symbol[] line) {
        for (int i = 1; i < line.length; i++) {
            if (line[i - 1] != line[i] || line[i] == Symbol.EMPTY) return false;
        }

        return true;
    }
}
