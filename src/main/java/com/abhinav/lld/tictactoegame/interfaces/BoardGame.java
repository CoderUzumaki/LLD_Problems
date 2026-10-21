package com.abhinav.lld.tictactoegame.interfaces;

import com.abhinav.lld.tictactoegame.core.Player;

public interface BoardGame {
    void play();
    Player switchPlayer();
    void anounceResult();
}
