package by.gsu.epamlab;

public abstract class AbstractPurchase implements Comparable<AbstractPurchase> {
    public final static Product PRODUCT = new Product();
    private int numberOfPurchaseUnits;

    public AbstractPurchase(int numberOfPurchaseUnits) {
	this.numberOfPurchaseUnits = numberOfPurchaseUnits;
    }
    public AbstractPurchase() {
    }
    
    public int getNumberOfPurchaseUnits() {
	return numberOfPurchaseUnits;
    }

    public void setNumberOfPurchaseUnits(int numberOfPurchaseUnits) {
	this.numberOfPurchaseUnits = numberOfPurchaseUnits;
    }

    protected abstract Byn fieldsToGetCost();

    protected abstract String fieldsToString();

    public Byn getCost() {
	return fieldsToGetCost();
    }

    @Override
    public int compareTo(AbstractPurchase purchase) {
	return purchase.getCost().compareTo(this.getCost());
    }

    @Override
    public String toString() {
	return numberOfPurchaseUnits + ";" + fieldsToString() + ";" + getCost();
    }
}
