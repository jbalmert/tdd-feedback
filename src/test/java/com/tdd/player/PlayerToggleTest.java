package com.tdd.player;

import static org.assertj.core.api.Java6Assertions.assertThat;

import com.tdd.player.Player;
import com.tdd.player.PlayerToggle;
import org.junit.Test;


public class PlayerToggleTest {

    PlayerToggle toggle = new PlayerToggle();

    @Test
    public void returnsPlayerXOnFirstCall() throws Exception {
        Player result = toggle.nextPlayer();

        assertThat(result).isSameAs(Player.X);
    }

    @Test
    public void togglesBetweenXAndOWithEachCall() throws Exception {
        Player result1 = toggle.nextPlayer();
        Player result2 = toggle.nextPlayer();
        Player result3 = toggle.nextPlayer();

        assertThat(result1).isSameAs(Player.X);
        assertThat(result2).isSameAs(Player.O);
        assertThat(result3).isSameAs(Player.X);
    }
}