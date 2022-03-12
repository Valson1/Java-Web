package services;

import static utils.ConstantsUtility.*;

import beans.DiscountUnitsPurchase;
import beans.Purchase;
import exceptions.CsvLineException;
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
	return csvLineLength == DISCOUNT_PURCHASE_NUMBER_FIELDS ? PurchaseKind.DISCOUNT_UNIT_PURCHASE
		: PurchaseKind.GENERAL_PURCHASE;
    }

    public static Purchase getPurchaseFromFactory(String csvLine) throws CsvLineException {
	String[] elements = csvLine.split(SEPARATOR);
	try {
	    return getPurchaseKind(elements.length).getPurchase(elements);
	} catch (IndexOutOfBoundsException | IllegalArgumentException e) {
	    throw new CsvLineException(e.getMessage(), e);
	}
    }

}