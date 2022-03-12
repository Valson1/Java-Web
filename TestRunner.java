import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import beans.Byn;
import beans.DiscountUnitsPurchase;
import beans.Purchase;
import by.epam.lab.*;
import exceptions.CsvLineException;
import services.FactoryClass;

public class TestRunner {

    private static String CSV_FILE_NAME1 = "src/in.csv";
    private static String CSV_FILE_NAME2 = "wefwedas";

    private static String EXPECTED_STRING = "bread;1.55;1;0.02;1.53;milk;1.31;2;2.62;bread;1.54;3;4.62;bread;1.45;5;7.25;potato;1.80;2;0.10;3.40;butter;3.70;1;3.70;butter;3.41;1;0.01;3.40;meat;11.00;2;0.80;20.40";

    private static Comparator<Purchase> comparator = new Comparator<>() {
	@Override
	public int compare(Purchase o1, Purchase o2) {
	    return o1.getPrice().compareTo(o2.getPrice());
	}
    };

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
	PurchaseList purchasesActual = new PurchaseList(CSV_FILE_NAME1, comparator);
	PurchaseList purchasesExpected = purchasesActual;
	purchasesActual.delete(0, 10);
	purchasesActual.setPurchases(purchases);
	Assert.assertEquals(purchasesExpected, purchasesActual);
	PurchaseList purchasesEmpty = new PurchaseList(CSV_FILE_NAME2, comparator);
	System.out.println(purchasesActual);
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
	PurchaseList purchasesActual = new PurchaseList(CSV_FILE_NAME1, comparator);
	purchasesActual.sort();
	Assert.assertEquals("bread;1.55;1;0.02;1.53", purchasesActual.getPurchases().get(3).toString());
	int index = purchasesActual.binarySearch(new Purchase("bwef", new Byn(180), 3));
	Assert.assertEquals(4, index);
	index = purchasesActual.binarySearch(new Purchase("bwef", new Byn(155), 3));
	Assert.assertEquals(3, index);
    }

    @Test
    public void deleteOutOfBoundsTest() {

	PurchaseList purchasesActual = new PurchaseList(CSV_FILE_NAME1, comparator);
	purchasesActual.delete(-1, 0);
	Assert.assertEquals(8, purchasesActual.getPurchases().size());
	purchasesActual.delete(12, 13);
	Assert.assertEquals(8, purchasesActual.getPurchases().size());
	purchasesActual.delete(6, 13);
	Assert.assertEquals(6, purchasesActual.getPurchases().size());
	Assert.assertEquals(-6,
		purchasesActual.binarySearch(new DiscountUnitsPurchase("butter", new Byn(341), 1, new Byn(1))));
	Assert.assertEquals(-7,
		purchasesActual.binarySearch(new DiscountUnitsPurchase("meat", new Byn(1100), 2, new Byn(80))));
    }

    @Test
    public void deleteTest() {

	PurchaseList purchasesActual = new PurchaseList(CSV_FILE_NAME1, comparator);
	purchasesActual.delete(1, 3);
	Assert.assertEquals(6, purchasesActual.getPurchases().size());
	Assert.assertEquals(-1, purchasesActual.binarySearch(new Purchase("milk", new Byn(131), 2)));
	Assert.assertEquals(-2, purchasesActual.binarySearch(new Purchase("bread", new Byn(154), 3)));
	purchasesActual.delete(4, 7);
	Assert.assertEquals(4, purchasesActual.getPurchases().size());
    }

    @Test
    public void getCostTest() {
	PurchaseList purchasesActual = new PurchaseList(CSV_FILE_NAME1, comparator);
	Assert.assertEquals(new Byn(4692), purchasesActual.getCost());
    }

    @Test(expected = IllegalArgumentException.class)
    public void deleteExceptionTest() {
	PurchaseList purchasesActual = new PurchaseList(CSV_FILE_NAME1, comparator);
	purchasesActual.delete(11, 2);
    }

    @Test
    public void testFactoryClass() {
	Purchase purchase = new Purchase();
	Purchase discountPurchase = new DiscountUnitsPurchase();
	try {
	    purchase = FactoryClass.getPurchaseFromFactory("milk;155;1");
	    discountPurchase = FactoryClass.getPurchaseFromFactory("milk;155;1;20");
	} catch (CsvLineException e) {
	    System.err.println(e.getMessage());
	}
	Assert.assertEquals(purchase, new Purchase("milk", new Byn(155), 1));
	Assert.assertEquals(discountPurchase, new DiscountUnitsPurchase("milk", new Byn(155), 1, new Byn(20)));
    }

    @Test
    public void testFactoryClassWrongPurchase() {
	boolean checkCsvLineException = false;
	try {
	    FactoryClass.getPurchaseFromFactory("milk;155;1;20;12;3");
	} catch (CsvLineException e) {
	    System.err.println(e.getMessage());
	    checkCsvLineException = true;
	}
	Assert.assertEquals(true, checkCsvLineException);
    }
}
