package geneticalgorithmassignmentt;

import java.util.Arrays;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author Rhys
 */
public class GeneticAlgorithmAssignmentt {

    private double mutationRate;
    private double crossoverRate;

    public GeneticAlgorithmAssignmentt(double mutationRate, double crossoverRate) {
        this.mutationRate = mutationRate;
        this.crossoverRate = crossoverRate;
    }

    //Tested
    public boolean conditionMet(Population population) {
        for (Individual individual : population.getIndividuals()) {
            if (individual.getFitness() == 9) {
                return true;
            }
        }
        return false;
    }

    //Tested 
    /* public double calcFitness(Individual individual) {

        int noOnes = 0;
    
        for (int i = 0; i < individual.size(); i++) {
            if (individual.getGene(i) == 1) {
                noOnes++;

            }

        }
        double fitness = (double) noOnes / individual.size();

        individual.setFitness(fitness);

        return fitness;
    }*/
    public double calcFitness(Rule[] rules, Individual individual) {
        byte[] indi = individual.getGenes();
        Rule[] in = splitToRules(indi, 6);
        int fitnessCount = 0;
        boolean found = false;

        for (int j = 0; j < 9; j++) {
            found = false;

            for (int i = 0; i < 9 && !found; i++) {

                if (Arrays.equals(in[i].getVariables(), rules[j].getVariables())) {

                    if (rules[j].getOutcome() == in[i].getOutcome()) {
                        fitnessCount++;

                        found = true;

                    }

                }

            }
        }

        double fitness = (double) fitnessCount;

        individual.setFitness(fitness);

        return fitness;

    }

//    public double calcFitness(Rule[] rules, Individual individual) {
//        byte[] indi = individual.getGenes();
//        Rule[] in = splitToRules(indi, 6);
//        int fitnessCount = 0;
//        boolean found = false;
//        
//        System.out.println("Rules From Data File");
//        System.out.println("");
//        for (int i = 0; i < rules.length; i++) {
//            
//            System.out.println("Rule: "+ (i + 1) + " " + rules[i]);
//            
//        }
//        System.out.println("");
//        System.out.println("Sets Created From Individual");
//        System.out.println("");
//         for (int i = 0; i < in.length; i++) {
//            System.out.println("Set: "+ (i + 1) + " " + in[i]);
//            
//        }
//       
//        for (int j = 0; j < 9 ; j++) {
//            found = false;
//            System.out.println("");
//            System.out.println("Comparison " + (j+1));
//            System.out.println("");
//            System.out.println("Rule: " + j + " " + rules[j]);
//            System.out.println("");
//            for (int i = 0; i < 9 && !found ; i++) {
//
//                
//                System.out.println("Set: " + (i +1)  + " " + in[i]);
//              
//                if (Arrays.equals(in[i].getVariables(), rules[j].getVariables()) ) {
//                    System.out.println("Match");
//
//                    if (rules[j].getOutcome() == in[i].getOutcome()) {
//                        fitnessCount++;
//
//                        System.out.println("Outcome Match");
//                        
//                        found = true;
//
//                    }
//
//                }
//
//            }
//        }
//        System.out.println("");
//        double fitness = (double) fitnessCount;
//        System.out.println(fitness);
//        individual.setFitness(fitness);
//
//        return fitness;
//
//    }
    //Tested
    public void calcPopulationFitness(Rule[] rules, Population population) {

        double populationFitness = 0;
        for (Individual individual : population.getIndividuals()) {
            populationFitness = populationFitness + individual.getFitness();
        }
        population.setPopulationFitness(populationFitness);

    }

    //Tested
    public Individual crossover(Individual parent1, Individual parent2) {

        int crossoverPoint = (int) (Math.random() * parent1.size());
        // System.out.println(parent1 + " " + parent2 + " " + crossoverPoint);
        Individual offspring = new Individual();

        for (int i = 0; i < parent1.size(); i++) {
            if (i < crossoverPoint) {
                offspring.setGene(i, (byte) parent1.getGene(i));
            } else {
                offspring.setGene(i, (byte) parent2.getGene(i));
            }
        }

        return offspring;
    }

    //Tested
    public Individual tournementSelection(Rule[] rules, Population population, int size) {

        Population tournement = new Population(size);
        for (int i = 0; i < size; i++) {
            tournement.saveIndividual(i, population.getIndividual((int) (Math.random() * population.size())));
            calcFitness(rules, tournement.getIndividual(i));
        }

        return tournement.getFittest();
    }

    //Tested now replaced by tournementSelection
    public Individual parentSelect(Population population) {

        Individual best = new Individual();
        Individual individual1 = population.getIndividual((int) (Math.random() * population.size()));
        Individual individual2 = population.getIndividual((int) (Math.random() * population.size()));
        //System.out.println("Parent1: " + parent1);
        //System.out.println("Parent2: " + parent2);

        if (individual1.getFitness() >= individual2.getFitness()) {
            best = individual1;
        } else {
            best = individual2;
        }
        return best;
    }

    //Tested
    public void mutate(Individual individual) {

        for (int i = 0; i < individual.size(); i++) {

            if (mutationRate > Math.random()) {
                byte newGene = 1;
                if (individual.getGene(i) == 1) {
                    newGene = 0;
                }
                individual.setGene(i, newGene);

            }
            //System.out.println(individual);
        }

    }

    //Tested
    public byte[] convertFileToData(String file) throws FileNotFoundException {
        String data = new Scanner(new File(file)).useDelimiter("\\Z").next();
        String data2 = data.replaceAll("\\s", "");

        byte[] b = new byte[data2.length()];
        for (int i = 0; i < b.length; i++) {
            b[i] = (byte) (data2.charAt(i) - '0');
        }
        //System.out.println(Arrays.toString(b));

        return b;
    }

    //Tested
    public Rule[] splitToRules(byte[] data, int blockSize) {

        int blockCount = (data.length / blockSize) + 1;
        Rule[] rules = new Rule[blockCount - 1];
        byte[] range = null;

        for (int i = 1; i < blockCount; i++) {
            int idx = (i - 1) * blockSize;
            range = Arrays.copyOfRange(data, idx, idx + (blockSize));

            Rule r = new Rule(Arrays.copyOfRange(range, 0, 5), range[5]);
            //System.out.println("Rule " + i +": " + r);
            rules[i - 1] = r;
        }
        //System.out.println(Arrays.toString(rules));
        return rules;

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException {

        GeneticAlgorithmAssignmentt ga = new GeneticAlgorithmAssignmentt(0.002, 0.8);
        Population newPop = new Population(50);

        Rule[] rules = ga.splitToRules(ga.convertFileToData("C:\\Users\\Rhys\\Documents\\GitHub\\GeneticAlgorithmAssignmentt\\src\\geneticalgorithmassignmentt\\data1.txt"), 6);

        // ga.calcFitness(rules, newPop.getIndividual(1));
        int generationNo = 1;

        while (ga.conditionMet(newPop) == false) {
            while (generationNo < 50) {
                for (Individual individual : newPop.getIndividuals()) {
                    ga.calcFitness(rules, individual);

                }

                Population selected = new Population(newPop.size());
                for (int i = 0; i < newPop.size(); i++) {
                    Individual best = ga.tournementSelection(rules, newPop, 5);
                    selected.saveIndividual(i, best);

                }

                Population crossoverPop = new Population(newPop.size());
                crossoverPop.saveIndividual(0, newPop.getFittest());

                for (int i = 1; i < newPop.size(); i++) {
                    if (Math.random() < ga.crossoverRate) {
                        Individual crossover = ga.crossover(ga.tournementSelection(rules, selected, 5), ga.tournementSelection(rules, selected, 5));
                        crossoverPop.saveIndividual(i, crossover);
                    } else {
                        crossoverPop.saveIndividual(i, selected.getFittest());
                    }

                }

                for (int i = 1; i < newPop.size(); i++) {
                    ga.mutate(crossoverPop.getIndividual(i));
                    ga.calcFitness(rules, crossoverPop.getIndividual(i));

                }
                newPop = crossoverPop;

                ga.calcPopulationFitness(rules, newPop);
                System.out.println("Generation: " + generationNo + " Generation Average Fitness: " + (newPop.getPopulationFitness() / newPop.size()) + " Fittest Individual: " + newPop.getFittest().getFitness());
                generationNo++;
            }
            for (Individual individual : newPop.getIndividuals()) {

                System.out.println(individual.getFitness() + " " + individual.toString());
            }
            System.out.println("Generation: " + generationNo);

        }

    }
}
