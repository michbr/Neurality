package neurality.inputs;

import neurality.IOutputValue;

/**
 * Created by bmichaud on 11/1/2016.
 */
public class BinaryInput implements IOutputValue{
    private boolean value;

    public BinaryInput(boolean value) {
        this.value = value;
    }

    public void setValue(boolean value) {
        this.value = value;
    }

    @Override
    public double getOutputValue() {
        return (value) ? 1.0 : 0.0;
    }
}
