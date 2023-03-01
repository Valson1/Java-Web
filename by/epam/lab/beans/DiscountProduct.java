package by.epam.lab.beans;

import static by.epam.lab.utils.ConstantsUtility.*;

public class DiscountProduct extends Product {
    private final Byn discount;

    public DiscountProduct(String name, Byn price, Byn discount) {
	super(name, price);
	this.discount = discount;
    }

    public Byn getDiscount() {
	return discount;
    }

    @Override
    public Byn getPrice() {
	return super.getPrice().sub(discount);
    }

    @Override
    protected String fieldsToString() {
	return super.fieldsToString() + SEPARATOR + discount;
    }

}
