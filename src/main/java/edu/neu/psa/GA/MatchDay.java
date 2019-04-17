package edu.neu.psa.GA;

import java.util.ArrayList;
import java.util.List;

public class MatchDay {
    private List<Match> matches;


    public MatchDay() {
        this.matches = new ArrayList<>();
    }

    public void add(Match match) {
        this.matches.add(match);
    }


    public List<Match> getMatches() {
        return matches;
    }
}
