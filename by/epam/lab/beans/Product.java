package by.epam.lab.beans;

import static by.epam.lab.utils.ConstantsUtility.*;

import java.util.Objects;

public class Product {
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

    @Override
    public int hashCode() {
	return Objects.hash(name, price);
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null || getClass() != obj.getClass())
	    return false;
	Product other = (Product) obj;
	return name.equals(other.name) && price.equals(other.price);
    }

    @Override
    public String toString() {
	return name + SEPARATOR + price;
    }

}
