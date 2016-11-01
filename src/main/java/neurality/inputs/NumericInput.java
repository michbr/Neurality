package neurality.inputs;

import neurality.IOutputValue;

/**
 * Created by bmichaud on 11/1/2016.
 */
public class NumericInput implements IOutputValue{
    private double value;

    public NumericInput(double value) {
        this.value = value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    @Override
    public double getOutputValue() {
        return value;
    }
}
