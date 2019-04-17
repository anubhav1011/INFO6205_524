package edu.neu.psa.GA;

import java.util.Arrays;

public class Individual {

    private int[] chromosome;
    private double fitness = -1;


    public Individual(int[] chromosome) {
        this.chromosome = chromosome;
    }


    public Individual(Database database) {
        int numberOfTeams = database.getNumberOfTeams();
        int matchesPlayedByEachTeam = (numberOfTeams - 1) * 2;

        /* Ex: numberOfTeams = 4

         * Each team plays each other twice (Home and Away)
         * Every team plays 6 matches.
         * Chromosome Length is the number of matches played by each team
         * In this case - 4 * 6 = 24
         *
         * */
        int chromosomeLength = matchesPlayedByEachTeam * numberOfTeams;
        this.chromosome = new int[chromosomeLength];
        int chromosomeIndex = 0;
        for (int i = 0; i < chromosomeLength / 2; i++) {
            int team1 = database.getRandomTeam().getTeamId();
            this.chromosome[chromosomeIndex] = team1;
            chromosomeIndex++;
            int team2 = database.getRandomTeam().getTeamId();
            this.chromosome[chromosomeIndex] = team2;
            chromosomeIndex++;

        }
        //System.out.println(Arrays.toString(this.chromosome));
    }

    public Individual(int chromosomeLength) {
        // Create random individual
        int[] individual;
        individual = new int[chromosomeLength];
        /**
         * This comment and the for loop doesn't make sense for this chapter.
         * But I'm leaving it in here because you were instructed to copy this
         * class from Chapter 4 -- and NOT having this comment here might be
         * more confusing than keeping it in.
         *
         * Comment from Chapter 4:
         *
         * "In this case, we can no longer simply pick 0s and 1s -- we need to
         * use every city index available. We also don't need to randomize or
         * shuffle this chromosome, as crossover and mutation will ultimately
         * take care of that for us."
         */
        for (int gene = 0; gene < chromosomeLength; gene++) {
            individual[gene] = gene;
        }
        this.chromosome = individual;
    }

    public int getChromosomeLength() {
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

    public int getGene(int geneIndex) {
        return chromosome[geneIndex];
    }

    public void setGene(int geneIndex, int gene) {
        this.chromosome[geneIndex] = gene;
    }
}
