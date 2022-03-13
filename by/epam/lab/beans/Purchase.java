package by.epam.lab.beans;

import static by.epam.lab.utils.ConstantsUtility.*;
import by.epam.lab.exceptions.*;


public class Purchase {

    private final String name;
    private final Byn price;
    private final int numberOfPurchaseUnits;

    public Purchase() {
	this.name = "";
	this.price = new Byn(0);
	this.numberOfPurchaseUnits = 0;
    }

    public Purchase(String name, Byn price, int numberOfPurchaseUnits) {
	if (name.trim().isEmpty()) {
	    throw new NonPositiveArgumentException(EXCEPTION_MESSAGE_NAME + name);
	}
	if (price.compareTo(new Byn(0)) < 1) {
	    throw new NonPositiveArgumentException(EXCEPTION_MESSAGE_PRICE + price);
	}
	if (numberOfPurchaseUnits <= 0) {
	    throw new NonPositiveArgumentException(EXCEPTION_MESSAGE_NUMBER_OF_UNITS + numberOfPurchaseUnits);
	}
	this.name = name;
	this.price = price;
	this.numberOfPurchaseUnits = numberOfPurchaseUnits;
    }

    private static Purchase getValidPurchase(String[] elements) {
	if (elements.length != PURCHASE_NUMBER_FIELDS) {
	    throw new ArrayIndexOutOfBoundsException(EXCEPTION_MESSAGE_CSV_LENGTH + elements.length);
	}
	return new Purchase(elements[FIRST_ELEMENT], new Byn(elements[SECOND_ELEMENT]),
		Integer.parseInt(elements[THIRD_ELEMENT]));
    }

    public Purchase(String[] elements) {
	this(getValidPurchase(elements));
    }

    public Purchase(Purchase purchase) {
	this(purchase.name, purchase.price, purchase.numberOfPurchaseUnits);
    }

    public String getName() {
	return name;
    }

    public Byn getPrice() {
	return price;
    }

    public int getNumberOfPurchaseUnits() {
	return numberOfPurchaseUnits;
    }

    public Byn getCost() {
	return price.multiply(numberOfPurchaseUnits);
    }

    protected String fieldsToString() {
	return getClass().getSimpleName() + SEPARATOR + name + SEPARATOR + price + SEPARATOR + numberOfPurchaseUnits;
    }

    @Override
    public String toString() {
	return fieldsToString() + SEPARATOR + getCost();
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (!(obj instanceof Purchase))
	    return false;
	Purchase purchase = (Purchase) obj;
	return price.equals(purchase.price) && name.equals(purchase.name);
    }

}
