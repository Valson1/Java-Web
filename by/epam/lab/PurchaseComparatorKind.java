package by.epam.lab;

import java.util.Comparator;

public enum PurchaseComparatorKind implements Comparator<Purchase> {
	NAME {
	    @Override
	    public int compare(Purchase o1, Purchase o2) {
		return o1.getName().compareTo(o2.getName());
	    }
	},
	PRICE {
	    @Override
	    public int compare(Purchase o1, Purchase o2) {
		return o1.getPrice().compareTo(o2.getPrice());
	    }
	},
	NUMBER_OF_PURCHASE_UNITS {
	    @Override
	    public int compare(Purchase o1, Purchase o2) {
		return Integer.compare(o1.getNumberOfPurchaseUnits(), o2.getNumberOfPurchaseUnits());
	    }
	},
   }