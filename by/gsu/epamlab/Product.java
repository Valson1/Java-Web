package by.gsu.epamlab;

public class Product {
    public final static String NAME = "Milk";
    public final static Byn PRICE = new Byn(140);

    public Product() {
    }

    @Override
    public String toString() {
	return NAME + ";" + PRICE;
    }

}
