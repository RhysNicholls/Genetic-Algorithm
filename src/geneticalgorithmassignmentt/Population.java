package geneticalgorithmassignmentt;

import java.util.*;

/**
 *
 * @author r24-nicholls
 */
public final class Population {

    Individual[] individuals;
    double populationFitness = 0;
    double populationAverage = 0;

    public Population(int populationSize) {
        individuals = new Individual[populationSize];
        for (int i = 0; i < individuals.length; i++) {
            Individual newIndividual = new Individual();
            newIndividual.generateIndividual();
            saveIndividual(i, newIndividual);

        }
    }

    public double getPopulationAverage() {
        return populationAverage;
    }

    public void setPopulationAverage(double populationAverage) {
        this.populationAverage = populationAverage;
    }

    public Individual[] getIndividuals() {
        return individuals;
    }

    public Individual getIndividual(int index) {
        return individuals[index];
    }

    public void setIndividuals(Individual[] individuals) {
        this.individuals = individuals;
    }

    public void saveIndividual(int index, Individual in) {
        individuals[index] = in;

    }

    public void setPopulationFitness(double fitness) {
        populationFitness = fitness;
    }

    public double getPopulationFitness() {
        return populationFitness;
    }

    public int size() {
        return individuals.length;
    }

    public Individual getFittest(int offset) {
        Arrays.sort(this.individuals, new Comparator<Individual>() {
            @Override
            public int compare(Individual o1, Individual o2) {
                if (o1.getFitness() > o2.getFitness()) {
                    return -1;
                } else if (o1.getFitness() < o2.getFitness()) {
                    return 1;
                }
                return 0;
            }
        });
        return this.individuals[offset];
    }
}
