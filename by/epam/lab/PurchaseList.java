package by.epam.lab;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class PurchaseList {
    private List<Purchase> purchases = new ArrayList<>();

    public PurchaseList(String csvFileName) {
	try (Scanner sc = new Scanner(new FileReader(csvFileName))) {
	    while (sc.hasNextLine()) {
		String line = sc.nextLine();
		try {
		    purchases.add(FactoryClass.getPurchaseFromFactory(line));
		} catch (IllegalArgumentException e) {
		    System.err.println(line);
		}
	    }
	} catch (FileNotFoundException e) {
	    purchases = Collections.emptyList();
	}
    }

    public List<Purchase> getPurchases() {
	return purchases;
    }
    

    public void setPurchases(List<Purchase> purchases) {
        this.purchases = purchases;
    }

    public void add(int index, Purchase purchase) {
	int listSize = purchases.size();
	purchases.add(checkRangeIndex(index, listSize), purchase);
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
	    throw new IllegalArgumentException();
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

    public void sort(PurchaseComparatorKind comparatorKind) {
	Collections.sort(purchases, comparatorKind);
    }

    public int binarySearch(PurchaseComparatorKind comparatorKind, Purchase key) {
	sort(comparatorKind);
	return Collections.binarySearch(purchases, key, comparatorKind);
    }

    @Override
    public String toString() {
	StringBuilder result = new StringBuilder();
	for (int i = 0; i < purchases.size(); i++) {
	    result.append(purchases.get(i)).append(ConstantsUtility.SEPARATOR);
	}
	if(!purchases.isEmpty()) {
	    result.deleteCharAt(result.length() - 1);
	}
	return result.toString();
    }

}
