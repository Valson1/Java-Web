import java.util.Arrays;

import by.gsu.epamlab.*;

public class Runner {
    public static void main(String[] args) {
	Product product = new Product();
	AbstractPurchase [] purchases = {
		new DiscountPurchase(12,new Byn(30)),
		new DiscountUnitPurchase(3,10),
		new DiscountPurchase(5,new Byn(20)),
		new TransportExpencesPurchase(7, new Byn(50)),
		new DiscountUnitPurchase(10, 20),
		new TransportExpencesPurchase(12, new Byn(45)) };
	showPurchasesInformation(product, purchases);
	Arrays.sort(purchases);
	showPurchasesInformation(product, purchases);
	System.out.println("Minimum cost of purchase:" + purchases[purchases.length - 1].getCost());
	AbstractPurchase purchase = null;
	for (int i = 0; i < purchases.length; i++) {
	    if(purchases[i].getCost().compareTo(new Byn(5)) == 0) {
		purchase = purchases[i];
	    }
	}
	Arrays.binarySearch(purchases,purchase);
	}

    private static void showPurchasesInformation(Product product, AbstractPurchase[] purchases) {
	System.out.println(product);
	for (AbstractPurchase purchase : purchases) {
	    System.out.println(purchase);
	}
    }
}
