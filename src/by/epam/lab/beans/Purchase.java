package by.epam.lab.beans;

import static by.epam.lab.utils.ConstantsUtility.*;

public class Purchase {

    private final Product product;
    private final int numberOfPurchaseUnits;

    public Purchase(String[] elements) {
	this(new Product(elements[PRODUCT_NAME_FIELD], new Byn(elements[PRODUCT_PRICE_FIELD])),
		Integer.parseInt(elements[NUMBER_UNITS_FIELD]));
    }

    public Purchase(Product product, int numberOfPurchaseUnits) {
	this.product = product;
	this.numberOfPurchaseUnits = numberOfPurchaseUnits;
    }

    public Product getProduct() {
	return product;
    }

    public int getNumberOfPurchaseUnits() {
	return numberOfPurchaseUnits;
    }

    public Byn getCost() {
	return product.getPrice().multiply(numberOfPurchaseUnits);
    }

    protected String fieldsToString() {
	return getClass().getSimpleName() + SEPARATOR + product + SEPARATOR + numberOfPurchaseUnits;
    }

    @Override
    public int hashCode() {
	return product.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (!(obj instanceof Purchase))
	    return false;
	Purchase other = (Purchase) obj;
	return product.equals(other.product);
    }

    @Override
    public String toString() {
	return fieldsToString() + SEPARATOR + getCost();
    }

}
