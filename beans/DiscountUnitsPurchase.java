package beans;

import by.epam.lab.Byn;
import by.epam.lab.ConstantsUtility;
import by.epam.lab.RoundMethod;

public class DiscountUnitsPurchase extends Purchase {
    private final Byn discountForUnit;

    public DiscountUnitsPurchase() {
	super();
	this.discountForUnit = new Byn(0);
    }

    public DiscountUnitsPurchase(String name, Byn price, int numberOfPurchaseUnits, Byn discountForUnit) {
	super(name, price, numberOfPurchaseUnits);
	if (discountForUnit.getValue() >= price.getValue()) {
	    throw new IllegalArgumentException(ConstantsUtility.EXCEPTION_MESSAGE_DISCOUNT_PRICE + discountForUnit);
	}
	if (discountForUnit.getValue() == 0) {
	    throw new IllegalArgumentException(ConstantsUtility.EXCEPTION_MESSAGE_DISCOUNT_ZERO + discountForUnit);
	}
	this.discountForUnit = discountForUnit;
    }

    private static DiscountUnitsPurchase getValidDiscountPurchase(String[] elements) {
	return new DiscountUnitsPurchase(elements[ConstantsUtility.FIRST_ELEMENT],
		new Byn(Integer.parseInt(elements[ConstantsUtility.SECOND_ELEMENT])),
		Integer.parseInt(elements[ConstantsUtility.THIRD_ELEMENT]),
		new Byn(Integer.parseInt(elements[ConstantsUtility.FOURTH_ELEMENT])));
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
	Byn byn = new Byn(getPrice());
	return byn.sub(discountForUnit).multiply(getNumberOfPurchaseUnits(), RoundMethod.ROUND, 0);
    }

    @Override
    protected String fieldsToString() {
	return super.fieldsToString() + ConstantsUtility.SEPARATOR + discountForUnit;
    }

}
