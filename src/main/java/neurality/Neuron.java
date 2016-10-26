package neurality;

import java.util.*;

/**
 * Created by bmichaud on 10/24/2016.
 */
public class Neuron implements IOutputValue {

    private double activationThreshold;
    private List<NeuronInput> inputs = new ArrayList<>();
    //private Map<neurality.IOutputValue, Double> weightMap = new HashMap<>();

    public Neuron(double activationThreshold) {
        this.activationThreshold = activationThreshold;
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
        return weights;
    }

    public void setWeights(Queue<Double> weights) {
        for (NeuronInput input : inputs) {
            input.setWeight(weights.remove());
        }
    }

    @Override
    public boolean getOutputValue() {
        double sum = 0;
        for (NeuronInput input : inputs) {
            sum+= ((input.getInput().getOutputValue()) ? 1 : 0) * input.getWeight();
        }
        return (sum - activationThreshold) > 0;
    }
}