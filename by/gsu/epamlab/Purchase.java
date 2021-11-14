package by.gsu.epamlab;

import java.util.Scanner;

public class Purchase {

    private String name;
    private Byn price;
    private int numberOfPurchaseUnits;

    public Purchase() {

    }

    public Purchase(String name, Byn price, int numberOfPurchaseUnits) {
	this.name = name;
	this.price = price;
	this.numberOfPurchaseUnits = numberOfPurchaseUnits;
    }

    public Purchase(Scanner sc) {
	this.name = sc.next();
	this.price = new Byn(sc.nextInt());
	this.numberOfPurchaseUnits = sc.nextInt();
    }

    public String getName() {
	return name;
    }

    public Byn getPrice() {
	return price;
    }

    public void setPrice(Byn price) {
	this.price = price;
    }

    public int getNumberOfPurchaseUnits() {
	return numberOfPurchaseUnits;
    }

    public void setNumberOfPurchaseUnits(int numberOfPurchaseUnits) {
	this.numberOfPurchaseUnits = numberOfPurchaseUnits;
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
	return fieldsToString() + ";" + getCost();
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null || !(obj instanceof Purchase))
	    return false;
	Purchase purchase = (Purchase) obj;
	if (price != purchase.price) {
	    return false;
	}
	return purchase.name != null ? name.equals(purchase.name) : purchase.name == null;
    }

}
