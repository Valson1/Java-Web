import by.gsu.epamlab.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Locale;
import java.util.Scanner;

public class Runner {
	private static String toRubles(int value) {
		return value / 100 + "." + value / 10 % 10 + value % 10;
	}
	private static void showConst() {
		System.out.println(Purchase.NAME + ";" + toRubles(Purchase.PRICE));
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
			    System.out.println(purchases[i]);
		    }
			Purchase maxCostOfPurchase = purchases[0];
			int sumOfPurchasesCost = 0;
			int sumOfPurchasesCostOnMonday = 0;
			for (Purchase purchase : purchases) {
				sumOfPurchasesCost += purchase.getCost();
				if(purchase.getWeekDay() == WeekDay.MONDAY) {
					sumOfPurchasesCostOnMonday += purchase.getCost();
				}
				if(maxCostOfPurchase.getCost() < purchase.getCost()) {
					maxCostOfPurchase = purchase;
				}
			}
			System.out.println("Mean cost " + sumOfPurchasesCost / 1000 + "." 
					+ sumOfPurchasesCost % 1000);
			System.out.println("Total cost on Monday " + sumOfPurchasesCostOnMonday);
			System.out.println("The day with the maximum cost purchase "
					+ maxCostOfPurchase.getWeekDay());
			Arrays.sort(purchases);
			showConst();
			for (Purchase purchase : purchases) {
				System.out.println(purchase);
			}
			System.out.println(purchases[Arrays.binarySearch(purchases, purchases[4])]);
		} catch (FileNotFoundException e) {
		    System.err.println("Input file is not found");
		}
    }
}

