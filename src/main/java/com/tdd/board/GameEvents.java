package com.tdd.board;

import com.tdd.player.Player;

public interface GameEvents {
    void move(Player player, Square square);
    void invalidMove(Player player, Square square);
    void wins(Player player);
    void draw();
    void gameOver();
}
