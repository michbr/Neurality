package genetics;

import java.util.List;

/**
 * Created by bmichaud on 10/26/2016.
 */
public class Chromosome {

    private final List<Double> weights;
    private double fitness;

    public Chromosome(List<Double> weights) {
        this.weights = weights;
    }

    public List<Double> getWeights() {
        return weights;
    }
}
