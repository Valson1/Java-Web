package services;

public enum RoundMethod {
    FLOOR {
	@Override
	double roundFunction(double d) {
	    return (int) Math.floor(d);
	}
    },
    ROUND {
	@Override
	double roundFunction(double d) {
	    return (int) Math.round(d);
	}
    },
    CEIL {
	@Override
	double roundFunction(double d) {
	    return (int) Math.ceil(d);
	}
    };

    abstract double roundFunction(double d);

    public int round(double roundedValue, int d) {
	return (int)roundFunction(roundedValue / pow10(d)) * pow10(d);
    }

    private static int pow10(int d) {
	int result = 1;
	for (int i = 0; i < d; i++) {
	    result *= 10;
	}
	return result;
    }
}
