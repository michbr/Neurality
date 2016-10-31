package neurality;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by bmichaud on 10/26/2016.
 */
public class NeuronLayerTest {

    @Test
    public void testExtractWeights() {
        NeuronLayer layer = new NeuronLayer(NeuralNet.NeuronMode.PERCEPTRON, 3, .5);
        InputLayer inputLayer = new InputLayer(1);
        inputLayer.setOutputLayer(layer);
        List<Double> weights = layer.extractWeights();
        assertEquals(3, weights.size());
    }

    @Test
    public void testCalculateOutputs() {
        NeuronLayer layer = new NeuronLayer(NeuralNet.NeuronMode.PERCEPTRON, 1, .5);
        InputLayer inputLayer = new InputLayer(1);
        inputLayer.setOutputLayer(layer);
        inputLayer.setInput(0, true);
        assertEquals(1, layer.getOutput().size());
        assertEquals(true, layer.getOutput().get(0));
    }
}