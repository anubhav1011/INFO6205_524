package edu.neu.psa.GA;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Database {

    private final HashMap<Integer, Team> teams;
    private List<MatchDay> seasonSchedule;


    public Database(HashMap<Integer, Team> teams) {
        this.teams = teams;
        this.seasonSchedule = new ArrayList<>();
    }


    public void addTeam(int teamId, String teamName) {
        this.teams.put(teamId, new Team(teamId, teamName));
    }

    public void createSeasonSchedule(Individual individual) {
        int numberOfTeams = teams.size();
        int matchDays = (numberOfTeams - 1) * 2;
        int[] chromosome = individual.getChromosome();
        int chromsoPos = -1;
        int seasonSchedulePos = 0;
        for (int i = 0; i < matchDays; i++) {
            MatchDay matchDay = new MatchDay();
            for (int j = 0; j < numberOfTeams / 2; j++) {
                Match match = new Match(chromosome[chromsoPos++], chromosome[chromsoPos++]);
                matchDay.add(match);

            }
            this.seasonSchedule.add(matchDay);

        }


    }

}
