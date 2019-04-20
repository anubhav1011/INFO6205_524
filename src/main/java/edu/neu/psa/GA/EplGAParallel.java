package edu.neu.psa.GA;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import edu.neu.psa.Actors.MasterActor;

import java.util.List;

public class EplGAParallel {


    public static void main(String[] args) {
        Database database = initializeDatabase();
        GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm(100, 0.01, 0.95, 4);
        Population population = geneticAlgorithm.initPopulation(database);
        final ActorSystem actorSystem = ActorSystem.create("Epl-GA-System");
        ActorRef masterActor = actorSystem.actorOf(MasterActor.props());
        masterActor.tell(new MasterActor.Init(), null);
        // Props props = MasterActor.props();
        geneticAlgorithm.evalPopulation(population, database);
        int generation = 1;
        while (geneticAlgorithm.isTerminationConditionMet(population) == false) {
            // Print fittest genotype from population
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
        System.out.println("Best solution: " + population.getFittest().getFitness());
        Genotype genotype = population.getGenotypes().stream()
                .filter(ind -> ind.getFitness() == 1)
                .findAny().orElse(null);
        System.out.println(genotype.getFitness());
        System.out.println("Best solution: " + population.getFittest().toString());
        System.out.println();
        System.out.println("#######################");
        System.out.println("English Premier League Schedule");
        System.out.println("#######################");
        System.out.println();
        Genotype fittest = population.getFittest();
        database.createSeasonSchedule(fittest);
        List<MatchSchedule> seasonSchedule = database.getSeasonSchedule();
        for (MatchSchedule matchSchedule : seasonSchedule) {
            int matchDayNumber = seasonSchedule.indexOf(matchSchedule) + 1;
            System.out.println("MatchSchedule: " + matchDayNumber);
            for (Match match : matchSchedule.getMatches()) {
                Integer[] match1 = match.getMatch();
                int matchNumber = matchSchedule.getMatches().indexOf(match) + 1;
                Team teamA = database.getTeamBasedOnId(match1[0]);
                Team teamB = database.getTeamBasedOnId(match1[1]);
                System.out.println("Match " + matchNumber + ": " + teamA.getTeamName() + " (H)" + " Vs " + teamB.getTeamName() + " (A)");

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
