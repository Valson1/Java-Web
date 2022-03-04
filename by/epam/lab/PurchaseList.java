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
	    final String SEPARATOR = ";";
	    final int PURCHASE_NUMBER_FIELDS = 3;
	    final int DISCOUNT_PURCHASE_NUMBER_FIELDS = 4;
	    while (sc.hasNextLine()) {
		String str = sc.nextLine();
		int length = str.split(SEPARATOR).length;
		try {
		    if(length < PURCHASE_NUMBER_FIELDS || length > DISCOUNT_PURCHASE_NUMBER_FIELDS) {
			throw new IllegalArgumentException();
		    }
		    purchases.add(FactoryClass.getPurchaseFromFactory(length,sc));
		} catch (IllegalArgumentException | InputMismatchException e) {
		    System.err.println(str);
		}
	    }
	} catch (FileNotFoundException e) {
	    purchases = Collections.emptyList();
	}
    }

    public void get() {
	for (Purchase purchase : purchases) {
	    System.out.println(purchase);
	}
    }
}
