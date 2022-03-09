package by.epam.lab;

public class FactoryClass {

    private enum PurchaseKind {
	GENERAL_PURCHASE {
	    public Purchase getPurchase(String[] elements) {
		return new Purchase(elements);
	    }
	},
	DISCOUNT_UNIT_PURCHASE {
	    public Purchase getPurchase(String[] elements) {
		return new DiscountUnitsPurchase(elements);
	    }
	};

	public abstract Purchase getPurchase(String[] elements);
    }

    public static Purchase getPurchaseFromFactory(String csvLine) {
	String [] elements = csvLine.split(ConstantsUtility.SEPARATOR);
	int length = elements.length;
	    if(length < ConstantsUtility.PURCHASE_NUMBER_FIELDS || length > ConstantsUtility.DISCOUNT_PURCHASE_NUMBER_FIELDS) {
		throw new IllegalArgumentException();
	    }
	PurchaseKind kind = PurchaseKind.values()[length == ConstantsUtility.PURCHASE_NUMBER_FIELDS ? 0 : 1];
	return kind.getPurchase(elements);
    }

}