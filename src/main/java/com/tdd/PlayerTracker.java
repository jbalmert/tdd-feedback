package com.tdd;


public class PlayerTracker {

    private PlayerToggle playerToggle;
    private PlayerBroadcaster playerBroadcaster;

    public PlayerTracker(PlayerToggle playerToggle, PlayerBroadcaster playerBroadcaster) {
        this.playerToggle = playerToggle;
        this.playerBroadcaster = playerBroadcaster;
    }

    public void nextTurn() {
        Player nextPlayer = playerToggle.nextPlayer();
        playerBroadcaster.broadcastCurrentPlayer(nextPlayer);
    }

}
