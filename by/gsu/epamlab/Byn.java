package by.gsu.epamlab;

import static java.lang.Math.round;

public class Byn implements Comparable<Byn> {
    private int value;

    public Byn(Byn byn) {
	this(byn.value);
    }

    public Byn(int value) {
	super();
	this.value = value;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null || getClass() != obj.getClass())
	    return false;
	Byn byn = (Byn) obj;
	return value == byn.value ? true : false;
    }

    public Byn sum(Byn byn) {
	value += byn.value;
	return this;
    }

    public Byn div(Byn byn) {
	value /= byn.value;
	return this;
    }

    public Byn multiply(int number) {
	value *= number;
	return this;
    }

    public Byn multiply(double discount) {
	round(value *= (1 - discount / 100));
	return this;
    }

    public Byn multiply(Byn byn) {
	value *= byn.value;
	return this;
    }

    public Byn sub(double discount) {
	round(value -= discount);
	return this;
    }

    public Byn sub(Byn byn) {
	value -= byn.value;
	return this;
    }

    @Override
    public String toString() {
	return String.format("%d.%02d", value / 100, value % 100);
    }

    @Override
    public int compareTo(Byn byn) {
	return this.value - byn.value;
    }
}
