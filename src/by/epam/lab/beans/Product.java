package by.epam.lab.beans;

import static by.epam.lab.utils.ConstantsUtility.*;


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
	final int prime = 31;
	int result = 17;
	result = prime * result + (name == null ? 0 : name.hashCode());
	result = prime * result + (price == null ? 0 : price.hashCode());
	return result;
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
