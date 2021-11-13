import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Scanner;
import by.gsu.epamlab.*;

public class Runner {

    public static void main(String[] args) {
	try (Scanner sc = new Scanner(new FileReader("src/in.txt"))) {
	    // Create an array for 6 objects.
	    Purchase[] purchases = new Purchase[6];
	    boolean isEquals = true;
	    Purchase purchaseWithMaxCost = new Purchase();
	    Byn maxCostOfPurchase = new Byn(0);
	    // Input data from the given file into the array.
	    for (int i = 0; i < purchases.length; i++) {
		// Pattern Factory for create objects
		purchases[i] = FactoryClass.getPurchaseFromFactory(sc);
		// Output content of purchases
		System.out.println(purchases[i]);
		// Found the purchase with maximum cost
		Byn purchaseCost = new Byn(purchases[i].getCost());
		int cost = purchaseCost.compareTo(maxCostOfPurchase);
		if (cost > 0) {
		    maxCostOfPurchase = purchaseCost;
		    purchaseWithMaxCost = purchases[i];
		}
		// Determine whether all purchases are equal.
		for (int j = 0; j < purchases.length; j++) {
		    if (!(purchases[i].equals(purchases[j]))) {
			isEquals = false;
		    }
		}
	    }
	    // Output the purchase with maximum cost
	    System.out.println("The maximum purchase is \n" + purchaseWithMaxCost);
	    System.out.println("Equivalence condition is " + isEquals);
	} catch (FileNotFoundException e) {
	    System.err.println("File not found");

	}
    }

}
