package by.epam.lab.beans;

import by.epam.lab.exceptions.*;
import static by.epam.lab.utils.ConstantsUtility.*;

public class DiscountUnitsPurchase extends Purchase {
    private final Byn discountForUnit;

    public DiscountUnitsPurchase(String name, Byn price, int numberOfPurchaseUnits, Byn discountForUnit) {
	super(name, price, numberOfPurchaseUnits);
	if (discountForUnit.compareTo(price) >= 0 || discountForUnit.compareTo(new Byn(0)) <= 0) {
	    throw new NonPositiveArgumentException(EXCEPTION_MESSAGE_DISCOUNT_PRICE + discountForUnit);
	}
	this.discountForUnit = discountForUnit;
    }

    private static DiscountUnitsPurchase getValidDiscountPurchase(String[] elements) {
	if (elements.length != DISCOUNT_PURCHASE_NUMBER_FIELDS) {
	    throw new ArrayIndexOutOfBoundsException(EXCEPTION_MESSAGE_CSV_LENGTH + elements.length);
	}
	return new DiscountUnitsPurchase(elements[FIRST_ELEMENT], new Byn(Integer.parseInt(elements[SECOND_ELEMENT])),
		Integer.parseInt(elements[THIRD_ELEMENT]), new Byn(Integer.parseInt(elements[FOURTH_ELEMENT])));
    }

    public DiscountUnitsPurchase(String[] elements) {
	this(getValidDiscountPurchase(elements));
    }

    public DiscountUnitsPurchase(DiscountUnitsPurchase discountPurchase) {
	this(discountPurchase.getName(), discountPurchase.getPrice(), discountPurchase.getNumberOfPurchaseUnits(),
		discountPurchase.discountForUnit);
    }

    public Byn getDiscountForUnite() {
	return discountForUnit;
    }

    @Override
    public Byn getCost() {
	return getPrice().sub(discountForUnit).multiply(getNumberOfPurchaseUnits());
    }

    @Override
    protected String fieldsToString() {
	return super.fieldsToString() + SEPARATOR + discountForUnit;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (!super.equals(obj))
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	DiscountUnitsPurchase other = (DiscountUnitsPurchase) obj;
	return discountForUnit.equals(other.discountForUnit);
    }

}
