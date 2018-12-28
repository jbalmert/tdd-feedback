package com.tdd.board;

import java.util.HashSet;
import java.util.Set;

public class Solution {
    Set<Square> solutionSet = new HashSet<>();
    public Solution(Square square1, Square square2, Square square3) {
        solutionSet.add(square1);
        solutionSet.add(square2);
        solutionSet.add(square3);
    }

    public boolean matches(Set<Square> squares) {
        return squares.containsAll(solutionSet);
    }
}
