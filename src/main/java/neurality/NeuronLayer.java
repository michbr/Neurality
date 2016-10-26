package neurality;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by bmichaud on 10/24/2016.
 */
public class NeuronLayer {

    private final List<Neuron> neuronList = new ArrayList<>();

    public NeuronLayer(int neuronCount) {
        for(int i = 0; i < neuronCount; ++i) {
            neuronList.add(new Neuron(.5));
        }
    }

    public List<Double> extractWeights() {
        List<Double> weights = new ArrayList<>();
        for (Neuron neuron : neuronList) {
            weights.addAll(neuron.extractWeights());
        }
        return weights;
    }

    public void setOutputLayer(NeuronLayer outputLayer) {
        Random random = new Random(System.currentTimeMillis());
        for(Neuron n : neuronList) {
            for (Neuron output : outputLayer.neuronList) {
                output.addInput(n, random.nextDouble() - random.nextDouble());
            }
        }
    }

    public String getOutput() {
        String result = "";
        for(Neuron neuron : neuronList) {
            result += (neuron.getOutputValue()) ? "1 " : "0 ";
        }
        return result;
    }

    public List<Neuron> getNeuronList() {
        return neuronList;
    }
}