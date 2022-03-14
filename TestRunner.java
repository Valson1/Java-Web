import java.util.Comparator;

import org.junit.Assert;
import org.junit.Test;
import by.epam.lab.beans.*;
import by.epam.lab.exceptions.*;
import by.epam.lab.services.*;

import static by.epam.lab.utils.TestConstantsUtility.*;

public class TestRunner {

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
    }

    public void addIndexOutOfBoundsTest() {
	PurchaseList purchasesActual = new PurchaseList(CSV_FILE_NAME1, comparator);
	Purchase purchase = new Purchase("www", new Byn(2), 12);
	purchasesActual.add(-10, purchase);
	Assert.assertEquals(purchasesActual.toString(), EXPECTED_STRING_LEFT_ADD);
	purchasesActual.add(20, purchase);
	Assert.assertEquals(purchasesActual.toString(), EXPECTED_STRING_RIGHT_ADD);
    }

    public void addTest() {
	Purchase purchase = new Purchase("www", new Byn(2), 12);
	PurchaseList purchasesMiddleAdd = new PurchaseList(CSV_FILE_NAME1, comparator);
	purchasesMiddleAdd.add(5, purchase);
	Assert.assertEquals(purchasesMiddleAdd.toString(), EXPECTED_STRING_MIDDLE_ADD);
    }

    @Test
    public void sortAndSearchTest() {
	PurchaseList purchasesActual = new PurchaseList(CSV_FILE_NAME1, comparator);
	Assert.assertEquals(false,purchasesActual.isSorted());
	purchasesActual.sort();
	Assert.assertEquals(true,purchasesActual.isSorted());
	Assert.assertEquals(purchasesActual.toString(), EXPECTED_STRING_SORTED);
	int index = purchasesActual.binarySearch(new Purchase("bwef", new Byn(180), 3));
	Assert.assertEquals(4, index);
	index = purchasesActual.binarySearch(new Purchase("bwef", new Byn(155), 3));
	Assert.assertEquals(3, index);
	index = purchasesActual.binarySearch(new Purchase("bwef", new Byn(345), 3));
	Assert.assertEquals(-7, index);
    }

    @Test
    public void deleteOutOfBoundsTest() {
	PurchaseList purchasesActual = new PurchaseList(CSV_FILE_NAME1, comparator);
	int index = purchasesActual.delete(-1, 0);
	Assert.assertEquals(purchasesActual.toString(), EXPECTED_STRING);
	Assert.assertEquals(index, 0);
	index = purchasesActual.delete(12, 13);
	Assert.assertEquals(purchasesActual.toString(), EXPECTED_STRING);
	Assert.assertEquals(index, 0);
	index = purchasesActual.delete(6, 13);
	Assert.assertEquals(purchasesActual.toString(), EXPECTED_STRING_DELETE);
	Assert.assertEquals(index, 2);
    }

    @Test
    public void deleteTest() {
	PurchaseList purchasesActual = new PurchaseList(CSV_FILE_NAME1, comparator);
	int index = purchasesActual.delete(1, 3);
	Assert.assertEquals(index, 2);
	Assert.assertEquals(purchasesActual.toString(), EXPECTED_STRING_DELETE_LEFT);
	index = purchasesActual.delete(4, 6);
	Assert.assertEquals(index, 2);
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
    public void testFactoryClass() throws CsvLineException {
	Purchase purchase = FactoryClass.getPurchaseFromFactory("milk;155;1");
	Purchase discountPurchase = FactoryClass.getPurchaseFromFactory("milk;155;1;20");
	Assert.assertEquals(purchase, new Purchase("milk", new Byn(155), 1));
	Assert.assertEquals(discountPurchase, new DiscountUnitsPurchase("milk", new Byn(155), 1, new Byn(20)));
    }

    @Test(expected = CsvLineException.class)
    public void testFactoryClassWrongPurchase() throws CsvLineException {
	FactoryClass.getPurchaseFromFactory("milk;155;1;20;12;3");
    }

    @Test(expected = CsvLineException.class)
    public void testFactoryClassPurchase() throws CsvLineException {
	FactoryClass.getPurchaseFromFactory("milk;155");
    }
}
