package by.epam.lab.services;

import static by.epam.lab.utils.ConstantsUtility.*;

import by.epam.lab.beans.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class PurchaseList {
    private final List<Purchase> purchases = new ArrayList<>();
    private final Comparator<Purchase> comparator;
    private boolean isSorted = false;

    public PurchaseList(Comparator<Purchase> comparator) {
	this.comparator = comparator;
    }
    
    public boolean isSorted() {
	return isSorted;
    }
    
    public List<Purchase> getPurchases() {
        return Collections.unmodifiableList(purchases);
    }

    public void add(int index, Purchase purchase) {
	int listSize = purchases.size();
	purchases.add(checkRangeIndex(index, listSize), purchase);
	isSorted = false;
    }

    private static int checkRangeIndex(int index, int listSize) {
	return index < 0 || index >= listSize ?  (index < 0 ? 0 : listSize) : index;
    }

    public int delete(int indexFrom, int indexTo) {
	if (indexFrom >= indexTo) {
	    return 0;
	}
	int listSize = purchases.size();
	indexFrom = checkRangeIndex(indexFrom, listSize);
	indexTo = checkRangeIndex(indexTo, listSize);
	List<Purchase> subList = purchases.subList(indexFrom, indexTo);
	int subListLength = subList.size();
	subList.clear();
	return subListLength;
    }

    public Byn getCost() {
	Byn getCost = new Byn();
	for (Purchase purchase : purchases) {
	    getCost = getCost.sum(purchase.getCost());
	}
	return getCost;
    }

    public void sort() {
	if (!isSorted) {
	    purchases.sort(comparator);
	}
	isSorted = true;
    }

    public int binarySearch(Purchase key) {
	sort();
	return Collections.binarySearch(purchases, key, comparator);
    }

    @Override
    public String toString() {
	final int STRING_LENGTH_UNIT = 1;
	StringBuilder result = new StringBuilder();
	for (Purchase purchase : purchases) {
	    result.append(purchase).append(SEPARATOR);
	}
	if (!purchases.isEmpty()) {
	    result.deleteCharAt(result.length() - STRING_LENGTH_UNIT);
	}
	return result.toString();
    }

}
