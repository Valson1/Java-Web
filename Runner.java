import by.gsu.epamlab.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;

public class Runner {
    public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		final int purchasesNumber = sc.nextInt();
		Purchase []purchases = new Purchase[purchasesNumber];
		try(Scanner file = new Scanner(new FileReader("src/in.txt"))) {
			for (int i = 0;i < purchases.length; i++) {
			    file.useLocale(Locale.ENGLISH);
			    int numberOfPurchase = file.nextInt();
			    String name = file.next();
			    int price = file.nextInt();
			    int numberOfPurchaseUnits = file.nextInt();
			    int discountPercent = file.nextInt();
			    int day = file.nextInt();
			    WeekDay weekDay = WeekDay.valueToDay(day);
			    purchases[i] = new Purchase(name,price,numberOfPurchaseUnits,
				    discountPercent,weekDay);
			    System.out.println(purchases[i]);
		    }
		} catch (FileNotFoundException e) {
		    System.err.println("Input file is not found");
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
		Arrays.sort(purchases);
		for (Purchase purchase : purchases) {
		    System.out.println(purchase);
		}
		PurchaseAction.showCostInformation(sumOfPurchasesCost,
			sumOfPurchasesCostOnMonday, maxCostOfPurchase);
		PurchaseAction.findPurchase(purchases);
    }
}

