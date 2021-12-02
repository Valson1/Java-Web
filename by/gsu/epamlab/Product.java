package by.gsu.epamlab;

import java.util.Objects;

public class Product {
    private String name;
    private Byn price;

    public Product(String name, Byn price) {
	this.name = name;
	this.price = new Byn(price);
    }

    public Product() {
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public Byn getPrice() {
	return price;
    }

    public void setPrice(Byn price) {
	this.price = price;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	Product other = (Product) obj;
	return name.equals(other.name) && price.equals(other.price);
    }

    @Override
    public String toString() {
	return name + ";" + price;
    }

}
