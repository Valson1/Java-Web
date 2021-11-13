package by.gsu.epamlab;

import java.util.Scanner;

public class FactoryClass {

    public enum PurchaseKind {
	GENERAL_PURCHASE {
	    public Purchase getPurchase(Scanner sc) {
		return new Purchase(sc);
	    }
	},
	DISCOUNT_UNIT_PURCHASE {
	    public Purchase getPurchase(Scanner sc) {
		return new DiscountUnitsPurchase(sc);
	    }
	},
	DISCOUNT_PURCHASE {
	    public Purchase getPurchase(Scanner sc) {
		return new DiscountPurchase(sc);
	    }
	};

	public abstract Purchase getPurchase(Scanner sc);
    }

    public static Purchase getPurchaseFromFactory(Purchase purchase, Scanner sc) {
	PurchaseKind kind = PurchaseKind.valueOf(sc.next());
	return kind.getPurchase(sc);
    }

}
