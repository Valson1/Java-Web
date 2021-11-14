package by.gsu.epamlab;

import java.util.Scanner;

public class DiscountPurchase extends Purchase {
    public final static double REQUIRED_DISCOUNT = 5;
    private double discountOfPurchase;

    public DiscountPurchase(String name, Byn price, int numberOfPurchaseUnits, double discountOfPurchase) {
	super(name, price, numberOfPurchaseUnits);
	this.discountOfPurchase = discountOfPurchase;
    }

    public DiscountPurchase(Scanner sc) {
	super(sc.next(), new Byn(sc.nextInt()), sc.nextInt());
	this.discountOfPurchase = sc.nextDouble();
    }

    public double getDiscountOfPurchase() {
	return discountOfPurchase;
    }

    public void setDiscountOfPurchase(double discountOfPurchase) {
	this.discountOfPurchase = discountOfPurchase;
    }

    @Override
    public Byn getCost() {
	Byn purchaseGetCost = super.getCost();
	if (getNumberOfPurchaseUnits() >= REQUIRED_DISCOUNT) {
	    purchaseGetCost = purchaseGetCost.multiply(1 - discountOfPurchase / 100, RoundMethod.ROUND, 2);
	}
	return purchaseGetCost;
    }

    @Override
    protected String fieldsToString() {
	return super.fieldsToString() + ";" + discountOfPurchase;
    }

}
