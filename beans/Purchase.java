package beans;

import by.epam.lab.Byn;
import by.epam.lab.ConstantsUtility;

public class Purchase {

    private final String name;
    private final Byn price;
    private final int numberOfPurchaseUnits;

    public Purchase() {
	this.name = "";
	this.price = new Byn(0);
	this.numberOfPurchaseUnits = 0;
    }

    public Purchase(String name, Byn price, int numberOfPurchaseUnits) {
	if (name.isEmpty()) {
	    throw new IllegalArgumentException(ConstantsUtility.EXCEPTION_MESSAGE_NAME);
	}
	if (price.getValue() == 0) {
	    throw new IllegalArgumentException(ConstantsUtility.EXCEPTION_MESSAGE_PRICE + price);
	}
	if (numberOfPurchaseUnits <= 0) {
	    throw new IllegalArgumentException(
		    ConstantsUtility.EXCEPTION_MESSAGE_NUMBER_OF_UNITS + numberOfPurchaseUnits);
	}
	this.name = name;
	this.price = price;
	this.numberOfPurchaseUnits = numberOfPurchaseUnits;
    }

    private static Purchase getValidPurchase(String[] elements) {
	if (elements.length != ConstantsUtility.PURCHASE_NUMBER_FIELDS) {
	    throw new ArrayIndexOutOfBoundsException(ConstantsUtility.EXCEPTION_MESSAGE_CSV_LENGTH + elements.length);
	}
	return new Purchase(elements[ConstantsUtility.FIRST_ELEMENT],
		new Byn(elements[ConstantsUtility.SECOND_ELEMENT]),
		Integer.parseInt(elements[ConstantsUtility.THIRD_ELEMENT]));
    }

    public Purchase(String[] elements) {
	this(getValidPurchase(elements));
    }

    public Purchase(Purchase purchase) {
	this(purchase.name, purchase.price, purchase.numberOfPurchaseUnits);
    }

    public String getName() {
	return name;
    }

    public Byn getPrice() {
	return price;
    }

    public int getNumberOfPurchaseUnits() {
	return numberOfPurchaseUnits;
    }

    public Byn getCost() {
	Byn byn = new Byn(price);
	return byn.multiply(numberOfPurchaseUnits);
    }

    protected String fieldsToString() {
	return name + ConstantsUtility.SEPARATOR + price + ConstantsUtility.SEPARATOR + numberOfPurchaseUnits;
    }

    @Override
    public String toString() {
	return fieldsToString();
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (!(obj instanceof Purchase))
	    return false;
	Purchase purchase = (Purchase) obj;
	return price.equals(purchase.price) && name.equals(purchase.name);
    }

}
