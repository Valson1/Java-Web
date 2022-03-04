package by.epam.lab;

import java.util.Scanner;

import by.epam.lab.Purchase;

public class DiscountUnitsPurchase extends Purchase {
    private final Byn discountForUnit;

    public DiscountUnitsPurchase() {
	this(null, null, 0, null);
    }

    public DiscountUnitsPurchase(String name, Byn price, int numberOfPurchaseUnits, Byn discountForUnit) {
	super(name, price, numberOfPurchaseUnits);
	if (discountForUnit.compareTo(price) >= 0 || discountForUnit.compareTo(new Byn(0)) <= 0) {
	    throw new IllegalArgumentException();
	}
	this.discountForUnit = discountForUnit;
    }

    public DiscountUnitsPurchase(Scanner sc) {
	this(sc.next(), new Byn(sc), sc.nextInt(),new Byn(sc));
    }

    public Byn getDiscountForUnite() {
	return discountForUnit;
    }

    @Override
    public Byn getCost() {
	Byn byn = new Byn(getPrice());
	return byn.sub(discountForUnit).multiply(getNumberOfPurchaseUnits(), RoundMethod.ROUND, 0);
    }

    @Override
    protected String fieldsToString() {
	return super.fieldsToString() + ";" + discountForUnit;
    }

}
