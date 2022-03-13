package by.epam.lab.beans;

import static by.epam.lab.utils.ConstantsUtility.*;
import by.epam.lab.exceptions.*;
import by.epam.lab.services.*;

public final class Byn implements Comparable<Byn> {
    private final int value;

    public Byn(int value) {
	if (value < 0) {
	    throw new NegativeArgumentException(EXCEPTION_MESSAGE_BYN_VALUE + value);
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
	    throw new NegativeArgumentException(EXCEPTION_MESSAGE_BYN_RUBS + rubs);
	}
	if (coins < 0 || coins >= VALUE_DIVISOR) {
	    throw new NegativeArgumentException(EXCEPTION_MESSAGE_BYN_COPS + coins);
	}
	return VALUE_DIVISOR * rubs + coins;
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
	return new Byn(value + byn.value);
    }

    public Byn sub(Byn byn) {
	return new Byn(value - byn.value);
    }

    public Byn multiply(int number) {
	return new Byn(value * number);
    }

    public Byn multiply(double k, RoundMethod roundMethod, int d) {
	return new Byn(roundMethod.round(this.value * k, d));
    }

    public Byn round(RoundMethod roundMethod, int d) {
	return new Byn(roundMethod.round(value, d));
    }

    @Override
    public String toString() {
	return String.format(FORMAT, value / VALUE_DIVISOR, value % VALUE_DIVISOR);
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