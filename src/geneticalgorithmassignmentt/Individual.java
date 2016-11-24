package geneticalgorithmassignmentt;

import java.util.Arrays;

/**
 *
 * @author r24-nicholls
 */
public class Individual {

    static int geneLength =49;
    private byte[] genes = new byte[geneLength];
    private double fitness = 0;

    public void generateIndividual() {
        for (int i = 0; i < genes.length; i++) {
            byte gene = (byte) Math.round(Math.random());
            genes[i] = gene;
        }
    }

    public int getGene(int index) {
        return genes[index];
    }

    public byte[] getGenes() {
        return Arrays.copyOf(genes, geneLength);
    }
    

    public void setGene(int index, byte value) {
        genes[index] = value;
        fitness = 0;
    }

    public static void setGeneLength(int length) {
        geneLength = length;
    }

    public double getFitness() {
        return fitness;
    }

    public void setFitness(double fitness) {
        this.fitness = fitness;
    }

    public int size() {
        return genes.length;
    }

    @Override
    public String toString() {
        String geneString = "";
        for (int i = 0; i < size(); i++) {
            geneString += getGene(i);
        }
        return geneString;
    }

}
