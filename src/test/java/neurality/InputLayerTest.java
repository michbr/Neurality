package neurality;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by bmichaud on 10/28/2016.
 */
public class InputLayerTest {

    @Test
    public void testCreate() {
        InputLayer inputLayer = new InputLayer(2);
        inputLayer.setInput(1, true);
        inputLayer.setInput(1, false);

        NeuronLayer neuronLayer = new NeuronLayer(NeuralNet.NeuronMode.PERCEPTRON, 1, .5, false);
        inputLayer.setOutputLayer(neuronLayer);
        assertEquals(2, neuronLayer.extractWeights().size());
    }
}