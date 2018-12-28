package com.tdd;

import com.tdd.player.Player;

public interface GameEvents {

    void xWins();

    void move(Player x, Square center);
}
