import by.gsu.epamlab.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Locale;
import java.util.Scanner;

public class Runner {
    public static void main(String[] args) {    	
		try(Scanner file = new Scanner(new FileReader("src/in.txt"))) {
			final int PURCHASES_NUMBER = file.nextInt();
			Purchase []purchases = new Purchase[PURCHASES_NUMBER];
			for (int i = 0; i < purchases.length; i++){
				file.useLocale(Locale.ENGLISH);
			    int numberOfPurchaseUnits = file.nextInt();
			    int discountPercent = file.nextInt();
			    int day = file.nextInt();
			    WeekDay weekDay = WeekDay.values()[day];
			    purchases[i] = new Purchase(numberOfPurchaseUnits,
				    discountPercent,weekDay);
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
			System.out.println("Total cost on Monday " + sumOfPurchasesCostOnMonday / 100 + "."
					+ sumOfPurchasesCostOnMonday / 10 % 10 
					+ sumOfPurchasesCostOnMonday % 10);
			System.out.println("The day with the maximum cost purchase "
					+ maxCostOfPurchase.getWeekDay());
			Arrays.sort(purchases);
			for (Purchase purchase : purchases) {
				System.out.println(purchase);
			}
			System.out.println(Arrays.binarySearch(purchases,purchases[4]));
		} catch (FileNotFoundException e) {
		    System.err.println("Input file is not found");
		}
    }
}

