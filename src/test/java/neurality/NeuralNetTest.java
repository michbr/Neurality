package neurality;

import org.junit.Test;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by bmichaud on 10/26/2016.
 */
public class NeuralNetTest {

    @Test
    public void testExtractWeights() {
        NeuralNet net = new NeuralNet(NeuralNet.NeuronMode.PERCEPTRON, 2, 1, 1, 0);
        assertEquals(2, net.extractWeights().size());
    }

    @Test
    public void testSetWeights() {
        NeuralNet net = new NeuralNet(NeuralNet.NeuronMode.PERCEPTRON, 2, 1, 1, 0);
        Queue<Double> weights = new LinkedList<Double>();
        weights.add(.3);
        weights.add(.4);
        net.setWeights(weights);
        List<Double> weightList = net.extractWeights();
        assertEquals(2, weightList.size());
        assertEquals(.3, weightList.get(0), .000001);
        assertEquals(.4, weightList.get(1), .000001);
    }

}