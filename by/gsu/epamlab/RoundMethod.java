package by.gsu.epamlab;

public enum RoundMethod {
    FLOOR {
	@Override
	int roundFunction(double d) {
	    return (int) Math.floor(d);
	}
    },
    ROUND {
	@Override
	int roundFunction(double d) {
	    return (int) Math.round(d);
	}
    },
    CEIL {
	@Override
	int roundFunction(double d) {
	    return (int) Math.ceil(d);
	}
    };

    abstract int roundFunction(double d);

    public int round(double roundedValue, int d) {
	int tenPow = pow10(d);
	int result = roundFunction(roundedValue / tenPow) * tenPow;
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
