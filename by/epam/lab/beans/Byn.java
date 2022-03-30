package by.epam.lab.beans;

import static by.epam.lab.utils.ConstantsUtility.*;

import java.util.Objects;

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

    public Byn(int rubs, int coins) {
	this(VALUE_DIVISOR * rubs + coins);
    }

    public Byn(String element) {
	this(Integer.parseInt(element));
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

    @Override
    public String toString() {
	return String.format(FORMAT, value / VALUE_DIVISOR, value % VALUE_DIVISOR);
    }

    @Override
    public int compareTo(Byn byn) {
	return value - byn.value;
    }

    @Override
    public int hashCode() {
	return value;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null || getClass() != obj.getClass())
	    return false;
	Byn byn = (Byn) obj;
	return value == byn.value;
    }

}
