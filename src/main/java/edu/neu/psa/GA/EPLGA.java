package edu.neu.psa.GA;

public class EPLGA {

    public static void main(String[] args) {
        Database database = initializeDatabase();
        GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm(100, 0.01, 0.95, 0);
        Population population = geneticAlgorithm.initPopulation(database);
        geneticAlgorithm.evalPopulation(population, database);
        int generation = 1;
        while (geneticAlgorithm.isTerminationConditionMet(population) == false) {
            // Print fittest individual from population
            System.out.println("Best solution: " + population.getFittest().toString());
            // Apply crossover
            population = geneticAlgorithm.crossoverPopulation(population);
            // Apply mutation
            population = geneticAlgorithm.mutatePopulation(population);
            // Evaluate population
            geneticAlgorithm.evalPopulation(population, database);
            // Increment the current generation
            generation++;
        }
        System.out.println("Found solution in " + generation + " generations");
        System.out.println("Best solution: " + population.getFittest().toString());
    }


    public static Database initializeDatabase() {
        Database database = new Database();
        database.addTeam(1, "Team A");
        database.addTeam(2, "Team B");
        database.addTeam(3, "Team C");
        database.addTeam(4, "Team D");
        return database;

    }

}

