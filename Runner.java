import java.util.Arrays;

import by.gsu.epamlab.*;

public class Runner {
    public static void main(String[] args) {
	Product product = new Product("Milk", new Byn(100));
	AbstractPurchase[] purchases = { new NumberDiscountPurchase(product, 12, new Byn(30)),
		new PercentageDiscountPurchase(product, 3, 10), new NumberDiscountPurchase(product, 5, new Byn(10)),
		new TransportExpencesPurchase(product, 7, new Byn(50)), new PercentageDiscountPurchase(product, 10, 20),
		new TransportExpencesPurchase(product, 12, new Byn(45)) };
	showPurchasesInformation(purchases);
	Arrays.sort(purchases);
	showPurchasesInformation(purchases);
	System.out.println("Minimum cost of purchase:" + purchases[purchases.length - 1].getCost());
	int index = search(purchases, new NumberDiscountPurchase(product, 5, new Byn(0)));
	if (index >= 0) {
	    System.out.println("Purchase is found :" + purchases[index]);
	} else {
	    System.out.println("Purchase is not found");
	}
    }
    
    private static int search(AbstractPurchase[] purchases, AbstractPurchase purchase) {
	return Arrays.binarySearch(purchases, purchase);
    }

    private static void showPurchasesInformation(AbstractPurchase[] purchases) {
	for (AbstractPurchase purchase : purchases) {
	    System.out.println(purchase);
	}
    }
}
