package by.epam.lab.services;

import by.epam.lab.beans.PricePurchase;
import by.epam.lab.beans.Purchase;
import static by.epam.lab.utils.ConstantsUtility.*;

public class PurchaseFactory {
    private enum PurchaseKind {
	GENERAL_PURCHASE {
	    @Override
	    protected Purchase getPurchase(String[] elements) {
		return new Purchase(elements);
	    }
	},
	PRICE_PURCHASE {
	    @Override
	    protected Purchase getPurchase(String[] elements) {
		return new PricePurchase(elements);
	    }
	};

	protected abstract Purchase getPurchase(String[] elements);
    }

    private static PurchaseKind getPurchaseKind(String[] elements) {
	return PurchaseKind.values()[elements.length - PURCHASE_NUMBER_FIELDS];
    }

    public static Purchase getPurchaseFromFactory(String csvLine) {
	String[] elements = csvLine.split(SEPARATOR);
	return getPurchaseKind(elements).getPurchase(elements);
    }
}
