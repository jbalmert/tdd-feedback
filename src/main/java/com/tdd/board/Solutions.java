package com.tdd.board;

import java.util.Set;

public class Solutions {
    private Set<Solution> solutionSet;

    public Solutions(Set<Solution> solutionSet) {
        this.solutionSet = solutionSet;
    }

    public boolean matches(Set<Square> squares) {
        for (Solution solution: solutionSet) {
            if (solution.matches(squares)) return true;
        }
        return false;
    }
}
