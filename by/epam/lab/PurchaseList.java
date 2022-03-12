package by.epam.lab;

import static utils.ConstantsUtility.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import beans.Byn;
import beans.Purchase;
import exceptions.CsvLineException;
import services.FactoryClass;

public class PurchaseList {
    private List<Purchase> purchases = new ArrayList<>();
    private final Comparator<Purchase> comparator;
    private boolean isSorted = false;

    public PurchaseList(String csvFileName, Comparator<Purchase> comparator) {
	this.comparator = comparator;
	try (Scanner sc = new Scanner(new FileReader(csvFileName))) {
	    while (sc.hasNextLine()) {
		try {
		    purchases.add(FactoryClass.getPurchaseFromFactory(sc.nextLine()));
		} catch (CsvLineException e) {
		    System.err.println(e + SPACE + e.getMessage());
		}
	    }
	} catch (FileNotFoundException e) {
	    System.err.println("File is not found");
	    isSorted = true;
	}
    }

    public List<Purchase> getPurchases() {
	return Collections.unmodifiableList(purchases);
    }

    public void setPurchases(List<Purchase> purchases) {
	this.purchases = purchases;
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
	int listSize = purchases.size();
	if (indexFrom > indexTo) {
	    throw new IllegalArgumentException(
		    EXCEPTION_MESSAGE_DELETE + indexFrom + SPACE + indexTo);
	}
	indexFrom = checkRangeIndex(indexFrom, listSize);
	indexTo = checkRangeIndex(indexTo, listSize);
	List<Purchase> subList = purchases.subList(indexFrom, indexTo);
	int subListLength = subList.size();
	subList.clear();
	return subListLength;
    }

    public Byn getCost() {
	Byn getCost = new Byn();
	for (int i = 0; i < purchases.size(); i++) {
	    getCost = getCost.sum(purchases.get(i).getCost());
	}
	return getCost;
    }

    public void sort() {
	Collections.sort(purchases, comparator);
	this.isSorted = true;
    }

    public int binarySearch(Purchase key) {
	if (!isSorted) {
	    sort();
	}
	return Collections.binarySearch(purchases, key, comparator);
    }

    @Override
    public String toString() {
	StringBuilder result = new StringBuilder();
	for (int i = 0; i < purchases.size(); i++) {
	    result.append(purchases.get(i)).append(SEPARATOR);
	}
	if (!purchases.isEmpty()) {
	    result.deleteCharAt(result.length() - 1);
	}
	return result.toString();
    }

}
