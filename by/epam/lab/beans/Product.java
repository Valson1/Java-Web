package by.epam.lab.beans;

import static by.epam.lab.utils.ConstantsUtility.*;
import by.epam.lab.interfaces.Priceable;

public class Product implements Priceable {
    private final String name;
    private final Byn price;

    public Product(String name, Byn price) {
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
	return getClass().getSimpleName() + SEPARATOR + name + SEPARATOR + price;
    }

    @Override
    public String toString() {
	return fieldsToString() + SEPARATOR + getPrice();
    }
}
