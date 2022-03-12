package by.epam.lab;

import exceptions.NonpositiveArgumentException;

public final class Byn implements Comparable<Byn> {
    private final int value;

    public Byn(int value) {
	if (value < 0) {
	    throw new NonpositiveArgumentException(value);
	}
	this.value = value;
    }

    public Byn(Byn byn) {
	this(byn.value);
    }

    public Byn() {
	this(0);
    }

    private static int getValidValue(int rubs, int coins) {
	if (rubs < 0) {
	    throw new IllegalArgumentException(ConstantsUtility.EXCEPTION_MESSAGE_BYN_RUBS + rubs);
	}
	if (coins < 0 || coins > ConstantsUtility.VALUE_DIVISOR) {
	    throw new IllegalArgumentException(ConstantsUtility.EXCEPTION_MESSAGE_BYN_COPS + coins);
	}
	return ConstantsUtility.VALUE_DIVISOR * rubs + coins;
    }

    public Byn(int rubs, int coins) {
	this(getValidValue(rubs, coins));
    }

    public Byn(String strKops) {
	this(Integer.parseInt(strKops));
    }

    public int getValue() {
	return value;
    }

    public Byn sum(Byn byn) {
	return new Byn(this.value + byn.value);
    }

    public Byn sub(Byn byn) {
	return new Byn(this.value - byn.value);
    }

    public Byn multiply(int number) {
	return new Byn(this.value * number);
    }

    public Byn multiply(double k, RoundMethod roundMethod, int d) {
	return new Byn(roundMethod.round(this.value * k, d));
    }

    public Byn round(RoundMethod roundMethod, int d) {
	return new Byn(roundMethod.round(value, d));
    }

    @Override
    public String toString() {
	return value / ConstantsUtility.VALUE_DIVISOR + ConstantsUtility.DOT + value % ConstantsUtility.VALUE_DIVISOR;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null || getClass() != obj.getClass())
	    return false;
	Byn other = (Byn) obj;
	return value == other.value;
    }

    @Override
    public int compareTo(Byn byn) {
	return value - byn.value;
    }

}