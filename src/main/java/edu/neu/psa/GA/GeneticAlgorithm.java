package edu.neu.psa.GA;

public class GeneticAlgorithm {

    private int populationSize;
    private double mutationRate;
    private double crossoverRate;
    //private int elitismCount;


    public GeneticAlgorithm(int populationSize, double mutationRate, double crossoverRate, int elitismCount) {
        this.populationSize = populationSize;
        this.mutationRate = mutationRate;
        this.crossoverRate = crossoverRate;
        //this.elitismCount = elitismCount;
    }


    public Population initPopulation(int chromosomeLength) {
        Population population = new Population(this.populationSize, chromosomeLength);
        return population;
    }

    public double calculateFitness(Individual individual) {
        // Track number of correct genes
        int correctGenes = 0;
        // Loop over individual’s genes
        for (int geneIndex = 0; geneIndex < individual.getChromosomeLength(); geneIndex++) {
            // Add one fitness point for each "1" found
            if (individual.getGene(geneIndex) == 1) {
                correctGenes += 1;
            }
        }
        // Calculate fitness
        double fitness = (double) correctGenes / individual.getChromosomeLength();
        // Store fitness
        individual.setFitness(fitness);
        return fitness;
    }

    public void evalPopulation(Population population) {
        //double populationFitness = 0;
        double populationFitness = population.getIndividuals().stream()
                .mapToDouble(x -> x.getFitness())
                .sum();
        population.setPopulationFitness(populationFitness);
    }

    public boolean isTerminationConditionMet(Population population) {
        boolean isTerminationConditionMet = population.getIndividuals().stream()
                .anyMatch(ind -> ind.getFitness() == 1);
        return isTerminationConditionMet;
    }

}
