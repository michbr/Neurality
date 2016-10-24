import java.util.ArrayList;
import java.util.List;

/**
 * Created by bmichaud on 10/24/2016.
 */
public class NeuralNet {

    private InputLayer inputLayer;

    private NeuronLayer firstLayer;
    private NeuronLayer outputLayer;

    private List<NeuronLayer> hiddenLayers = new ArrayList<>();

    public NeuralNet(int inputCount, int outputCount, int middleCount, int hiddenLayerCount) {
        inputLayer = new InputLayer(inputCount);
        firstLayer = new NeuronLayer(inputCount);
        outputLayer = new NeuronLayer(outputCount);
        inputLayer.setOutputLayer(firstLayer);
        NeuronLayer prevLayer = firstLayer;
        for (int i = 0; i < hiddenLayerCount; ++i) {
            NeuronLayer newLayer = new NeuronLayer(middleCount);
            hiddenLayers.add(newLayer);
            prevLayer.setOutputLayer(newLayer);
            prevLayer=newLayer;
        }
        prevLayer.setOutputLayer(outputLayer);
    }

    public void calculateOutput() {
        System.out.println(outputLayer.getOuput());
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
    }
}