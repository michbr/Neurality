package genetics;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bmichaud on 10/26/2016.
 */
public class Population {
    List<Chromosome> chromosomes = new ArrayList<>();
    private double mutationRate;
    private double crossoverRate;

    public Population(double mutationRate, double crossoverRate) {
        this.mutationRate = mutationRate;
        this.crossoverRate = crossoverRate;
    }


}
