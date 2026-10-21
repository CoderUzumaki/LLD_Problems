package com.abhinav.lld.tictactoegame.core;

import com.abhinav.lld.tictactoegame.interfaces.PlayerStrategy;

import java.util.Scanner;

public class HumanPlayerStrategy implements PlayerStrategy {
    private Scanner scanner;
    private String playerName;

    public HumanPlayerStrategy(String playerName) {
        this.scanner = new Scanner(System.in);
        this.playerName = playerName;
    }

    @Override
    public Position makeMove(Board board) {

        while (true) {
            System.out.printf("%s enter your move (rows [0, 2], cols[0, 2]): ", playerName);

            try {
                int row = scanner.nextInt();
                int col = scanner.nextInt();
                Position move = new Position(row, col);

                if (board.isValidMove(move)) {
                    return move;
                }

                System.out.println("Invalid move. Please try again.");
            } catch (Exception e) {
                System.out.println("Invalid input. Try again.");
                scanner.nextLine();
            }
        }
    }

    public String getPlayerName() {
        return playerName;
    }
}
