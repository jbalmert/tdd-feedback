package com.tdd.board;

import java.util.HashSet;
import java.util.Set;

public class MoveValidator {

    Set<Square> availableMoves = new HashSet<>();
    public MoveValidator() {
        availableMoves.add(Square.LEFT_TOP);
        availableMoves.add(Square.MIDDLE_TOP);
        availableMoves.add(Square.RIGHT_TOP);
        availableMoves.add(Square.LEFT_MIDDLE);
        availableMoves.add(Square.CENTER);
        availableMoves.add(Square.RIGHT_MIDDLE);
        availableMoves.add(Square.LEFT_BOTTOM);
        availableMoves.add(Square.MIDDLE_BOTTOM);
        availableMoves.add(Square.RIGHT_BOTTOM);

    }

    public boolean isValid(Square square) {
        return availableMoves.remove(square);
    }
}
