package genetics;

import neurality.NeuralNet;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by bmichaud on 11/7/2016.
 */
public class XORFitnessEvaluator extends FitnessEvaluator {

    public XORFitnessEvaluator(NeuralNet net) {
        super(net);
    }

    @Override
    public double evaluateFitness(Chromosome candidate) {
        LinkedList<Double> weights = new LinkedList<>();
        weights.addAll(candidate.getWeights());
        net.setWeights(weights);

        double fitness = 0;
        net.setInputs(new boolean[] {true, true});
        List<Double> output = net.calculateOutput();
        fitness += 1 - Math.abs(output.get(0));

        net.setInputs(new boolean[] {false, true});
        output = net.calculateOutput();
        fitness += 1 - Math.abs(output.get(0) - 1);

        net.setInputs(new boolean[] {true, false});
        output = net.calculateOutput();
        fitness += 1 - Math.abs(output.get(0) - 1);

        net.setInputs(new boolean[] {false, false});
        output = net.calculateOutput();
        fitness += 1 - Math.abs(output.get(0));

        return fitness;
    }
}
