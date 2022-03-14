package by.epam.lab.services;

import static by.epam.lab.utils.ConstantsUtility.*;

import by.epam.lab.beans.*;
import by.epam.lab.exceptions.*;

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

	protected abstract Purchase getPurchase(String[] elements);
    }

    private static PurchaseKind getPurchaseKind(int csvLineLength) {
	return PurchaseKind.values()[csvLineLength - PURCHASE_NUMBER_FIELDS];
    }

    public static Purchase getPurchaseFromFactory(String csvLine) throws CsvLineException {
	String[] elements = csvLine.split(SEPARATOR);
	try {
	    return getPurchaseKind(elements.length).getPurchase(elements);
	} catch (IndexOutOfBoundsException | IllegalArgumentException e) {
	    throw new CsvLineException(csvLine, e);
	}
    }

}