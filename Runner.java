import java.util.Arrays;

import by.gsu.epamlab.*;

public class Runner {
    public static void main(String[] args) {
	final Product product = new Product("Milk", new Byn(100));
	AbstractPurchase[] purchases = { new DiscountPurchase(product, 12, new Byn(30)),
		new DiscountUnitPurchase(product, 3, 10), new DiscountPurchase(product, 5, new Byn(0)),
		new TransportExpencesPurchase(product, 7, new Byn(50)), new DiscountUnitPurchase(product, 10, 20),
		new TransportExpencesPurchase(product, 12, new Byn(45)) };
	showPurchasesInformation(product, purchases);
	Arrays.sort(purchases);
	showPurchasesInformation(product, purchases);
	System.out.println("Minimum cost of purchase:" + purchases[purchases.length - 1].getCost());

	int indexOfBinnarySearch = search(purchases);
	if (indexOfBinnarySearch >= 0) {
	    System.out.println("Purchase is found :" + purchases[indexOfBinnarySearch]);
	} else {
	    System.out.println("Purchase is not found");
	}
}

    private static int search(AbstractPurchase[] purchases) {
	Product product = new Product(null, new Byn(500));
	AbstractPurchase purchase = new DiscountPurchase(product, 1, new Byn(0));
	for (int i = 0; i < purchases.length; i++) {
	    if (purchases[i].compareTo(purchase) == 0) {
		purchase = purchases[i];
	    }
	}
	return Arrays.binarySearch(purchases, purchase);		
    }

    private static void showPurchasesInformation(Product product, AbstractPurchase[] purchases) {
	System.out.println(product);
	for (AbstractPurchase purchase : purchases) {
	    System.out.println(purchase);
	}
    }
}
