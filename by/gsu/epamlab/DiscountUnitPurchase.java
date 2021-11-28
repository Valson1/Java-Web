package by.gsu.epamlab;

public class DiscountUnitPurchase extends AbstractPurchase {
    private double discountForUnit;

    public DiscountUnitPurchase(int numberOfPurchaseUnits, double discountForUnit) {
	super(numberOfPurchaseUnits);
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
    protected Byn fieldsToGetCost() {
	return Product.PRICE.multiply(getNumberOfPurchaseUnits()).myltiply(1 - discountForUnit / 100);
    }

    @Override
    protected String fieldsToString() {
	return String.format("%.0f",discountForUnit);
    }    
}
