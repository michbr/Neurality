package neurality;

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
            inputs.add(new UserInput(false));
        }
    }

    public void setOutputLayer(NeuronLayer outputLayer) {
        Random random = new Random(System.currentTimeMillis());

        for(IOutputValue n : inputs) {
            for (Neuron output : outputLayer.getNeuronList()) {
                output.addInput(n, random.nextDouble() - random.nextDouble());
            }
        }
    }

    public void setInput(int index, boolean value) {
        if (index >= 0 && index < inputs.size()) {
            ((UserInput)inputs.get(index)).setValue(value);
        }
    }

    public static class UserInput implements IOutputValue {
        private boolean value;

        public UserInput(boolean value) {
            this.value = value;
        }

        public void setValue(boolean value) {
            this.value = value;
        }

        @Override
        public boolean getOutputValue() {
            return value;
        }
    }
}