package neurality.neurons;

import neurality.NeuronInput;

/**
 * Created by bmichaud on 10/31/2016.
 */
public class Perceptron extends AbstractNeuron {

    public Perceptron(double activationThreshold) {
        super(activationThreshold);
    }

    @Override
    public double getOutputValue() {
        double sum = 0;
        for (NeuronInput input : inputs) {
            sum+= input.getInput().getOutputValue() * input.getWeight();
        }
        return ((sum - activationThreshold) > 0) ? 1.0 : 0;
    }
}
