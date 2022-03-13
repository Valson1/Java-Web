package by.epam.lab.services;

import static by.epam.lab.utils.ConstantsUtility.*;

import by.epam.lab.beans.*;
import by.epam.lab.exceptions.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class PurchaseList {
    private final List<Purchase> purchases = new ArrayList<>();
    private final Comparator<Purchase> comparator;
    private boolean isSorted = false;

    public PurchaseList(String csvFileName, Comparator<Purchase> comparator) {
	this.comparator = comparator;
	try (Scanner sc = new Scanner(new FileReader(csvFileName))) {
	    while (sc.hasNextLine()) {
		try {
		    purchases.add(FactoryClass.getPurchaseFromFactory(sc.nextLine()));
		} catch (CsvLineException e) {
		    System.err.println(e.getMessage());
		}
	    }
	} catch (FileNotFoundException e) {
	    System.err.println(EXCEPTION_MESSAGE_FILE);
	    isSorted = true;
	}
    }

    public void add(int index, Purchase purchase) {
	int listSize = purchases.size();
	purchases.add(checkRangeIndex(index, listSize), purchase);
	isSorted = false;
    }

    private static int getIndexNearestEnd(int index, int listSize) {
	return index < 0 ? 0 : listSize;
    }

    private static int checkRangeIndex(int index, int listSize) {
	return index < 0 || index >= listSize ? getIndexNearestEnd(index, listSize) : index;
    }

    public int delete(int indexFrom, int indexTo) {
	if (indexFrom > indexTo) {
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
