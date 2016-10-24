import java.util.ArrayList;
import java.util.List;

/**
 * Created by bmichaud on 10/24/2016.
 */
public class InputLayer {

    List<NeuronInput> inputs = new ArrayList<>();

    public InputLayer(int inputCount) {
        for (int i = 0; i < inputCount; ++i) {
            inputs.add(new UserInput(false));
        }
    }

    public void setOutputLayer(NeuronLayer outputLayer) {
        for(NeuronInput n : inputs) {
            for (Neuron output : outputLayer.getNeuronList()) {
                output.addInput(n);
            }
        }
    }

    public void setInput(int index, boolean value) {
        if (index > 0 && index < inputs.size()) {
            ((UserInput)inputs.get(index)).setValue(value);
        }
    }

    public static class UserInput implements NeuronInput {
        private boolean value;

        public UserInput(boolean value) {
            this.value = value;
        }

        public void setValue(boolean value) {
            this.value = value;
        }

        @Override
        public boolean getInputValue() {
            return value;
        }
    }
}