package by.gsu.epamlab;

public class Byn implements Comparable<Byn> {
    private int valueOfFinancialEntity;

    public Byn() {
	super();
    }

    public Byn(int valueFinancialEntity) {
	super();
	this.valueOfFinancialEntity = valueFinancialEntity;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null || getClass() != obj.getClass())
	    return false;
	Byn byn = (Byn) obj;
	return valueOfFinancialEntity == byn.valueOfFinancialEntity ? true : false;
    }

    public int sum(Byn byn) {
	return this.valueOfFinancialEntity + byn.valueOfFinancialEntity;
    }

    public int div(Byn byn) {
	return this.valueOfFinancialEntity / byn.valueOfFinancialEntity;
    }

    public int myltiply(Byn byn) {
	return this.valueOfFinancialEntity * byn.valueOfFinancialEntity;
    }

    public int sub(Byn byn) {
	return this.valueOfFinancialEntity - byn.valueOfFinancialEntity;
    }

    @Override
    public String toString() {
	return String.format("%d.%02d", valueOfFinancialEntity / 100, valueOfFinancialEntity % 100);
    }

    @Override
    public int compareTo(Byn byn) {
	return this.valueOfFinancialEntity - byn.valueOfFinancialEntity;
    }
}
