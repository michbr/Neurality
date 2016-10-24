import java.util.*;

/**
 * Created by bmichaud on 10/24/2016.
 */
public class Neuron implements NeuronInput {

    private double activationThreshold;
    private List<NeuronInput> inputs = new ArrayList<>();
    private Map<NeuronInput, Double> weightMap = new HashMap<>();

    public Neuron(double activationThreshold) {
        this.activationThreshold = activationThreshold;
    }

    public void addInput(NeuronInput n) {
        inputs.add(n);
        Random random = new Random(System.currentTimeMillis());
        weightMap.put(n, random.nextDouble() - random.nextDouble());
    }

    public void addInput(NeuronInput n, double weight) {
        inputs.add(n);
        weightMap.put(n, weight);
    }

    @Override
    public boolean getInputValue() {
        double sum = 0;
        for (NeuronInput input : inputs) {
            sum+= ((input.getInputValue()) ? 1 : 0) * weightMap.get(input);
        }
        return (sum - activationThreshold) > 0;
    }
}