package neurality.neurons;

import neurality.IOutputValue;
import neurality.NeuronInput;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Random;

/**
 * Created by bmichaud on 10/31/2016.
 */
public abstract class AbstractNeuron implements IOutputValue {
    private final boolean evolveActivationThreshold;
    protected double activationThreshold;
    protected List<NeuronInput> inputs = new ArrayList<>();

    public AbstractNeuron(double activationThreshold, boolean evolveActivationThreshold) {
        this.activationThreshold = activationThreshold;
        this.evolveActivationThreshold = evolveActivationThreshold;
    }

    public void addInput(IOutputValue n) {
        Random random = new Random(System.currentTimeMillis());
        inputs.add(new NeuronInput(n,random.nextDouble() - random.nextDouble()));
    }

    public void addInput(IOutputValue n, double weight) {
        inputs.add(new NeuronInput(n, weight));
    }

    public List<Double> extractWeights() {
        List<Double> weights = new ArrayList<>();
        for (NeuronInput input : inputs) {
            weights.add(input.getWeight());
        }
        if (evolveActivationThreshold) {
            weights.add(activationThreshold);
        }
        return weights;
    }

    public void setWeights(Queue<Double> weights) {
        for (NeuronInput input : inputs) {
            input.setWeight(weights.remove());
        }
        if (evolveActivationThreshold) {
            activationThreshold = weights.remove();
        }
    }

    @Override
    public abstract double getOutputValue();
}
