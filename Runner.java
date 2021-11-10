import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import by.gsu.epamlab.*;

public class Runner {
    private static enum PurchaseKind {
	GENERAL_PURCHASE, DISCOUNT_UNIT_PURCHASE, DISCOUNT_PURCHASE
    }

    public static void main(String[] args) {
	try (Scanner sc = new Scanner(new FileReader("src/Purchases.txt"))) {
	    Purchase[] purchases = new Purchase[6];
	    for (int i = 0; i< purchases.length; i++) {
		PurchaseKind kind = PurchaseKind.valueOf(sc.next());
		switch (kind) {
		case GENERAL_PURCHASE:
		    purchases[i] = new Purchase(sc.next(), sc.nextInt(), sc.nextInt());
		    break;
		case DISCOUNT_UNIT_PURCHASE:
		    purchases[i] = new DiscountUnitsPurchase(sc.next(), sc.nextInt(), sc.nextInt(), sc.nextDouble());
		    break;
		case DISCOUNT_PURCHASE:
		    purchases[i] = new DiscountUnitsPurchase(sc.next(), sc.nextInt(), sc.nextInt(), sc.nextDouble());
		    break;
		default:
		    throw new IllegalArgumentException();
		}
		System.out.println(purchases[i]);
		int maxCost = 0;
		Purchase maxCostPurchase = null;
		int cost = purchases[i].getCost();
		if (maxCost < cost) {
		    maxCostPurchase = purchases[i];
		    System.out.println(maxCostPurchase);
		}
	    }
	} catch (FileNotFoundException e) {
	    System.err.println("File not found");

	}
    }

}
