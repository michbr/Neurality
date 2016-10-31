package neurality;

import neurality.neurons.AbstractNeuron;
import neurality.neurons.Neuron;
import neurality.neurons.Perceptron;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Random;

/**
 * Created by bmichaud on 10/24/2016.
 */
public class NeuronLayer {

    private final List<AbstractNeuron> neuronList = new ArrayList<>();

    public NeuronLayer(NeuralNet.NeuronMode mode, int neuronCount, double activationThreshold) {
        for(int i = 0; i < neuronCount; ++i) {
            if (mode == NeuralNet.NeuronMode.NEURON) {
                neuronList.add(new Neuron(activationThreshold));
            } else {
                neuronList.add(new Perceptron(activationThreshold));
            }
        }
    }

    public List<Double> extractWeights() {
        List<Double> weights = new ArrayList<>();
        for (AbstractNeuron neuron : neuronList) {
            weights.addAll(neuron.extractWeights());
        }
        return weights;
    }

    public void setWeights(Queue<Double> weights) {
        for (AbstractNeuron neuron : neuronList) {
            neuron.setWeights(weights);
        }
    }

    public void setOutputLayer(NeuronLayer outputLayer) {
        Random random = new Random(System.currentTimeMillis());
        for(AbstractNeuron n : neuronList) {
            for (AbstractNeuron output : outputLayer.neuronList) {
                output.addInput(n, random.nextDouble() - random.nextDouble());
            }
        }
    }

    public List<Boolean> getOutput() {
        List<Boolean> result = new ArrayList<>();
        for(AbstractNeuron neuron : neuronList) {
            result.add(neuron.getOutputValue());
        }
        return result;
    }

    public List<AbstractNeuron> getNeuronList() {
        return neuronList;
    }
}