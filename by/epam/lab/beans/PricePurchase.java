package by.epam.lab.beans;

import static by.epam.lab.utils.ConstantsUtility.*;

public class PricePurchase extends Purchase {
    private final Byn discountForUnit;

    public PricePurchase(Product product, int numberOfPurchaseUnits, Byn discountForUnite) {
	super(product, numberOfPurchaseUnits);
	this.discountForUnit = discountForUnite;
    }

    public PricePurchase(String[] elements) {
	this(new Product(elements[PRODUCT_NAME_FIELD], new Byn(elements[PRODUCT_PRICE_FIELD])),
		Integer.parseInt(elements[NUMBER_UNITS_FIELD]), new Byn(elements[DISCOUNT_FIELD]));
    }

    public Byn getDiscountForUnite() {
	return discountForUnit;
    }

    @Override
    public Byn getCost() {
	return getProduct().getPrice().sub(discountForUnit).multiply(getNumberOfPurchaseUnits());
    }

    @Override
    protected String fieldsToString() {
	return super.fieldsToString() + SEPARATOR + discountForUnit;
    }
}
