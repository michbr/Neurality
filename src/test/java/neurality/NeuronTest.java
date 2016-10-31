package neurality;

import neurality.neurons.Neuron;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
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

    @Test
    public void testGetOutput() {
        Neuron neuron = new Neuron(.5);
        neuron.addInput(new IOutputValue() {
            @Override
            public boolean getOutputValue() {
                return true;
            }
        }, .6);
        assertTrue(neuron.getOutputValue());
    }

    @Test
    public void testGetOutput_multipleNeurons() {
        Neuron a = new Neuron(.5);
        Neuron b = new Neuron(.5);
        a.addInput(new IOutputValue() {
            @Override
            public boolean getOutputValue() {
                return true;
            }
        }, .6);
        b.addInput(a, .6);
        assertTrue(b.getOutputValue());
    }

}