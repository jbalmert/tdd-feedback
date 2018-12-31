package com.tdd.board;

import com.tdd.player.Player;

import java.util.ArrayList;
import java.util.List;

public class GameEventBroadcaster implements GameEvents{
    List<GameEvents> eventsList = new ArrayList<>();

    public void register(GameEvents events) {
        eventsList.add(events);
    }

    @Override
    public void move(Player player, Square square) {
        for (GameEvents events : eventsList) {
            events.move(player, square);
        }
    }

    @Override
    public void invalidMove(Player player, Square square) {
        for (GameEvents events : eventsList) {
            events.invalidMove(player, square);
        }
    }

    @Override
    public void wins(Player player) {
        for (GameEvents events : eventsList) {
            events.wins(player);
        }
    }

    @Override
    public void draw() {
        for (GameEvents events : eventsList) {
            events.draw();
        }
    }
}
