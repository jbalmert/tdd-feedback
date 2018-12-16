package com.tdd;

import java.util.ArrayList;
import java.util.List;

public class PlayerTracker {
    List<CurrentPlayerAware> listeners = new ArrayList<>();
    private Player currentPlayer = Player.X;

    public void nextTurn() {
        notifyListeners();
        togglePlayer();
    }

    private void notifyListeners() {
        for (CurrentPlayerAware listener : listeners) {
            listener.currentPlayer(currentPlayer);
        }
    }

    public void register(CurrentPlayerAware currentPlayerAware) {
        this.listeners.add(currentPlayerAware);
    }

    private void togglePlayer() {
        currentPlayer = currentPlayer == Player.X ? Player.O : Player.X;
    }
}
