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

    @Override
    public String toString() {
	return UtilityClass.toRubles(valueOfFinancialEntity);
    }

    @Override
    public int compareTo(Byn byn) {
	return this.valueOfFinancialEntity - byn.valueOfFinancialEntity ;
    }
}
