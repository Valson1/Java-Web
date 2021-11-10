package by.gsu.epamlab;

public class Purchase {

    private String name;
    private int price;
    private int numberOfPurchaseUnits;

    public Purchase() {

    }

    public Purchase(String name, int price, int numberOfPurchaseUnits) {
	super();
	this.name = name;
	this.price = price;
	this.numberOfPurchaseUnits = numberOfPurchaseUnits;
    }
    
    public String getName() {
	return name;
    }

    public int getPrice() {
	return price;
    }

    public void setPrice(int price) {
	this.price = price;
    }

    public int getNumberOfPurchaseUnits() {
	return numberOfPurchaseUnits;
    }

    public void setNumberOfPurchaseUnits(int numberOfPurchaseUnits) {
	this.numberOfPurchaseUnits = numberOfPurchaseUnits;
    }

    public int getCost() {
	return price * numberOfPurchaseUnits;
    }

    @Override
    public String toString() {
	return name + ";" + UtilityClass.toRubles(price) + ";" + numberOfPurchaseUnits;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null || getClass() != obj.getClass())
	    return false;
	Purchase purchase = (Purchase) obj;
	if (price != purchase.price) {
	    return false;
	}
	return purchase.name != null ? name.equals(purchase.name) : purchase.name == null;
    }

}
