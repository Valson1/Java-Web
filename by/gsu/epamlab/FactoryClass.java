package by.gsu.epamlab;

import java.util.Scanner;

public class FactoryClass {
    private static enum PurchaseKind {
	GENERAL_PURCHASE, DISCOUNT_UNIT_PURCHASE, DISCOUNT_PURCHASE
    }

    public static Purchase getPurchaseFromFactory(Purchase purchase, Scanner sc) {
	PurchaseKind kind = PurchaseKind.valueOf(sc.next());
	switch (kind) {
	case GENERAL_PURCHASE:
	    purchase = new Purchase(sc.next(), sc.nextInt(), sc.nextInt());
	    break;
	case DISCOUNT_UNIT_PURCHASE:
	    purchase = new DiscountUnitsPurchase(sc.next(), sc.nextInt(), sc.nextInt(), sc.nextDouble());
	    break;
	case DISCOUNT_PURCHASE:
	    purchase = new DiscountUnitsPurchase(sc.next(), sc.nextInt(), sc.nextInt(), sc.nextDouble());
	    break;
	default:
	    throw new IllegalArgumentException();
	}
	return purchase;
    }
}