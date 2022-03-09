import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import by.epam.lab.*;

public class TestRunner {

    private static String CSV_FILE_NAME1 = "src/in.csv";
    private static String CSV_FILE_NAME2 = "wefwedas";

    private static String EXPECTED_STRING = "bread;1.55;1;0.2;milk;1.31;2;bread;1.54;3;bread;1.45;5;potato;1.80;2;0.10;butter;3.70;1;butter;3.41;1;0.1;meat;11.0;2;0.80";

    @Test
    public void testConstructorPurchaseList() {
	List<Purchase> purchases = new ArrayList<>();
	purchases.add(new DiscountUnitsPurchase("bread", new Byn(155), 1, new Byn(2)));
	purchases.add(new Purchase("milk", new Byn(131), 2));
	purchases.add(new Purchase("bread", new Byn(154), 3));
	purchases.add(new Purchase("bread", new Byn(145), 5));
	purchases.add(new DiscountUnitsPurchase("potato", new Byn(180), 2, new Byn(10)));
	purchases.add(new Purchase("butter", new Byn(370), 1));
	purchases.add(new DiscountUnitsPurchase("butter", new Byn(341), 1, new Byn(1)));
	purchases.add(new DiscountUnitsPurchase("meat", new Byn(1100), 2, new Byn(80)));
	// constructor
	PurchaseList purchasesActual = new PurchaseList(CSV_FILE_NAME1);
	PurchaseList purchasesExpected = purchasesActual;
	purchasesActual.delete(0, 10);
	purchasesActual.setPurchases(purchases);
	Assert.assertEquals(purchasesExpected, purchasesActual);
	PurchaseList purchasesEmpty = new PurchaseList(CSV_FILE_NAME2);
	Assert.assertEquals(purchasesEmpty.toString(), "");
	Assert.assertEquals(EXPECTED_STRING, purchasesActual.toString());

	// add method index out of bounds
	List<Purchase> getPurchases = purchasesActual.getPurchases();
	Purchase purchase = new Purchase("www", new Byn(2), 12);
	purchasesActual.add(-10, purchase);
	Assert.assertEquals(purchase, getPurchases.get(0));
	purchasesActual.add(20, purchase);
	Assert.assertEquals(purchase, getPurchases.get(getPurchases.size() - 1));

	// add method
	purchasesActual.add(5, purchase);
	Assert.assertEquals(purchase, getPurchases.get(5));

    }

    @Test
    public void sortAndSearchTest() {
	PurchaseList purchasesActual = new PurchaseList(CSV_FILE_NAME1);
	purchasesActual.sort(PurchaseComparatorKind.NUMBER_OF_PURCHASE_UNITS);
	Assert.assertEquals("bread;1.55;1;0.2", purchasesActual.getPurchases().get(0).toString());
	int index = purchasesActual.binarySearch(PurchaseComparatorKind.NUMBER_OF_PURCHASE_UNITS,
		new Purchase("bwef", new Byn(23), 3));
	Assert.assertEquals(6, index);
    }

    @Test
    public void deleteOutOfBoundsTest() {
	PurchaseList purchasesActual = new PurchaseList(CSV_FILE_NAME1);
	purchasesActual.delete(-1, 0);
	Assert.assertEquals(8, purchasesActual.getPurchases().size());
	purchasesActual.delete(12, 13);
	Assert.assertEquals(8, purchasesActual.getPurchases().size());
	purchasesActual.delete(6, 13);
	Assert.assertEquals(6, purchasesActual.getPurchases().size());
	Assert.assertEquals(-6, purchasesActual.binarySearch(PurchaseComparatorKind.PRICE,
		new DiscountUnitsPurchase("butter", new Byn(341), 1, new Byn(1))));
	Assert.assertEquals(-7, purchasesActual.binarySearch(PurchaseComparatorKind.PRICE,
		new DiscountUnitsPurchase("meat", new Byn(1100), 2, new Byn(80))));
    }

    @Test
    public void deleteTest() {
	PurchaseList purchasesActual = new PurchaseList(CSV_FILE_NAME1);
	purchasesActual.delete(1, 3);
	Assert.assertEquals(6, purchasesActual.getPurchases().size());
	Assert.assertEquals(-1,
		purchasesActual.binarySearch(PurchaseComparatorKind.PRICE, new Purchase("milk", new Byn(131), 2)));
	Assert.assertEquals(-2,
		purchasesActual.binarySearch(PurchaseComparatorKind.PRICE, new Purchase("bread", new Byn(154), 3)));
	purchasesActual.delete(4, 7);
	Assert.assertEquals(4, purchasesActual.getPurchases().size());
    }

    @Test
    public void getCostTest() {
	PurchaseList purchasesActual = new PurchaseList(CSV_FILE_NAME1);
	System.out.println(purchasesActual);
	Assert.assertEquals(new Byn(4692), purchasesActual.getCost());
    }

    @Test(expected = IllegalArgumentException.class)
    public void deleteExceptionTest() {
	PurchaseList purchasesActual = new PurchaseList(CSV_FILE_NAME1);
	purchasesActual.delete(11, 2);
    }

    @Test(expected = NumberFormatException.class)
    public void purchaseNFETest() {
	FactoryClass.getPurchaseFromFactory("milk;70.5;2");
    }

    @Test(expected = NumberFormatException.class)
    public void discountPurchaseNFETest() {
	FactoryClass.getPurchaseFromFactory("water;1;wef;10");
    }

    @Test
    public void testFactoryClass() {
	Purchase purchase = FactoryClass.getPurchaseFromFactory("milk;155;1");
	Purchase discountPurchase = FactoryClass.getPurchaseFromFactory("milk;155;1;20");
	Assert.assertEquals(purchase, new Purchase("milk", new Byn(155), 1));
	Assert.assertEquals(discountPurchase, new DiscountUnitsPurchase("milk", new Byn(155), 1, new Byn(20)));
    }
}
