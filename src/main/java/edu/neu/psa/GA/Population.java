package edu.neu.psa.GA;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Population {

    private List<Individual> population;

    private double populationFitness = -1;


    public Population() {
        this.population = new ArrayList<>();
    }

    public Population(int populationSize, Database database) {
        this.population = new ArrayList<>();
        for (int i = 0; i < populationSize; i++) {
            Individual individual = new Individual(database);
            this.population.add(individual);
        }

    }


    public void sortBasedOnFitness() {
        this.population = this.population.stream().sorted((x, y) -> {
            double xFitness = x.getFitness();
            double yFitness = y.getFitness();
            if (xFitness > yFitness) {
                return -1;
            } else {
                return 0;
            }
            // return 0;
        }).collect(Collectors.toList());


    }


    public Individual getFittest() {
        return this.population.get(0);


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


    public int populationSize() {
        return this.population.size();
    }

    public void setIndividual(int populationIndex, Individual parent1) {
        this.population.set(populationIndex, parent1);

    }
}
