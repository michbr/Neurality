package genetics;

import java.util.List;

/**
 * Created by bmichaud on 10/26/2016.
 */
public class Chromosome implements Comparable<Chromosome> {

    private final List<Double> weights;
    private double fitness;

    public Chromosome(List<Double> weights) {
        this.weights = weights;
    }

    public List<Double> getWeights() {
        return weights;
    }

    public void setFitness(double fitness) {
        this.fitness = fitness;
    }

    public double getFitness() {
        return fitness;
    }

    @Override
    public int compareTo(Chromosome c) {
        if (c.fitness > fitness) {
            return -1;
        } else if (c.fitness < fitness) {
            return 1;
        }
        return 0;
    }
}