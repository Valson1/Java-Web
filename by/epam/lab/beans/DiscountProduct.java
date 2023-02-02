package by.epam.lab.beans;

import static by.epam.lab.utils.ConstantsUtility.*;

import by.epam.lab.exceptions.NonPositiveArgumentException;

public class DiscountProduct extends Product {
    private final Byn discount;

    public DiscountProduct(String name, Byn price, Byn discount) {
	super(name, price);
	if (discount.compareTo(new Byn(0)) <= 0 || discount.compareTo(price) >= 0) {
	    throw new NonPositiveArgumentException(EXCEPTION_MESSAGE_DISCOUNT_PRICE + discount);
	}
	this.discount = discount;
    }

    public Byn getDiscount() {
	return discount;
    }

    @Override
    protected String fieldsToString() {
	return super.fieldsToString() + SEPARATOR + discount;
    }

}
