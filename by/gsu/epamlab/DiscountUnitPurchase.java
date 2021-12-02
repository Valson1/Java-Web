package by.gsu.epamlab;

public class DiscountUnitPurchase extends AbstractPurchase {
    private double discountForUnit;
    private final static int REQURIED_UNIT = 7;

    public DiscountUnitPurchase(Product product, int numberOfPurchaseUnits, double discountForUnit) {
	super(product, numberOfPurchaseUnits);
	this.discountForUnit = discountForUnit;
    }

    public DiscountUnitPurchase() {
    }

    public double getDiscountForUnit() {
	return discountForUnit;
    }

    public void setDiscountForUnit(double discountForUnit) {
	this.discountForUnit = discountForUnit;
    }

    @Override
    protected Byn fieldsToGetCost(Byn price) {
	Byn getCostPurchase = price.multiply(getNumberOfPurchaseUnits());
	if (getNumberOfPurchaseUnits() > REQURIED_UNIT) {
	    getCostPurchase = price.multiply(getNumberOfPurchaseUnits()).myltiply(1 - discountForUnit / 100,
		    RoundMethod.FLOOR, 2);
	}
	return getCostPurchase;
    }

    @Override
    public String fieldsToString() {
	return getNumberOfPurchaseUnits() + ";" + discountForUnit;
    }
}
