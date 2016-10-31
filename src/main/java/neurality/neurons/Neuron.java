package neurality.neurons;

import neurality.NeuronInput;

/**
 * Created by bmichaud on 10/24/2016.
 */
public class Neuron extends AbstractNeuron {

    public Neuron(double activationThreshold) {
        super(activationThreshold);
    }

    @Override
    public boolean getOutputValue() {
        double sum = 0;
        for (NeuronInput input : inputs) {
            sum+= ((input.getInput().getOutputValue()) ? 1.0 : 0.0) * input.getWeight();
        }
        double finalInput = (1)/(1 + Math.exp(-sum));
        return (finalInput - activationThreshold) > 0;
    }
}