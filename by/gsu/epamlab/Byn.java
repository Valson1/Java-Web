package by.gsu.epamlab;

public class Byn implements Comparable<Byn> {
    private int value;

    public Byn(Byn byn) {
	this(byn.value);
    }

    public Byn(int value) {
	super();
	this.value = value;
    }

    public Byn(int rubs, int coins) {
	this((rubs * 100) + coins);
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

    public Byn sub(Byn byn) {
	value -= byn.value;
	return this;
    }

    public Byn multiply(int number) {
	value *= number;
	return this;
    }

    public Byn multiply(double k,RoundMethod roundMethod, int d) {
	value = (int) roundMethod.round(value * k,roundMethod,d);
	return this;
    }
    public Byn round(RoundMethod roundMethod, int d){
	value = (int) roundMethod.round(value, roundMethod, d);
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
