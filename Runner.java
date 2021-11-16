import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Scanner;
import by.gsu.epamlab.*;

public class Runner {

    public static void main(String[] args) {
	try (Scanner sc = new Scanner(new FileReader("src/in.txt"))) {
	    // Create an array for 6 objects.
	    final int PURCHASES_NUMBER = 6;
	    Purchase[] purchases = new Purchase[PURCHASES_NUMBER];
	    Purchase purchaseWithMaxCost = new Purchase();
	    boolean areEquals = true;
	    // Input data from the given file into the array.
	    for (int i = 0; i < purchases.length; i++) {
		// Pattern Factory for create objects
		purchases[i] = FactoryClass.getPurchaseFromFactory(sc);
		// Output content of purchases
		System.out.println(purchases[i]);
		// Found the purchase with maximum cost
		if (purchases[i].getCost().compareTo(purchaseWithMaxCost.getCost()) > 0) {
		    purchaseWithMaxCost = purchases[i];
		}
		if (areEquals) {
		    areEquals = purchases[i].equals(purchases[0]);
		}
	    }
	    // Output the purchase with maximum cost
	    System.out.println("The maximum purchase is \n" + purchaseWithMaxCost);
	    System.out.println("Equivalence condition is " + areEquals);

	} catch (FileNotFoundException e) {
	    System.err.println("File not found");

	}
    }

}
