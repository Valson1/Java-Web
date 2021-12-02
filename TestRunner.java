import java.util.Arrays;

import org.junit.*;

import by.gsu.epamlab.*;

public class TestRunner {
    @Test
    public void testByn() {
	Byn byn1 = new Byn(230);
	Byn byn2 = new Byn(310);
	Assert.assertNotEquals(byn1, byn2);
	Assert.assertEquals(new Byn(80), byn2.sub(byn1));
	Assert.assertEquals(new Byn(540), byn2.sum(byn1));
	Assert.assertEquals(new Byn(71300), byn2.multiply(230));
	Assert.assertEquals("5.12", new Byn(512));
    }

    @Test
    public void testPurchaseEntities() {
	Product product = new Product("Milk", new Byn(140));
	AbstractPurchase[] purchases = { new DiscountPurchase(product, 12, new Byn(30)),
		new DiscountUnitPurchase(product, 3, 10), new DiscountPurchase(product, 5, new Byn(0)),
		new TransportExpencesPurchase(product, 7, new Byn(50)), new DiscountUnitPurchase(product, 10, 20),
		new TransportExpencesPurchase(product, 12, new Byn(45)) };
	Assert.assertTrue(purchases[0].equals(new DiscountPurchase(product, 12, new Byn(30))));
	Assert.assertTrue(purchases[1].equals(new DiscountUnitPurchase(product, 3, 10)));
	Assert.assertTrue(purchases[2].equals(new DiscountPurchase(product, 5, new Byn(0))));
	Assert.assertTrue(purchases[3].equals(new TransportExpencesPurchase(product, 7, new Byn(50))));
	Assert.assertTrue(purchases[4].equals(new DiscountUnitPurchase(product, 10, 20)));
	Assert.assertTrue(purchases[5].equals(new TransportExpencesPurchase(product, 12, new Byn(45))));
    }

    @Test
    public void testGetCost() {
	Product product = new Product("Coffee", new Byn(230));
	AbstractPurchase p1 = new DiscountPurchase(product, 3, new Byn(44));
	AbstractPurchase p2 = new DiscountUnitPurchase(product, 5, 100);
	AbstractPurchase p3 = new TransportExpencesPurchase(product, 4, new Byn(400));
	Assert.assertEquals(new Byn(500), p1.getCost());
	Assert.assertEquals(new Byn(1100), p2.getCost());
	Assert.assertEquals(new Byn(1300), p3.getCost());
    }

    @Test
    public void testSearch() {
	Product product1 = new Product("Milk", new Byn(140));
	AbstractPurchase[] purchases = { new DiscountUnitPurchase(product1, 23, 10),
		new DiscountPurchase(product1, 4, new Byn(0)), new DiscountPurchase(product1, 5, new Byn(0)),
		new TransportExpencesPurchase(product1, 7, new Byn(50)), new DiscountUnitPurchase(product1, 10, 20),
		new TransportExpencesPurchase(product1, 12, new Byn(45)) };
	Arrays.sort(purchases);
	Product product2 = new Product(null, new Byn(500));
	AbstractPurchase purchase = new DiscountPurchase(product2, 1, new Byn(0));
	for (int i = 0; i < purchases.length; i++) {
	    if (purchases[i].compareTo(purchase) == 0) {
		purchase = purchases[i];
	    }
	}
	Assert.assertEquals(5, Arrays.binarySearch(purchases, purchase));
    }
}
