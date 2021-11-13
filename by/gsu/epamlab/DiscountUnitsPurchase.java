package by.gsu.epamlab;

import java.util.Scanner;

public class DiscountUnitsPurchase extends Purchase {
    private double discountForUnit;

    public DiscountUnitsPurchase(String name, Byn price, int numberOfPurchaseUnits, double discountForUnite) {
	super(name, price, numberOfPurchaseUnits);
	this.discountForUnit = discountForUnite;
    }

    public DiscountUnitsPurchase(Scanner sc) {
	super(sc.next(), new Byn(sc.nextInt()), sc.nextInt());
	this.discountForUnit = sc.nextDouble();
    }

    public double getDiscountForUnite() {
	return discountForUnit;
    }

    public void setDiscountForUnite(double discountForUnite) {
	this.discountForUnit = discountForUnite;
    }

    @Override
    public Byn getCost() {
	return getPrice().sub(discountForUnit).multiply(getNumberOfPurchaseUnits());
    }

    @Override
    public String toString() {
	return fieldsToString() + discountForUnit + ";" + getCost();
    }

}
