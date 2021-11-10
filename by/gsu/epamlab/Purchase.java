package by.gsu.epamlab;

public class Purchase {
    public final static String name = "Milk";
    public final static int price = 120;
    private int numberOfPurchaseUnits;

    public Purchase() {
	super();
    }

    public Purchase(int numberOfPurchaseUnits) {
	super();
	this.numberOfPurchaseUnits = numberOfPurchaseUnits;
    }

    public String getName() {
	return name;
    }

    public int getPrice() {
	return price;
    }

    public int getNumberOfPurchaseUnits() {
	return numberOfPurchaseUnits;
    }

    public void setNumberOfPurchaseUnits(int numberOfPurchaseUnits) {
	this.numberOfPurchaseUnits = numberOfPurchaseUnits;
    }

    public int getCost() {
	
    }

    @Override
    public String toString() {
	return numberOfPurchaseUnits + ";";
    }

    @Override
    public boolean equals(Object obj) {
	return super.equals(obj);
    }
    

}
