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
	if (discountForUnit.getValue() >= price.getValue()) {
	    throw new IllegalArgumentException("Discount is more or equal to price");
	}
	if (discountForUnit.getValue() <= 0) {
	    throw new IllegalArgumentException("Discount is less or equal to zero");
	}
	this.discountForUnit = discountForUnit;
    }

    public DiscountUnitsPurchase(String[] elements) {
	this(elements[ConstantsUtility.FIRST_ELEMENT],
		new Byn(Integer.parseInt(elements[ConstantsUtility.SECOND_ELEMENT])),
		Integer.parseInt(elements[ConstantsUtility.THIRD_ELEMENT]),
		new Byn(Integer.parseInt(elements[ConstantsUtility.FOURTH_ELEMENT])));
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
	return new StringBuilder(super.fieldsToString()).append(ConstantsUtility.SEPARATOR).append(discountForUnit).toString();
    }

}
