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
	this(null,new Byn(),0);
	
    }

    public Purchase(String name, Byn price, int numberOfPurchaseUnits) {
	if(name.isBlank() || price.compareTo(new Byn(0)) <= 0 || numberOfPurchaseUnits <= 0) {
	    throw new IllegalArgumentException();
	}
	this.name = name;
	this.price = price;
	this.numberOfPurchaseUnits = numberOfPurchaseUnits;
    }

    public Purchase(Scanner sc) {
	this(sc.next(),new Byn(sc), sc.nextInt());
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
	return name + ";" + price + ";" + numberOfPurchaseUnits;
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
