package genetics;

import neurality.NeuralNet;

import java.util.*;

/**
 * Created by bmichaud on 10/26/2016.
 */
public class Population {
    private List<Chromosome> chromosomes = new ArrayList<>();
    private double mutationRate;
    private double crossoverRate;
    private NeuralNet net;
    private Random random = new Random(System.currentTimeMillis());

    private int chromosomeLength;
    private int populationSize;
    private double totalFitness;
    private int generationCount = 0;

    public Population(NeuralNet net, int populationSize, double mutationRate, double crossoverRate) {
        this.mutationRate = mutationRate;
        this.crossoverRate = crossoverRate;
        this.populationSize = populationSize;
        //net = new NeuralNet(2, 1, 1, 0);
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
        System.out.println("Generation " + generationCount);
        ++generationCount;
        evaluatePopulationFitness();
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
        if (random.nextDouble() > crossoverRate) {
            population.add(a);
            population.add(b);
        } else {
            int crossoverIndex = random.nextInt(chromosomeLength-1);

            Chromosome child1 = a.crossover(b, crossoverIndex);
            Chromosome child2 = b.crossover(a, crossoverIndex);

            mutate(child1);
            mutate(child1);
            population.add(child1);
            population.add(child2);
        }
    }

    private void mutate(Chromosome chromosome) {
        List<Double> weights = chromosome.getWeights();
        Queue<Double> newWeights = new LinkedList<>();
        for (double weight : weights) {
            if (random.nextDouble() > mutationRate) {
                newWeights.add(generateRandomGene());
            } else {
                newWeights.add(weight);
            }
        }
    }

    private void calculateFitness(Chromosome candidate) {
        LinkedList<Double> weights = new LinkedList<>();
        weights.addAll(candidate.getWeights());
        net.setWeights(weights);

        double fitness = 0;
        net.setInputs(new boolean[] {true, true});
        List<Boolean> output = net.calculateOutput();
        //printOutput(new boolean[] {true, true}, output);

        fitness += output.get(0) ? 0 : 1;

        net.setInputs(new boolean[] {false, true});
        output = net.calculateOutput();
        //printOutput(new boolean[] {false, true}, output);

        fitness += output.get(0) ? 1 : 0;

        net.setInputs(new boolean[] {true, false});
        output = net.calculateOutput();
        //printOutput(new boolean[] {true, false}, output);

        fitness += output.get(0) ? 1 : 0;

        net.setInputs(new boolean[] {false, false});
        output = net.calculateOutput();
        //printOutput(new boolean[] {false, false}, output);
        fitness += output.get(0) ? 0 : 1;
        candidate.setFitness(fitness);
    }

    private void populate() {
        for (int i = 0; i < populationSize; ++i) {
            chromosomes.add(generateRandomChromosome());
        }
    }

    private Chromosome selectRandomChromosome() {
        double slice = totalFitness * random.nextDouble();

        double accumulatedFitness = 0;
        for(Chromosome chromosome : chromosomes) {
            accumulatedFitness+= chromosome.getFitness();

            if (accumulatedFitness >= slice) {
                return chromosome;
            }
        }
        return null;
    }

    private Chromosome generateRandomChromosome() {
        LinkedList<Double> weights = new LinkedList<>();
        for (int i = 0; i < chromosomeLength; i++) {
            weights.add(generateRandomGene());
        }
        Chromosome result = new Chromosome(weights);
        return result;
    }

    private double generateRandomGene() {
        return random.nextDouble() - random.nextDouble();
    }


    private Chromosome getBest() {
        evaluatePopulationFitness();
        return chromosomes.get(chromosomes.size() - 1);
    }

    public static void main(String[] args) {
        NeuralNet net = new NeuralNet(2, 1, 4, 2);
        Population p = new Population(net, 20, .05, .7);
        p.run(4.0);
        Chromosome chromosome = p.getBest();
        LinkedList<Double> weights = new LinkedList<>();
        weights.addAll(chromosome.getWeights());
        net.setWeights(weights);
        net.setInputs(new boolean[] {true, true});
        List<Boolean> output = net.calculateOutput();
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


        net.calculateOutput();
    }

    public static void printOutput(boolean[] input, List<Boolean> output) {
        System.out.println("Input: (" +
                (input[0] ? 1 : 0) + ", " +
                (input[1] ? 1 : 0) + ")" +
                "--> " + (output.get(0) ? 1 : 0));
    }
}