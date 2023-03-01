package by.gsu.epamlab;

import java.util.Objects;

public abstract class AbstractPurchase implements Comparable<AbstractPurchase> {
    private final Product product;
    private int numberOfPurchaseUnits;

    public AbstractPurchase(Product product, int numberOfPurchaseUnits) {
	this.numberOfPurchaseUnits = numberOfPurchaseUnits;
	this.product = product;
    }

    public AbstractPurchase() {
	this(null, 0);
    }

    public Product getProduct() {
	return product;
    }

    public int getNumberOfPurchaseUnits() {
	return numberOfPurchaseUnits;
    }

    public void setNumberOfPurchaseUnits(int numberOfPurchaseUnits) {
	this.numberOfPurchaseUnits = numberOfPurchaseUnits;
    }

    public String fieldsToString() {
	return product + ";" + numberOfPurchaseUnits;
    }

    protected abstract Byn fieldsToGetCost(Byn naturalCost);

    public Byn getCost() {
	Byn naturalCost = product.getPrice().multiply(numberOfPurchaseUnits);
	Byn allGetCost = fieldsToGetCost(naturalCost);
	return allGetCost.round(RoundMethod.FLOOR, 2);
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (!(obj instanceof AbstractPurchase))
	    return false;
	AbstractPurchase other = (AbstractPurchase) obj;
	return numberOfPurchaseUnits == other.numberOfPurchaseUnits && product.equals(other.product);
    }

    @Override
    public int compareTo(AbstractPurchase purchase) {
	return purchase.getCost().compareTo(getCost());
    }

    @Override
    public String toString() {
	return fieldsToString() + ";" + getCost();
    }
}
