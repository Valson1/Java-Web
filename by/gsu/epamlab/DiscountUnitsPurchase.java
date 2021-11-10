package by.gsu.epamlab;

public class DiscountUnitsPurchase extends Purchase {
    private double discountForUnit;

    public DiscountUnitsPurchase(String name, int price, int numberOfPurchaseUnits, double discountForUnite) {
	super(name, price, numberOfPurchaseUnits);
	this.discountForUnit = discountForUnite;
    }

    public double getDiscountForUnite() {
	return discountForUnit;
    }

    public void setDiscountForUnite(double discountForUnite) {
	this.discountForUnit = discountForUnite;
    }

    @Override
    public int getCost() {
	return (int) Math.round((getPrice() - discountForUnit) * getNumberOfPurchaseUnits());
    }
    protected String fieldsToString() {
	return getName() + ";" + getPrice() + ";" + getNumberOfPurchaseUnits();
    }
    @Override
    public String toString() {
	return fieldsToString() + ";" + discountForUnit + ";" + UtilityClass.toRubles(getCost());
    }

}
