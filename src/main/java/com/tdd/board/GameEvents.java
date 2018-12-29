package com.tdd.board;

import com.tdd.player.Player;

public interface GameEvents {
    void move(Player x, Square center);
    void wins(Player player);
}
