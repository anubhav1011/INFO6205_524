package edu.neu.psa.GA;

import java.util.List;

public class EPLGA {

    public static void main(String[] args) {
        Database database = initializeDatabase();
        GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm(200, 0.01, 0.8, 5);
        Population population = geneticAlgorithm.initPopulation(database);
        geneticAlgorithm.evalPopulation(population, database);
        int generation = 1;
        while (geneticAlgorithm.isTerminationConditionMet(population) == false) {
            // Print fittest individual from population
            System.out.println("Best solution: " + population.getFittest().getFitness());
            // Apply crossover
            population = geneticAlgorithm.crossoverPopulation(population);
            // Apply mutation
            population = geneticAlgorithm.mutatePopulation(population, database);
            // Evaluate population
            geneticAlgorithm.evalPopulation(population, database);
            // Increment the current generation
            generation++;
        }
        System.out.println("Found solution in " + generation + " generations");
        System.out.println("Best solution: " + population.getFittest().toString());
        System.out.println();
        System.out.println("#######################");
        System.out.println("Premier League Schedule");
        System.out.println("#######################");
        System.out.println();
        Individual fittest = population.getFittest();
        database.createSeasonSchedule(fittest);
        List<MatchDay> seasonSchedule = database.getSeasonSchedule();
        for (MatchDay matchDay : seasonSchedule) {
            int matchDayNumber = seasonSchedule.indexOf(matchDay) + 1;
            System.out.println("MatchDay: " + matchDayNumber);
            for (Match match : matchDay.getMatches()) {
                Integer[] match1 = match.getMatch();
                int matchNumber = matchDay.getMatches().indexOf(match) + 1;
                Team teamA = database.getTeamBasedOnId(match1[0]);
                Team teamB = database.getTeamBasedOnId(match1[1]);
                System.out.println("Match " + matchNumber + ": " + teamA.getTeamName() + " (H) " + " Vs " + teamB.getTeamName() + " (A) ");

            }
            System.out.println();


        }


    }


    public static Database initializeDatabase() {
        Database database = new Database();
        database.addTeam(1, "Manchester United");
        database.addTeam(2, "Manchester City");
        database.addTeam(3, "Chelsea");
        database.addTeam(4, "Liverpool");
        database.addTeam(5, "Tottenham");
        database.addTeam(6, "Arsenal");
//        database.addTeam(7, "Everton");
//        database.addTeam(8, "Wolves");
//        database.addTeam(9, "Leicester City");
//        database.addTeam(10, "Southampton");
        return database;

    }

}

