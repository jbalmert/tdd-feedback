package com.tdd.board;

import org.junit.Test;
import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.junit.Assert.*;

public class MoveValidatorTest {

    @Test
    public void aMoveMayOnlyBeMadeOnce() {
        MoveValidator validator = new MoveValidator();

        assertThat(validator.isValid(Square.CENTER)).isTrue();
        assertThat(validator.isValid(Square.CENTER)).isFalse();
    }

    @Test
    public void ANullMoveIsNotValid() {
        MoveValidator validator = new MoveValidator();

        assertThat(validator.isValid(null)).isFalse();
    }
}