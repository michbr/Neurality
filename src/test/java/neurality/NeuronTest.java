package neurality;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by bmichaud on 10/26/2016.
 */
public class NeuronTest {

    @Test
    public void testExtractWeights() {
        Neuron neuron = new Neuron(.5);
        neuron.addInput(new IOutputValue() {
            @Override
            public boolean getOutputValue() {
                return false;
            }
        }, .5);
        assertEquals(1, neuron.extractWeights().size());
        assertEquals(.5, neuron.extractWeights().get(0).doubleValue(), .000001);
    }

}