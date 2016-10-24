import java.util.ArrayList;
import java.util.List;

/**
 * Created by bmichaud on 10/24/2016.
 */
public class NeuronLayer {

    private int neuronCount;
    private final List<Neuron> neuronList = new ArrayList<>();

    public NeuronLayer(int neuronCount) {
        this.neuronCount = neuronCount;
        for(int i = 0; i < neuronCount; ++i) {
            neuronList.add(new Neuron(.5));
        }
    }

    public void setOutputLayer(NeuronLayer outputLayer) {
        for(Neuron n : neuronList) {
            for (Neuron output : outputLayer.neuronList) {
                output.addInput(n);
            }
        }
    }

    public String getOuput() {
        String result = "";
        for(Neuron neuron : neuronList) {
            result += (neuron.getInputValue()) ? "1 " : "0 ";
        }
        return result;
    }

    public List<Neuron> getNeuronList() {
        return neuronList;
    }
}