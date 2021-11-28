package by.gsu.epamlab;

import java.util.Currency;

public final class Byn implements Comparable<Byn>{
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
    
    public Byn myltiply(double k) {
	return new Byn((int)Math.floor(this.value * k / 100) * 100);
    }

    @Override
    public String toString() {
	return String.format("%d.%02d", value / 100, value % 100);
    }

    @Override
    public int compareTo(Byn byn) {
	return value - byn.value;
    }
    
}
