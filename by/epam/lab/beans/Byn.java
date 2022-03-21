package by.epam.lab.beans;

import static by.epam.lab.utils.ConstantsUtility.*;
import by.epam.lab.services.*;

public final class Byn implements Comparable<Byn> {
    private final int value;

    public Byn(int value) {
	this.value = value;
    }

    public Byn(Byn byn) {
	this(byn.value);
    }

    public Byn() {
	this(0);
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
	return new Byn(roundMethod.round(value * k, d));
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