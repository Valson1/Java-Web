package by.epam.lab;

import java.util.Scanner;

public class FactoryClass {

    private static enum PurchaseKind {
	GENERAL_PURCHASE {
	    public Purchase getPurchase(Scanner sc) {
		return new Purchase(sc);
	    }
	},
	DISCOUNT_UNIT_PURCHASE {
	    public Purchase getPurchase(Scanner sc) {
		return new DiscountUnitsPurchase(sc);
	    }
	};

	public abstract Purchase getPurchase(Scanner sc);
    }

    public static Purchase getPurchaseFromFactory(int length,Scanner sc) {
	final int PURCHASE_NUMBER_FIELDS = 3;
	PurchaseKind kind = PurchaseKind.values()[length == PURCHASE_NUMBER_FIELDS ? 0 : 1];
	return kind.getPurchase(sc);
    }

}