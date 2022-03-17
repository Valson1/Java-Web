package by.epam.lab.utils;

import static by.epam.lab.utils.ConstantsUtility.*;
import by.epam.lab.beans.*;
import by.epam.lab.services.PurchaseList;

public class PurchaseUtils {
    private final Purchase purchase;
    
    public PurchaseUtils(Purchase purchase) {
	this.purchase = purchase;
    }
    
    public PurchaseUtils(Product product,double numberUnits) {
	purchase = new Purchase(product,numberUnits);
    }
    public Purchase getPurchase() {
	return purchase;
    }
    
    public void printPurchase() {
	System.out.println(purchase);
    }
    
    public void printCost() {
	System.out.println(COST + purchase.getCost());
    }
    
    private String getDiff(Purchase purchase) {
	Byn getCost = purchase.getCost();
	Byn thisGetCost = this.purchase.getCost();
	return thisGetCost.compareTo(getCost) == 0 ? EMPTY_STRING : thisGetCost.sub(getCost).toString();
    }
    
    public void printCostDiff(Purchase purchase) {
	System.out.println(getDiff(purchase));
    }
    
    public void printIsSameCost(PurchaseList purchases) {
	int index = purchases.binarySearch(purchase);
	System.out.println(index > 0 ? PURCHASE_FOUND + purchases.getPurchases().get(index) : PURCHASE_NOT_FOUND);
    }
}
