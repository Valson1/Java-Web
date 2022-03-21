package by.epam.lab.utils;

import static by.epam.lab.utils.ConstantsUtility.*;

import by.epam.lab.beans.*;
import by.epam.lab.interfaces.Priceble;

public class PurchaseUtils<T extends Priceble, K extends Number> {
    private final Purchase<T, K> purchase;

    public PurchaseUtils(Purchase<T, K> purchase) {
	this.purchase = purchase;
    }

    public PurchaseUtils(T item, K numberUnits) {
	this.purchase = new Purchase<>(item, numberUnits);
    }

    public Purchase<T, K> getPurchase() {
	return purchase;
    }

    public void printPurchase() {
	System.out.println(purchase);
    }

    public void printCost() {
	System.out.println(COST + purchase.getCost() + BYN);
    }

    public void printCostDiff(Purchase<? extends Priceble, ? extends Number> purchase) {
	Byn thisGetCost = this.purchase.getCost();
	Byn getCost = purchase.getCost();
	int result = thisGetCost.compareTo(getCost);
	System.out.println(result == 0 ? EMPTY_STRING
		: result > 0 ? POSITIVE + thisGetCost.sub(getCost) : NEGATIVE + getCost.sub(thisGetCost));
    }

    public void printIsSameCost(Purchase<? extends Priceble, ? extends Number>... purchases) {
	boolean isFound = false;
	Purchase<? extends Priceble, ? extends Number> foundPurchase = null;
	for (Purchase<? extends Priceble, ? extends Number> purchase : purchases) {
	    isFound = false;
	    if (this.purchase.compareTo(purchase) == 0) {
		isFound = true;
	    }
	}
	System.out.println(isFound ? PURCHASE_FOUND + foundPurchase : PURCHASE_NOT_FOUND);
    }
}
