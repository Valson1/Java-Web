package by.epam.lab;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import by.epam.lab.*;

public class Purchase {

    private final String name;
    private final Byn price;
    private final int numberOfPurchaseUnits;

    public Purchase() {
	this(null, new Byn(), 0);

    }

    public Purchase(String name, Byn price, int numberOfPurchaseUnits) {
	if (name.isEmpty()) {
	    throw new IllegalArgumentException("Name is empty");
	}
	if (price.getValue() <= 0) {
	    throw new IllegalArgumentException("Price is less or equal to zero");
	}
	if (numberOfPurchaseUnits <= 0) {
	    throw new IllegalArgumentException("Number of units is less or equal to zero");
	}
	this.name = name;
	this.price = price;
	this.numberOfPurchaseUnits = numberOfPurchaseUnits;
    }

    public Purchase(String[] elements) {
	this(elements[ConstantsUtility.FIRST_ELEMENT],
		new Byn(Integer.parseInt(elements[ConstantsUtility.SECOND_ELEMENT])),
		Integer.parseInt(elements[ConstantsUtility.THIRD_ELEMENT]));
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
	Byn byn = new Byn(price);
	return byn.multiply(numberOfPurchaseUnits);
    }

    protected String fieldsToString() {
	return new StringBuilder(name).append(ConstantsUtility.SEPARATOR).append(price)
		.append(ConstantsUtility.SEPARATOR).append(numberOfPurchaseUnits).toString();
    }

    @Override
    public String toString() {
	return fieldsToString();
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
