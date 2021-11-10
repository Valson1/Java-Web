import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import by.gsu.epamlab.*;

public class Runner {

    public static void main(String[] args) {
	try (Scanner sc = new Scanner(new FileReader("src/Purchases.txt"))) {
	    Purchase[] purchases = new Purchase[6];
	    Purchase maxCostPurchase = null;
	    boolean isEquals = true;
	    int maxCost = 0;
	    for (int i = 0; i < purchases.length; i++) {
		purchases[i] = FactoryClass.getPurchaseFromFactory(purchases[i], sc);
		System.out.println(purchases[i]);
		int cost = purchases[i].getCost();
		if (maxCost < cost) {
		    maxCost = cost;
		    maxCostPurchase = purchases[i];
		}
		for (int j = 0; j < purchases.length; j++) {
		    if (!(purchases[i].equals(purchases[j]))) {
			isEquals = false;
		    }
		}
	    }
	    System.out.println("The maximum purchase is \n" + maxCostPurchase);
	    System.out.println("Equivalence condition is " + isEquals);
	} catch (FileNotFoundException e) {
	    System.err.println("File not found");

	}
    }

}
