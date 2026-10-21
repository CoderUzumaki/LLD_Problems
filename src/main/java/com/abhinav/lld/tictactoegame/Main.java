package com.abhinav.lld.tictactoegame;

import com.abhinav.lld.tictactoegame.core.HumanPlayerStrategy;
import com.abhinav.lld.tictactoegame.core.TicTacToeGame;
import com.abhinav.lld.tictactoegame.interfaces.BoardGame;
import com.abhinav.lld.tictactoegame.interfaces.PlayerStrategy;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        final int ROW = 3, COL = 3;

        System.out.println("Welcome to Tic-Tac-Toe Game!");

        System.out.print("Enter player name for X: ");
        String xPlayerName = input.nextLine();

        System.out.print("Enter player name for O: ");
        String oPlayerName = input.nextLine();

        PlayerStrategy xStrategy = new HumanPlayerStrategy(xPlayerName);
        PlayerStrategy oStrategy = new HumanPlayerStrategy(oPlayerName);

        BoardGame tictactoe = new TicTacToeGame(xStrategy, oStrategy, ROW, COL);
        tictactoe.play();
    }
}
