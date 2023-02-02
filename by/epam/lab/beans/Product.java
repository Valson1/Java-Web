package by.epam.lab.beans;

import static by.epam.lab.utils.ConstantsUtility.*;
import by.epam.lab.exceptions.NonPositiveArgumentException;

public class Product {
    private final String name;
    private final Byn price;

    public Product(String name, Byn price) {
	if (price.compareTo(new Byn(0)) <= 0) {
	    throw new NonPositiveArgumentException(EXCEPTION_MESSAGE_PRICE + price);
	}
	if (name.isEmpty()) {
	    throw new NonPositiveArgumentException(EXCEPTION_MESSAGE_NAME + name);
	}
	this.name = name;
	this.price = price;
    }

    public String getName() {
	return name;
    }

    public Byn getPrice() {
	return price;
    }

    protected String fieldsToString() {
	return name + SEPARATOR + price;
    }

    @Override
    public String toString() {
	return fieldsToString();
    }

}
