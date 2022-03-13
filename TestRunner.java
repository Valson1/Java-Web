import java.util.Comparator;

import org.junit.Assert;
import org.junit.Test;
import by.epam.lab.beans.*;
import by.epam.lab.exceptions.*;
import by.epam.lab.services.*;

public class TestRunner {

    private static final String CSV_FILE_NAME1 = "src/in.csv";
    private static final String CSV_FILE_NAME2 = "wefwedas";

    private static final String EXPECTED_STRING = "DiscountUnitsPurchase;bread;1.55;1;0.02;1.53;Purchase;milk;1.31;2;2.62;Purchase;bread;1.54;3;4.62;Purchase;bread;1.45;5;7.25;DiscountUnitsPurchase;potato;1.80;2;0.10;3.40;Purchase;butter;3.70;1;3.70;DiscountUnitsPurchase;butter;3.41;1;0.01;3.40;DiscountUnitsPurchase;meat;11.00;2;0.80;20.40";
    private static final String EXPECTED_STRING_DELETE_LEFT = "DiscountUnitsPurchase;bread;1.55;1;0.02;1.53;Purchase;bread;1.45;5;7.25;DiscountUnitsPurchase;potato;1.80;2;0.10;3.40;Purchase;butter;3.70;1;3.70;DiscountUnitsPurchase;butter;3.41;1;0.01;3.40;DiscountUnitsPurchase;meat;11.00;2;0.80;20.40";
    private static final String EXPECTED_STRING_DELETE_MIDDLE = "Purchase;bread;1.45;5;7.25;DiscountUnitsPurchase;bread;1.55;1;0.02;1.53;DiscountUnitsPurchase;potato;1.80;2;0.10;3.40;DiscountUnitsPurchase;butter;3.41;1;0.01;3.40";
    private static final String EXPECTED_STRING_MIDDLE_ADD = "DiscountUnitsPurchase;bread;1.55;1;0.02;1.53;Purchase;milk;1.31;2;2.62;Purchase;bread;1.54;3;4.62;Purchase;bread;1.45;5;7.25;DiscountUnitsPurchase;potato;1.80;2;0.10;3.40;Purchase;www;0.02;12;0.24;Purchase;butter;3.70;1;3.70;DiscountUnitsPurchase;butter;3.41;1;0.01;3.40;DiscountUnitsPurchase;meat;11.00;2;0.80;20.40";
    private static final String EXPECTED_STRING_LEFT_ADD = "Purchase;www;0.02;12;0.24;" + EXPECTED_STRING;
    private static final String EXPECTED_STRING_RIGHT_ADD = EXPECTED_STRING_LEFT_ADD + ";Purchase;www;0.02;12;0.24";
    private static final String EXPECTED_STRING_SORTED = "Purchase;milk;1.31;2;2.62;Purchase;bread;1.45;5;7.25;Purchase;bread;1.54;3;4.62;DiscountUnitsPurchase;bread;1.55;1;0.02;1.53;DiscountUnitsPurchase;potato;1.80;2;0.10;3.40;DiscountUnitsPurchase;butter;3.41;1;0.01;3.40;Purchase;butter;3.70;1;3.70;DiscountUnitsPurchase;meat;11.00;2;0.80;20.40";
    private static final String EXPECTED_STRING_DELETE = "DiscountUnitsPurchase;bread;1.55;1;0.02;1.53;Purchase;milk;1.31;2;2.62;Purchase;bread;1.54;3;4.62;Purchase;bread;1.45;5;7.25;DiscountUnitsPurchase;potato;1.80;2;0.10;3.40;Purchase;butter;3.70;1;3.70";

    private static final Comparator<Purchase> comparator = new Comparator<>() {
	@Override
	public int compare(Purchase o1, Purchase o2) {
	    return o1.getPrice().compareTo(o2.getPrice());
	}
    };

    @Test
    public void testConstructorPurchaseList() {
	PurchaseList purchases = new PurchaseList("we", comparator);
	purchases.add(0, new DiscountUnitsPurchase("bread", new Byn(155), 1, new Byn(2)));
	purchases.add(1, new Purchase("milk", new Byn(131), 2));
	purchases.add(2, new Purchase("bread", new Byn(154), 3));
	purchases.add(3, new Purchase("bread", new Byn(145), 5));
	purchases.add(4, new DiscountUnitsPurchase("potato", new Byn(180), 2, new Byn(10)));
	purchases.add(5, new Purchase("butter", new Byn(370), 1));
	purchases.add(6, new DiscountUnitsPurchase("butter", new Byn(341), 1, new Byn(1)));
	purchases.add(7, new DiscountUnitsPurchase("meat", new Byn(1100), 2, new Byn(80)));
	// constructor
	PurchaseList purchasesActual = new PurchaseList(CSV_FILE_NAME1, comparator);
	Assert.assertEquals(purchases.toString(), purchasesActual.toString());
	PurchaseList purchasesEmpty = new PurchaseList(CSV_FILE_NAME2, comparator);
	Assert.assertEquals(purchasesEmpty.toString(), "");
	Assert.assertEquals(EXPECTED_STRING, purchasesActual.toString());

	// add method index out of bounds
	Purchase purchase = new Purchase("www", new Byn(2), 12);
	purchasesActual.add(-10, purchase);
	Assert.assertEquals(purchasesActual.toString(), EXPECTED_STRING_LEFT_ADD);
	purchasesActual.add(20, purchase);
	Assert.assertEquals(purchasesActual.toString(), EXPECTED_STRING_RIGHT_ADD);

	// add method

	PurchaseList purchasesMiddleAdd = new PurchaseList(CSV_FILE_NAME1, comparator);
	purchasesMiddleAdd.add(5, purchase);
	Assert.assertEquals(purchasesMiddleAdd.toString(), EXPECTED_STRING_MIDDLE_ADD);

    }

    @Test
    public void sortAndSearchTest() {
	PurchaseList purchasesActual = new PurchaseList(CSV_FILE_NAME1, comparator);
	purchasesActual.sort();
	Assert.assertEquals(purchasesActual.toString(), EXPECTED_STRING_SORTED);
	int index = purchasesActual.binarySearch(new Purchase("bwef", new Byn(180), 3));
	Assert.assertEquals(4, index);
	index = purchasesActual.binarySearch(new Purchase("bwef", new Byn(155), 3));
	Assert.assertEquals(3, index);
    }

    @Test
    public void deleteOutOfBoundsTest() {

	PurchaseList purchasesActual = new PurchaseList(CSV_FILE_NAME1, comparator);
	purchasesActual.delete(-1, 0);
	Assert.assertEquals(purchasesActual.toString(), EXPECTED_STRING);
	purchasesActual.delete(12, 13);
	Assert.assertEquals(purchasesActual.toString(), EXPECTED_STRING);
	purchasesActual.delete(6, 13);
	Assert.assertEquals(purchasesActual.toString(), EXPECTED_STRING_DELETE);
	Assert.assertEquals(-6,
		purchasesActual.binarySearch(new DiscountUnitsPurchase("butter", new Byn(341), 1, new Byn(1))));
	Assert.assertEquals(-7,
		purchasesActual.binarySearch(new DiscountUnitsPurchase("meat", new Byn(1100), 2, new Byn(80))));
    }

    @Test
    public void deleteTest() {

	PurchaseList purchasesActual = new PurchaseList(CSV_FILE_NAME1, comparator);
	purchasesActual.delete(1, 3);
	Assert.assertEquals(purchasesActual.toString(), EXPECTED_STRING_DELETE_LEFT);
	Assert.assertEquals(-1, purchasesActual.binarySearch(new Purchase("milk", new Byn(131), 2)));
	Assert.assertEquals(-2, purchasesActual.binarySearch(new Purchase("bread", new Byn(154), 3)));
	purchasesActual.delete(4, 7);
	Assert.assertEquals(purchasesActual.toString(), EXPECTED_STRING_DELETE_MIDDLE);
    }

    @Test
    public void getCostTest() {
	PurchaseList purchasesActual = new PurchaseList(CSV_FILE_NAME1, comparator);
	Assert.assertEquals(new Byn(4692), purchasesActual.getCost());
    }

    @Test
    public void deleteIndexesMessedUpTest() {
	PurchaseList purchasesActual = new PurchaseList(CSV_FILE_NAME1, comparator);
	purchasesActual.delete(11, 2);
	Assert.assertEquals(purchasesActual.toString(), EXPECTED_STRING);
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
