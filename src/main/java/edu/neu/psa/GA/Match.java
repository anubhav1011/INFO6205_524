package edu.neu.psa.GA;

public class Match {

    private int[] match;

    public Match(int homeTeam, int awayTeam) {
        this.match = new int[2];
        this.match[0] = homeTeam;
        this.match[1] = awayTeam;

    }
}
