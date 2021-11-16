package by.gsu.epamlab;

public enum RoundMethod {
    FLOOR {
	@Override
	double roundFunction(double d) {
	    return Math.floor(d);
	}
    },
    ROUND {
	@Override
	double roundFunction(double d) {
	    return Math.round(d);
	}
    },
    CEIL {
	@Override
	double roundFunction(double d) {
	    return Math.ceil(d);
	}
    };

    abstract double roundFunction(double d);

    public int round(double roundedValue, int d) {
	int tenPow = (int) pow10(d);
	int result = (int) roundFunction(roundedValue / tenPow) * tenPow;
	return result;
    }

    private static int pow10(int d) {
	int result = 1;
	for (int i = 0; i < d; i++) {
	    result *= 10;
	}
	return result;
    }
}
