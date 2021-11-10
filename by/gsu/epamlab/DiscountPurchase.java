package by.gsu.epamlab;

public class DiscountPurchase extends Purchase {
    public final static double requiredDiscount = 15;
    private double discountOfPurchase;

    public DiscountPurchase(String name, int price, 
	    int numberOfPurchaseUnits, double discountOfPurchase) {
	super(name, price, numberOfPurchaseUnits);
	this.discountOfPurchase = discountOfPurchase;
    }

    public DiscountPurchase() {
	super();
    }

    public double getDiscountOfPurchase() {
	return discountOfPurchase;
    }

    public void setDiscountOfPurchase(double discountOfPurchase) {
	this.discountOfPurchase = discountOfPurchase;
    }

    @Override
    public int getCost() {
	if (getNumberOfPurchaseUnits() > requiredDiscount) {
	    return (int) Math.round(getPrice() * getNumberOfPurchaseUnits() * (1 - discountOfPurchase / 100));	    
	}
	else {
	    return super.getCost();
	}
    }

    @Override
    public String toString() {
	return super.toString() + ";" + discountOfPurchase + ";" + UtilityClass.toRubles(getCost());
    }

}
