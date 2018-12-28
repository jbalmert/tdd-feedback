package com.tdd.player;

import java.util.ArrayList;
import java.util.List;

public class PlayerBroadcaster {
    List<CurrentPlayerAware> listeners = new ArrayList<>();

    public void broadcastCurrentPlayer(Player player) {
        for (CurrentPlayerAware listener : listeners) {
            listener.currentPlayer(player);
        }
    }

    public void register(CurrentPlayerAware listener) {
        listeners.add(listener);
    }
}
