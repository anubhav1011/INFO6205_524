package edu.neu.psa.GA;

public class EPLGA {

    public static void main(String[] args) {
        GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm(100, 0.01, 0.95, 0);
        Population population = geneticAlgorithm.initPopulation(50);
        geneticAlgorithm.evalPopulation(population);
        int generation = 1;
        while (geneticAlgorithm.isTerminationConditionMet(population) == false) {
            // Print fittest individual from population
            System.out.println("Best solution: " + population.getFittest().toString());
            // Apply crossover
            population = geneticAlgorithm.crossoverPopulation(population);
            // TODO!
            // Apply mutation
            population = geneticAlgorithm.mutatePopulation(population);
            // TODO!
            // Evaluate population
            geneticAlgorithm.evalPopulation(population);
            // Increment the current generation
            generation++;
        }
        System.out.println("Found solution in " + generation + " generations");
        System.out.println("Best solution: " + population.getFittest().toString());
    }

}

