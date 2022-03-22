package by.epam.lab.utils;

import static by.epam.lab.utils.ConstantsUtility.*;

import by.epam.lab.beans.*;
import by.epam.lab.interfaces.Priceable;

public class PurchaseUtils<T extends Priceable, N extends Number> {
    private final Purchase<T, N> purchase;

    public PurchaseUtils(Purchase<T, N> purchase) {
	this.purchase = purchase;
    }

    public Purchase<T, N> getPurchase() {
	return purchase;
    }

    public void printPurchase() {
	System.out.println(purchase);
    }

    public void printCost() {
	System.out.println(COST + purchase.getCost() + BYN);
    }

    public <E extends Purchase<? extends Priceable, ? extends Number>> void printCostDiff(E purchase) {
	Byn thisGetCost = this.purchase.getCost();
	Byn getCost = purchase.getCost();
	int result = thisGetCost.compareTo(getCost);
	System.out.println(result == 0 ? EMPTY_STRING
		: result > 0 ? POSITIVE + thisGetCost.sub(getCost) : NEGATIVE + getCost.sub(thisGetCost));
    }

    public <E extends Purchase<? extends Priceable, ? extends Number>> void printIsSameCost(E... purchases) {
	boolean isFound = false;
	for (E purchase : purchases) {
	    if (this.purchase.getCost().compareTo(purchase.getCost()) == 0) {
		isFound = true;
		break;
	    }
	}
	System.out.println(isFound ? PURCHASE_FOUND : PURCHASE_NOT_FOUND);
    }
}
