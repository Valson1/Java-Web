package beans;

import exceptions.NonpositiveArgumentException;
import services.RoundMethod;

import static utils.ConstantsUtility.*;

import java.util.Objects;

public class DiscountUnitsPurchase extends Purchase {
    private final Byn discountForUnit;

    public DiscountUnitsPurchase() {
	super();
	this.discountForUnit = new Byn(0);
    }

    public DiscountUnitsPurchase(String name, Byn price, int numberOfPurchaseUnits, Byn discountForUnit) {
	super(name, price, numberOfPurchaseUnits);
	if (discountForUnit.compareTo(price) >= 0) {
	    throw new NonpositiveArgumentException(EXCEPTION_MESSAGE_DISCOUNT_PRICE + discountForUnit);
	}
	if (discountForUnit.compareTo(new Byn(0)) <= 0) {
	    throw new NonpositiveArgumentException(EXCEPTION_MESSAGE_DISCOUNT_ZERO + discountForUnit);
	}
	this.discountForUnit = discountForUnit;
    }

    private static DiscountUnitsPurchase getValidDiscountPurchase(String[] elements) {
	if (elements.length != DISCOUNT_PURCHASE_NUMBER_FIELDS) {
	    throw new ArrayIndexOutOfBoundsException(EXCEPTION_MESSAGE_CSV_LENGTH + elements.length);
	}
	return new DiscountUnitsPurchase(elements[FIRST_ELEMENT],
		new Byn(Integer.parseInt(elements[SECOND_ELEMENT])),
		Integer.parseInt(elements[THIRD_ELEMENT]),
		new Byn(Integer.parseInt(elements[FOURTH_ELEMENT])));
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
	return getPrice().sub(discountForUnit).multiply(getNumberOfPurchaseUnits(), RoundMethod.ROUND, 0);
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
	return super.equals(other) && discountForUnit.equals(other.discountForUnit);
    }
    
    

}
