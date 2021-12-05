package by.gsu.epamlab;

public class PercentageDiscountPurchase extends AbstractPurchase {
    private double discountForUnit;
    private final static int REQURIED_UNIT = 3;

    public PercentageDiscountPurchase(Product product, int numberOfPurchaseUnits, double discountForUnit) {
	super(product, numberOfPurchaseUnits);
	this.discountForUnit = discountForUnit;
    }

    public PercentageDiscountPurchase() {
    }

    public double getDiscountForUnit() {
	return discountForUnit;
    }

    public void setDiscountForUnit(double discountForUnit) {
	this.discountForUnit = discountForUnit;
    }

    @Override
    protected Byn fieldsToGetCost(Byn naturalCost) {
	if (getNumberOfPurchaseUnits() >= REQURIED_UNIT) {
	    naturalCost = naturalCost.multiply(getNumberOfPurchaseUnits()).myltiply(1 - discountForUnit / 100,
		    RoundMethod.FLOOR, 0);
	}
	return naturalCost;
    }

    @Override
    public String fieldsToString() {
	return super.fieldsToString() + ";" + discountForUnit;
    }
}
