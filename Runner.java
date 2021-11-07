
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Locale;
import java.util.Scanner;

import by.gsu.epamlab.*;
public class Runner {
	private static void showConst() {
		System.out.println(Purchase.NAME + ";" + FinancialAction.toRubles(Purchase.PRICE));
	}
	private static void showPurchases(Purchase []purchases) {
		for (Purchase purchase : purchases) {
			System.out.println(purchase);
		}
	}
    public static void main(String[] args) {    	
		try(Scanner file = new Scanner(new FileReader("src/in.txt"))) {
			final int PURCHASES_NUMBER = file.nextInt();
			Purchase []purchases = new Purchase[PURCHASES_NUMBER];
			showConst();
			for (int i = 0; i < purchases.length; i++){
				file.useLocale(Locale.ENGLISH);
			    purchases[i] = new Purchase(file.nextInt(),
			    		file.nextInt(), file.nextInt());
		    }
			showPurchases(purchases);
			Purchase maxCostPurchase = purchases[0];
			WeekDay maxCostDay = null;
			int sumOfPurchasesCost = 0;
			int sumOfPurchasesCostOnMonday = 0;
			double meanCost = 0.0;
			for (Purchase purchase : purchases) {
				int cost = purchase.getCost();
				int maxCost = maxCostPurchase.getCost();
				sumOfPurchasesCost += cost;
				if(purchase.getWeekDay() == WeekDay.MONDAY) {
					sumOfPurchasesCostOnMonday += cost;
				}
				if(maxCost < cost) {
					maxCostDay = purchase.getWeekDay();
				}
			}
			if(purchases.length > 0) {
				meanCost = ((double) sumOfPurchasesCost) / purchases.length;
			}

			System.out.println(String.format("Mean cost %.3f",meanCost).replace(',', '.'));
			System.out.println("Total cost on Monday " 
					+ FinancialAction.toRubles(sumOfPurchasesCostOnMonday));
			System.out.println("The day with the maximum cost purchase "
					+ maxCostDay);
			Arrays.sort(purchases);
			showConst();
			showPurchases(purchases);
			int valueOfSearch = Arrays.binarySearch(purchases,new Purchase(5,0,0));
			if(valueOfSearch >= 0) {
				System.out.println("Requried purchase is " 
						+ purchases[Arrays.binarySearch(purchases,new Purchase(5,0,0))]);
			}
			else {
				System.out.println("Requried purchase is not found");				
			}
		} catch (FileNotFoundException e) {
		    System.err.println("Input file is not found");
		}
    }
}

