package edu.neu.psa.GA;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Population {

    private List<Individual> population;

    private double populationFitness = -1;


    public Population(List<Individual> population) {
        this.population = population;
    }

    public Population(int populationSize, int chromosomeLength) {
        this.population = new ArrayList<>();
        for (int i = 0; i < populationSize; i++) {
            Individual individual = new Individual(chromosomeLength);
            this.population.add(individual);
        }

    }


    public Individual getFittest() {
        Individual fittestIndividual = this.population.stream().sorted((x, y) -> {
            double xFitness = x.getFitness();
            double yFitness = y.getFitness();
            if (xFitness > yFitness) {
                return -1;
            } else {
                return 0;
            }
            // return 0;
        }).collect(Collectors.<Individual>toList()).get(0);
        return fittestIndividual;


    }


    public double getPopulationFitness() {
        return populationFitness;
    }

    public void setPopulationFitness(double populationFitness) {
        this.populationFitness = populationFitness;
    }

    public List<Individual> getIndividuals() {
        return population;
    }

    public void shuffle() {
        Random random = new Random();
        for (int i = 0; i < population.size(); i++) {
            int index = random.nextInt(i + 1);
            Individual a = population.get(index);
            population.set(index, population.get(i));
            population.set(i, a);
        }
    }
}
