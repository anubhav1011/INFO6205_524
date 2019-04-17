package edu.neu.psa.GA;

import java.util.List;

public class GeneticAlgorithm {

    private int populationSize;
    private double mutationRate;
    private double crossoverRate;
    private int elitismCount;


    public GeneticAlgorithm(int populationSize, double mutationRate, double crossoverRate, int elitismCount) {
        this.populationSize = populationSize;
        this.mutationRate = mutationRate;
        this.crossoverRate = crossoverRate;
        this.elitismCount = elitismCount;
    }


    public Population initPopulation(Database database) {
        Population population = new Population(this.populationSize, database);
        return population;
    }

    public double calculateFitness(Individual individual, Database database) {
        // Create new database object to use -- cloned from an existing database
        Database threadDatabase = new Database(database);
        threadDatabase.createSeasonSchedule(individual);
        int clashes = threadDatabase.calculateClashes();
        double fitness = 1 / (double) (clashes + 1);
        individual.setFitness(fitness);
        return fitness;

    }

    public void evalPopulation(Population population, Database database) {
        //double populationFitness = 0;
        double populationFitness = population.getIndividuals().stream()
                .mapToDouble(x -> calculateFitness(x, database))
                .sum();
        population.setPopulationFitness(populationFitness);
        //sort the population based on fitness
        population.sortBasedOnFitness();


    }

    public boolean isTerminationConditionMet(Population population) {
        boolean isTerminationConditionMet = population.getIndividuals().stream()
                .anyMatch(ind -> ind.getFitness() == 1);
        return isTerminationConditionMet;
    }


    public Individual selectParent(Population population) {
        List<Individual> individuals = population.getIndividuals();
        double populationFitness = population.getPopulationFitness();
        double rouletteWheelPosition = Math.random() * populationFitness;
        double spinWheel = 0;
        for (Individual individual : individuals) {
            spinWheel += individual.getFitness();
            if (spinWheel >= rouletteWheelPosition) {
                return individual;
            }
        }
        // Get individuals
        return individuals.get(population.populationSize() - 1);
    }


    public Population crossoverPopulation(Population population) {
        // Create new population
        Population newPopulation = new Population();
        // Loop over current population by fitness
        for (int populationIndex = 0; populationIndex < population.populationSize(); populationIndex++) {
            Individual parent1 = population.getFittest();
            // Apply crossover to this individual?
            if (this.crossoverRate > Math.random() && populationIndex >= this.elitismCount) {
                // Initialize offspring
                Individual offspring = new Individual(parent1.getChromosomeLength());
                // Find second parent
                Individual parent2 = selectParent(population);
                // Loop over genome
                for (int geneIndex = 0; geneIndex < parent1.getChromosomeLength(); geneIndex++) {
                    // Use half of parent1's genes and half of parent2's genes
                    if (0.5 > Math.random()) {
                        offspring.setGene(geneIndex, parent1.getGene(geneIndex));
                    } else {
                        offspring.setGene(geneIndex, parent2.getGene(geneIndex));
                    }
                }
                // Add offspring to new population
                newPopulation.setIndividual(populationIndex, offspring);
            } else {
                // Add individual to new population without applying crossover
                newPopulation.setIndividual(populationIndex, parent1);
            }
        }
        return newPopulation;
    }


    public Population mutatePopulation(Population population) {
        population.sortBasedOnFitness();
        // Initialize new population
        Population newPopulation = new Population();
        // Loop over current population by fitness
        for (int populationIndex = 0; populationIndex < population.populationSize(); populationIndex++) {
            Individual individual = population.getFittest();
            // Loop over individual’s genes
            for (int geneIndex = 0; geneIndex < individual.getChromosomeLength(); geneIndex++) {
                // Skip mutation if this is an elite individual
                if (populationIndex >= this.elitismCount) {
                    // Does this gene need mutation?
                    if (this.mutationRate > Math.random()) {
                        // Get new gene
                        int newGene = 1;
                        if (individual.getGene(geneIndex) == 1) {
                            newGene = 0;
                        }
                        // Mutate gene
                        individual.setGene(geneIndex, newGene);
                    }
                }
            }
            // Add individual to population
            newPopulation.setIndividual(populationIndex, individual);
        }
        // Return mutated population
        return newPopulation;
    }
}
