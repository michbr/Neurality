package genetics;

import util.GlobalRandom;

import java.util.*;

/**
 * Created by bmichaud on 10/26/2016.
 */
public class Chromosome implements Comparable<Chromosome> {

    private List<Double> weights;
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

    public Chromosome crossover(Chromosome other, int index) {
        if (other == this) {
            return this;
        } else {
            LinkedList<Double> childGenes = new LinkedList<>();
            for (int i = 0; i < index; ++i) {
                childGenes.add(weights.get(i));
            }

            for (int i = index; i < weights.size(); ++i) {
                childGenes.add(other.weights.get(i));
            }

            Chromosome child = new Chromosome(childGenes);
            return child;
        }
    }

    public void mutate(double mutationRate) {
        List<Double> newWeights = new ArrayList<>();
        Random random = GlobalRandom.getInstance().getRandom();
        for (double weight : weights) {
            if (random.nextDouble() > mutationRate) {
                newWeights.add(generateRandomGene());
            } else {
                newWeights.add(weight);
            }
        }
        weights = newWeights;
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

    public static double generateRandomGene() {
        Random random = GlobalRandom.getInstance().getRandom();
        return random.nextDouble() - random.nextDouble();
    }
}