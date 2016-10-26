package neurality;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * Created by bmichaud on 10/24/2016.
 */
public class NeuralNet {

    private InputLayer inputLayer;

    //private neurality.NeuronLayer firstLayer;
    private NeuronLayer outputLayer;

    private List<NeuronLayer> hiddenLayers = new ArrayList<>();

    public NeuralNet(int inputCount, int outputCount, int middleCount, int hiddenLayerCount) {
        inputLayer = new InputLayer(inputCount);
        //firstLayer = new neurality.NeuronLayer(inputCount);
        outputLayer = new NeuronLayer(outputCount);
        //inputLayer.setOutputLayer(firstLayer);
        //neurality.NeuronLayer prevLayer = firstLayer;
        NeuronLayer prevLayer = null;
        for (int i = 0; i < hiddenLayerCount; ++i) {
            NeuronLayer newLayer = new NeuronLayer(middleCount);
            hiddenLayers.add(newLayer);
            if (prevLayer != null) {
                prevLayer.setOutputLayer(newLayer);
            }
            prevLayer=newLayer;
        }
        if (prevLayer != null) {
            prevLayer.setOutputLayer(outputLayer);
            inputLayer.setOutputLayer(hiddenLayers.get(0));
        } else {
            inputLayer.setOutputLayer(outputLayer);
        }
    }

    public List<Double> extractWeights() {
        List<Double> weights = new ArrayList<>();
        for (NeuronLayer layer : hiddenLayers) {
            weights.addAll(layer.extractWeights());
        }
        weights.addAll(outputLayer.extractWeights());
        return weights;
    }

    public void setWeights(Queue<Double> weights) {
        for (NeuronLayer neuronLayer : hiddenLayers) {
            setWeights(weights);
        }
        outputLayer.setWeights(weights);
    }

    public void calculateOutput() {
        System.out.println(outputLayer.getOutput());
    }

    public void setInputs(boolean [] inputs) {
        for (int i = 0; i < inputs.length; ++i) {
            inputLayer.setInput(i, inputs[i]);
        }
    }

    public static void main(String[] args) {
        NeuralNet net = new NeuralNet(2, 1, 1, 0);
        net.setInputs(new boolean[] {true, true});
        net.calculateOutput();
        List<Double> weights = net.extractWeights();
        for (Double weight : weights) {
            System.out.println("weight: " + weight);
        }
    }
}