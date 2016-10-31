package genetics;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by bmichaud on 10/31/2016.
 */
public class ChromosomeTest {

    @Test
    public void testCrossover() throws Exception {
        Chromosome a = new Chromosome(generateWeights(-1, 10));
        Chromosome b = new Chromosome(generateWeights(1, 10));

        Chromosome result = a.crossover(b, 6);
        for (int i = 0; i < 6; ++i) {
            assertTrue(result.getWeights().get(i) < 0 );
        }

        for (int i = 6; i < 10; ++i) {
            assertTrue(result.getWeights().get(i) > 0 );
        }

    }

    @Test
    public void testCrossover_Reversed() throws Exception {
        Chromosome a = new Chromosome(generateWeights(-1, 10));
        Chromosome b = new Chromosome(generateWeights(1, 10));

        Chromosome result = b.crossover(a, 7);
        for (int i = 0; i < 7; ++i) {
            assertTrue(result.getWeights().get(i) > 0 );
        }

        for (int i = 7; i < 10; ++i) {
            assertTrue(result.getWeights().get(i) < 0 );
        }

    }

    @Test
    public void testMutate() {

        Chromosome a = new Chromosome(generateWeights(1, 100));
        a.mutate(.5);
        boolean passed = false;
        for (int i = 0; i < 100; ++i) {
            if (Math.abs(a.getWeights().get(i) - 1) > .00001) {
                passed = true;
            }
        }
        assertTrue(passed);
    }

    private List<Double> generateWeights(double fillValue, int length) {
        List<Double> result = new ArrayList<>();
        for (int i = 0; i < length; ++i) {
            result.add(fillValue);
        }
        return result;
    }
}