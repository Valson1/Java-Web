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
	Assert.assertEquals(new Byn(310),byn2);
	Assert.assertEquals(new Byn(540), byn2.sum(byn1));
	Assert.assertEquals(new Byn(310),byn2);
	Assert.assertEquals(new Byn(71300), byn2.multiply(230));
	Assert.assertEquals(new Byn(310),byn2);
	Assert.assertEquals("5.12", new Byn(512).toString());
    }

    @Test
    public void testPurchaseEntities() {
	Product product = new Product("Milk", new Byn(140));
	AbstractPurchase[] purchases = { new NumberDiscountPurchase(product, 12, new Byn(30)),
		new PercentageDiscountPurchase(product, 3, 10), new NumberDiscountPurchase(product, 5, new Byn(0)),
		new TransportExpencesPurchase(product, 7, new Byn(50)), new PercentageDiscountPurchase(product, 10, 20),
		new TransportExpencesPurchase(product, 12, new Byn(45)) };
	Assert.assertTrue(purchases[0].equals(new NumberDiscountPurchase(product, 12, new Byn(30))));
	Assert.assertTrue(purchases[1].equals(new PercentageDiscountPurchase(product, 3, 10)));
	Assert.assertTrue(purchases[2].equals(new NumberDiscountPurchase(product, 5, new Byn(0))));
	Assert.assertTrue(purchases[3].equals(new TransportExpencesPurchase(product, 7, new Byn(50))));
	Assert.assertTrue(purchases[4].equals(new PercentageDiscountPurchase(product, 10, 20)));
	Assert.assertTrue(purchases[5].equals(new TransportExpencesPurchase(product, 12, new Byn(45))));
	AbstractPurchase[] purchasesVerifiable = { 
		new TransportExpencesPurchase(product, 12, new Byn(45)),
		new PercentageDiscountPurchase(product, 23, 10),
		new NumberDiscountPurchase(product, 23, new Byn(100))
		 };
	Arrays.sort(purchasesVerifiable);
	AbstractPurchase[] purchasesChecking = { 
		new PercentageDiscountPurchase(product, 23, 10),
		new TransportExpencesPurchase(product, 12, new Byn(45)),
		new NumberDiscountPurchase(product, 23, new Byn(90))
		};
	Assert.assertArrayEquals(purchasesVerifiable, purchasesChecking);
    }

    @Test
    public void testGetCost() {
	Product product = new Product("Coffee", new Byn(230));
	AbstractPurchase p1 = new NumberDiscountPurchase(product, 3, new Byn(44));
	AbstractPurchase p2 = new PercentageDiscountPurchase(product, 5, 100);
	AbstractPurchase p3 = new TransportExpencesPurchase(product, 4, new Byn(400));
	Assert.assertEquals(new Byn(500), p1.getCost());
	Assert.assertEquals(new Byn(0), p2.getCost());
	Assert.assertEquals(new Byn(1300), p3.getCost());
    }

    @Test
    public void testSearch() {
	Product product = new Product("Milk", new Byn(100));
	AbstractPurchase[] purchasesVerifiable = { 
		new TransportExpencesPurchase(product, 12, new Byn(45)),
		new PercentageDiscountPurchase(product, 23, 10),
		new NumberDiscountPurchase(product, 23, new Byn(100))
		 };
	Arrays.sort(purchasesVerifiable);
	AbstractPurchase purchase = new NumberDiscountPurchase(product,12, new Byn(0));
	Assert.assertEquals(1, Arrays.binarySearch(purchasesVerifiable, purchase));
	Assert.assertNotEquals(2, Arrays.binarySearch(purchasesVerifiable, purchase));
    }
}