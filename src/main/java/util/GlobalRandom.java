package util;

import java.util.Random;

/**
 * Created by bmichaud on 10/31/2016.
 */
public class GlobalRandom {

    private static GlobalRandom instance;
    private Random random = new Random(System.currentTimeMillis());

    private GlobalRandom() {
    }

    public static GlobalRandom getInstance() {
        if (instance == null) {
            instance = new GlobalRandom();
        }
        return instance;
    }

    public Random getRandom() {
        return random;
    }

}
