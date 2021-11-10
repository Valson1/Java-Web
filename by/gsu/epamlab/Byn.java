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
	return super.equals(obj);
    }

    @Override
    public String toString() {
	return valueOfFinancialEntity + ";";
    }

    @Override
    public int compareTo(Byn byn) {
	return this.valueOfFinancialEntity - byn.valueOfFinancialEntity ;
    }
}
