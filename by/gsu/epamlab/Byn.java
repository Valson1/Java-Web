package by.gsu.epamlab;

import java.util.Currency;
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
	this.value = 0;
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

    public Byn myltiply(double k, RoundMethod roundMethod, int d) {
	return new Byn(roundMethod.round(this.value * k, d));
    }

    public Byn round(RoundMethod roundMethod, int d) {
	return new Byn(roundMethod.round(value, d));
    }

    @Override
    public String toString() {
	return String.format("%d.%02d", value / 100, value % 100);
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	Byn other = (Byn) obj;
	return value == other.value;
    }

    @Override
    public int compareTo(Byn byn) {
	return value - byn.value;
    }

}
