package edu.neu.psa.GA;

public class Individual {

    private int[] chromosome;
    private double fitness = -1;


    public Individual(int[] chromosome) {
        this.chromosome = chromosome;
    }


    public Individual(int chromosomeLength) {
        this.chromosome = new int[chromosomeLength];
        for (int gene = 0; gene < chromosomeLength; gene++) {
            if (0.5 < Math.random()) {
                chromosome[gene] = 0;
            } else {
                chromosome[gene] = 1;
            }
        }

    }

    public int chromosomeLength() {
        return chromosome.length;
    }

    public int[] getChromosome() {
        return chromosome;
    }

    public double getFitness() {
        return fitness;
    }

    public void setFitness(double fitness) {
        this.fitness = fitness;
    }
}
