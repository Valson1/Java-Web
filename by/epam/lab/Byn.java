package by.epam.lab;

import java.util.Scanner;

public final class Byn implements Comparable<Byn> {
    private final int value;

    public Byn(int value) {
	this.value = value;
    }

    public Byn(Scanner sc) {
	this.value = sc.nextInt();
    }

    public Byn(Byn byn) {
	this(byn.value);
    }

    public Byn() {
	this.value = 0;
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
	return new StringBuilder().append(value / 100).append(ConstantsUtility.DOT).append(value % 100).toString();
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