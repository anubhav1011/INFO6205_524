package edu.neu.psa.GA;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Database {

    private final HashMap<Integer, Team> teams;
    private List<MatchDay> seasonSchedule;


    public Database() {
        this.teams = new HashMap<>();
        this.seasonSchedule = new ArrayList<>();
    }

    public Database(Database cloneable) {
        this.teams = cloneable.teams;
        this.seasonSchedule = new ArrayList<>();
    }


    public void addTeam(int teamId, String teamName) {
        this.teams.put(teamId, new Team(teamId, teamName));
    }

    public void createSeasonSchedule(Individual individual) {
        int numberOfTeams = teams.size();
        int matchDays = (numberOfTeams - 1) * 2;
        int[] chromosome = individual.getChromosome();
        int chromsoPos = 0;
        int seasonSchedulePos = 0;
        for (int i = 0; i < matchDays; i++) {
            MatchDay matchDay = new MatchDay();
            for (int j = 0; j < numberOfTeams / 2; j++) {
                int teamA = chromsoPos++;
                int teamB = chromsoPos++;
                Match match = new Match(chromosome[teamA], chromosome[teamB]);
                matchDay.add(match);

            }
            this.seasonSchedule.add(matchDay);

        }
        //System.out.println(this.seasonSchedule);
    }


    public int getNumberOfTeams() {
        return this.teams.size();
    }


    public Team getRandomTeam() {
        Object[] teamArrays = this.teams.values().toArray();
        int randomId = (int) (teamArrays.length * Math.random());
        Team team = (Team) teamArrays[randomId];
        return team;


    }


    public int calculateClashes() {
        int clashes = 0;
        int numberOfTimeSameMatchBeingPlayed;
        int teamsPlayingMultipleMatchesSameDay = 0;
        int teamsPlayingAgainstEachOther;
        //Get all matches for a particular schedule
        List<Match> allMatches = seasonSchedule.stream()
                .flatMap(x -> x.getMatches().stream())
                .collect(Collectors.toList());
        // Calculate number of times same match being played
        Map<Match, Long> matchesMap = allMatches.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        numberOfTimeSameMatchBeingPlayed = matchesMap.values().stream()
                .filter(x -> x.intValue() > 1)
                .mapToInt(x -> x.intValue())
                .sum();
//        if (numberOfTimeSameMatchBeingPlayed != 0) {
//            numberOfTimeSameMatchBeingPlayed = numberOfTimeSameMatchBeingPlayed / 2;
//        }
        for (MatchDay matchDay : seasonSchedule) {
            Integer[] matchDayChromosome = matchDay.getMatches().stream()
                    .flatMap(x -> Stream.of(x.getMatch()))
                    .toArray(Integer[]::new);
            Map<Integer, Long> teamPlayingMultipleTimesSameDayMap = Arrays.asList(matchDayChromosome).stream()
                    .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
            int sum = teamPlayingMultipleTimesSameDayMap.values().stream()
                    .filter(x -> x.intValue() > 1)
                    .mapToInt(x -> x.intValue())
                    .sum();
//            if (sum != 0) {
//                sum = sum / 2;
//            }
            teamsPlayingMultipleMatchesSameDay = teamsPlayingMultipleMatchesSameDay + sum;
//            if (teamsPlayingMultipleMatchesSameDay != 0) {
//                teamsPlayingMultipleMatchesSameDay = teamsPlayingMultipleMatchesSameDay + teamsPlayingMultipleMatchesSameDay / 2;
//
//
//            }
        }
        long teamsPlayingAgainstEachOtherLong = allMatches.stream().filter(x -> x.getMatch()[0] == x.getMatch()[1])
                .count();
        teamsPlayingAgainstEachOther = (int) teamsPlayingAgainstEachOtherLong;
        clashes = numberOfTimeSameMatchBeingPlayed + teamsPlayingMultipleMatchesSameDay + teamsPlayingAgainstEachOther;
        return clashes;


    }

    public List<MatchDay> getSeasonSchedule() {
        return seasonSchedule;
    }


    public Team getTeamBasedOnId(int teamId) {
        return this.teams.get(teamId);
    }
}
