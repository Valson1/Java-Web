
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;

import by.gsu.epamlab.*;

public class Runner {
    private static void showPurchases(Purchase[] purchases) {
	System.out.println(Purchase.NAME + ";" + UtilityClass.toRubles(Purchase.PRICE));
	for (Purchase purchase : purchases) {
	    System.out.println(purchase);
	}
    }

    private static void purchaseCostInformation(Purchase[] purchases) {
	WeekDay maxCostDay = null;
	int sumOfPurchasesCostOnMonday = 0;
	double meanCost = 0;
	int maxCost = purchases[0].getCost();
	for (Purchase purchase : purchases) {
	    int cost = purchase.getCost();
	    meanCost += cost;
	    if (purchase.getWeekDay() == WeekDay.MONDAY) {
		sumOfPurchasesCostOnMonday += cost;
	    }
	    if (maxCost < cost) {
		maxCostDay = purchase.getWeekDay();
	    }
	}
	if (purchases.length > 0) {
	    meanCost /= purchases.length;
	}
	System.out.printf(Locale.ENGLISH, "The mean cost of all purchases: %.3f\n", meanCost / 100);
	System.out.println("Total cost on Monday " + UtilityClass.toRubles(sumOfPurchasesCostOnMonday));
	System.out.println("The day with the maximum cost purchase " + maxCostDay);
    }

    public static void main(String[] args) {

	// 1. Create an array for PURCHASES_NUMBER purchases.
	try (Scanner sc = new Scanner(new FileReader("src/in.txt"))) {
	    sc.useLocale(Locale.ENGLISH);
	    final int PURCHASES_NUMBER = sc.nextInt();
	    Purchase[] purchases = new Purchase[PURCHASES_NUMBER];

	    // 2. Initialize this array by the file data.
	    for (int i = 0; i < purchases.length; i++) {
		purchases[i] = new Purchase(sc.nextInt(), sc.nextDouble(), sc.nextInt());
	    }

	    // 3. Output constants and other content of purchases.
	    showPurchases(purchases);

	    // 4. Calculate the mean cost of all purchases
	    // the total cost of all purchases on Monday,
	    // the day with the maximum purchase cost and output them to the console
	    purchaseCostInformation(purchases);

	    // 5. Sort the array
	    Arrays.sort(purchases);

	    // 6. Output sorted array.
	    showPurchases(purchases);

	    // 7. Find Purchase with number of units by binarySearch();
	    int valueOfSearch = Arrays.binarySearch(purchases, new Purchase(5, 0, 0));
	    if (valueOfSearch >= 0) {
		System.out.println(
			"Required purchase is " + purchases[valueOfSearch]);
	    } else {
		System.out.println("Requried purchase is not found");
	    }
	} catch (FileNotFoundException e) {
	    System.err.println("Input file is not found");
	}
    }
}
