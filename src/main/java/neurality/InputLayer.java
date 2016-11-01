package neurality;

import neurality.inputs.BinaryInput;
import neurality.neurons.AbstractNeuron;
import neurality.neurons.Neuron;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by bmichaud on 10/24/2016.
 */
public class InputLayer {

    private List<IOutputValue> inputs = new ArrayList<>();

    public InputLayer(int inputCount) {
        for (int i = 0; i < inputCount; ++i) {
            inputs.add(new BinaryInput(false));
        }
    }

    public void setOutputLayer(NeuronLayer outputLayer) {
        Random random = new Random(System.currentTimeMillis());

        for(IOutputValue n : inputs) {
            for (AbstractNeuron output : outputLayer.getNeuronList()) {
                output.addInput(n, random.nextDouble() - random.nextDouble());
            }
        }
    }

    public void setInput(int index, boolean value) {
        if (index >= 0 && index < inputs.size()) {
            ((BinaryInput)inputs.get(index)).setValue(value);
        }
    }
}