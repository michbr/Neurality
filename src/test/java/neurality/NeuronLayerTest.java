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
        NeuronLayer layer = new NeuronLayer(3);
        InputLayer inputLayer = new InputLayer(1);
        inputLayer.setOutputLayer(layer);
        List<Double> weights = layer.extractWeights();
        assertEquals(3, weights.size());
    }

}