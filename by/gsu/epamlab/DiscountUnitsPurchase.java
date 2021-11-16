package by.gsu.epamlab;

import java.util.Scanner;

public class DiscountUnitsPurchase extends Purchase {
    private Byn discountForUnit;

    public DiscountUnitsPurchase(String name, Byn price, int numberOfPurchaseUnits, Byn discountForUnite) {
	super(name, price, numberOfPurchaseUnits);
	this.discountForUnit = discountForUnite;
    }

    public DiscountUnitsPurchase(Scanner sc) {
	super(sc.next(), new Byn(sc.nextInt()), sc.nextInt());
	this.discountForUnit = new Byn(sc.nextInt());
    }

    public Byn getDiscountForUnite() {
	return discountForUnit;
    }

    public void setDiscountForUnite(Byn discountForUnite) {
	this.discountForUnit = discountForUnite;
    }

    @Override
    public Byn getCost() {
	Byn byn = new Byn(getPrice());
	return byn.sub(discountForUnit).multiply(getNumberOfPurchaseUnits(),RoundMethod.ROUND, 2);
    }

    @Override
    protected String fieldsToString() {
	return super.fieldsToString() + ";" + discountForUnit;
    }

}
