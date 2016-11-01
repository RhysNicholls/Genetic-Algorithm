package geneticalgorithmassignmentt;

import java.util.*;

/**
 *
 * @author r24-nicholls
 */
public final class Population {

    Individual[] individuals;
    double populationFitness = 0;

    public Population(int populationSize) {
        individuals = new Individual[populationSize];
        for (int i = 0; i < individuals.length; i++) {
            Individual newIndividual = new Individual();
            newIndividual.generateIndividual();
            saveIndividual(i, newIndividual);

        }
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

    public Individual getFittest() {
        Individual fittest = individuals[0];
        
        for (int i = 0; i < size(); i++) {
            if (fittest.getFitness() <= getIndividual(i).getFitness()) {
                fittest = getIndividual(i);
            }
        }
        return fittest;
    }
}
