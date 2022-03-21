package by.epam.lab.utils;

import static by.epam.lab.utils.ConstantsUtility.*;

import by.epam.lab.beans.*;

public class PurchaseUtils {
    private final Purchase purchase;

    public PurchaseUtils(Purchase purchase) {
	this.purchase = purchase;
    }

    public Purchase getPurchase() {
	return purchase;
    }

    public void printPurchase() {
	System.out.println(purchase);
    }

    public void printCost() {
	System.out.println(COST + purchase.getCost() + BYN);
    }

    public void printCostDiff(Purchase purchase) {
	Byn thisGetCost = this.purchase.getCost();
	Byn getCost = purchase.getCost();
	int result = thisGetCost.compareTo(getCost);
	System.out.println(result == 0 ? EMPTY_STRING
		: result > 0 ? POSITIVE + thisGetCost.sub(getCost) : NEGATIVE + getCost.sub(thisGetCost));
    }

    public void printIsSameCost(Purchase... purchases) {
	boolean isFound = false;
	for (Purchase purchase : purchases) {
	    if (this.purchase.getCost().compareTo(purchase.getCost()) == 0) {
		isFound = true;
		break;
	    }
	}
	System.out.println(isFound ? PURCHASE_FOUND : PURCHASE_NOT_FOUND);
    }
}
