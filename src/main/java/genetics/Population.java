package genetics;

import neurality.NeuralNet;
import util.GlobalRandom;

import java.util.*;

/**
 * Created by bmichaud on 10/26/2016.
 */
public class Population {
    private List<Chromosome> chromosomes = new ArrayList<>();
    private double mutationRate;
    private double crossoverRate;
    private NeuralNet net;

    private int chromosomeLength;
    private int populationSize;
    private double totalFitness;
    private int generationCount = 0;
    private double mutationPower;

    public Population(NeuralNet net, int populationSize, double mutationRate, double mutationPower, double crossoverRate) {
        this.mutationRate = mutationRate;
        this.crossoverRate = crossoverRate;
        this.populationSize = populationSize;
        this.mutationPower = mutationPower;
        chromosomeLength = net.extractWeights().size();
        this.net = net;
        populate();
    }

    public void run(int generations) {
        for (int i = 0; i < generations; ++i) {
            runGeneration();
        }
    }

    public void run(double targetFitness) {
        while(getBest().getFitness() < targetFitness) {
            runGeneration();
        }
    }

    private void runGeneration() {
        //System.out.println("Generation " + generationCount);
        ++generationCount;
        evaluatePopulationFitness();
        if (generationCount % 10 == 0)
            System.out.println("\tTotal Fitness: " + totalFitness);
        List<Chromosome> newPopulation = new ArrayList<>();
        while(newPopulation.size() < populationSize) {
            Chromosome parent1 = selectRandomChromosome();
            Chromosome parent2 = selectRandomChromosome();
            crossover(parent1, parent2, newPopulation);
        }
        chromosomes = newPopulation;
    }

    private void evaluatePopulationFitness() {
        totalFitness = 0;
        for (Chromosome chromosome : chromosomes) {
            calculateFitness(chromosome);
            totalFitness+= chromosome.getFitness();
        }
        Collections.sort(chromosomes);
    }

    private void crossover(Chromosome a, Chromosome b, List<Chromosome> population) {
        Random random = GlobalRandom.getInstance().getRandom();
        if (random.nextDouble() > crossoverRate) {
            a.mutate(mutationRate, mutationPower);
            b.mutate(mutationRate, mutationPower);
            population.add(a);
            population.add(b);
        } else {
            int crossoverIndex = random.nextInt(chromosomeLength-1);

            Chromosome child1 = a.crossover(b, crossoverIndex);
            Chromosome child2 = b.crossover(a, crossoverIndex);

            child1.mutate(mutationRate, mutationPower);
            child2.mutate(mutationRate, mutationPower);
            population.add(child1);
            population.add(child2);
        }
    }

    private void calculateFitness(Chromosome candidate) {
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

        candidate.setFitness(fitness);
    }

    private void populate() {
        for (int i = 0; i < populationSize; ++i) {
            chromosomes.add(generateRandomChromosome());
        }
    }

    private Chromosome selectRandomChromosome() {
        Random random = GlobalRandom.getInstance().getRandom();
        double slice = totalFitness * random.nextDouble();

        double accumulatedFitness = 0;
        for(Chromosome chromosome : chromosomes) {
            accumulatedFitness+= chromosome.getFitness();

            if (accumulatedFitness >= slice) {
                return chromosome;
            }
        }
        return chromosomes.get(chromosomes.size()-1);
    }

    private Chromosome generateRandomChromosome() {
        LinkedList<Double> weights = new LinkedList<>();
        for (int i = 0; i < chromosomeLength; i++) {
            weights.add(Chromosome.generateRandomGene());
        }
        Chromosome result = new Chromosome(weights);
        return result;
    }

    private Chromosome getBest() {
        evaluatePopulationFitness();
        return chromosomes.get(chromosomes.size() - 1);
    }

    public static void main(String[] args) {

        //Setup the Neural Net
        final int INPUT_NEURON_COUNT = 2;
        final int OUTPUT_NEURON_COUNT = 1;
        final int HIDDEN_LAYER_NEURON_COUNT = 7;
        final int HIDDEN_LAYER_COUNT = 2;
        NeuralNet net = new NeuralNet(NeuralNet.NeuronMode.NEURON, INPUT_NEURON_COUNT, OUTPUT_NEURON_COUNT, HIDDEN_LAYER_NEURON_COUNT, HIDDEN_LAYER_COUNT);
        final int POPULATION_SIZE = 200;

        //Setup the genetic algorithm
        final double MUTATION_RATE = .02;
        final double MUTATION_STRENGTH = .4;
        final double CROSSOVER_RATE = .7;
        Population p = new Population(net, POPULATION_SIZE, MUTATION_RATE, MUTATION_STRENGTH, CROSSOVER_RATE);

        //Run
        p.run(2.8);

        //Display results
        Chromosome chromosome = p.getBest();
        LinkedList<Double> weights = new LinkedList<>();
        weights.addAll(chromosome.getWeights());
        net.setWeights(weights);

        net.setInputs(new boolean[] {true, true});
        List<Double> output = net.calculateOutput();
        printOutput(new boolean[] {true, true}, output);

        net.setInputs(new boolean[] {false, true});
        output = net.calculateOutput();
        printOutput(new boolean[] {false, true}, output);

        net.setInputs(new boolean[] {true, false});
        output = net.calculateOutput();
        printOutput(new boolean[] {true, false}, output);

        net.setInputs(new boolean[] {false, false});
        output = net.calculateOutput();
        printOutput(new boolean[] {false, false}, output);
    }

    public static void printOutput(boolean[] input, List<Double> output) {
        System.out.println("Input: (" +
                (input[0] ? 1 : 0) + ", " +
                (input[1] ? 1 : 0) + ")" +
                "--> " + output.get(0));
    }
}