package genetics;

import neurality.NeuralNet;

/**
 * Created by bmichaud on 10/31/2016.
 */
public abstract class FitnessEvaluator {

    protected final NeuralNet net;

    public FitnessEvaluator(NeuralNet net) {
        this.net = net;
    }

    public abstract double evaluateFitness(Chromosome chromosome);
}
