package genetics;

import neurality.NeuralNet;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by bmichaud on 10/28/2016.
 */
public class PopulationTest {

    @Test
    public void testCreate() {
        Population population = new Population(new NeuralNet(NeuralNet.NeuronMode.PERCEPTRON, 1, 1, 1, 0), 5, .7, .05);

    }

}