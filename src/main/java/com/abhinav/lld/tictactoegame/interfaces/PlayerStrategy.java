package com.abhinav.lld.tictactoegame.interfaces;

import com.abhinav.lld.tictactoegame.core.Board;
import com.abhinav.lld.tictactoegame.core.Position;

public interface PlayerStrategy {
    Position makeMove(Board board);
    String getPlayerName();
}
