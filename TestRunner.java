import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import by.epam.lab.*;

public class TestRunner {
    
    private static String CSV_FILE_NAME1 = "src/in.csv";
    private static String CSV_FILE_NAME2 = "wefwedas";
    
    @Test
    public void testConstructorPurchaseList() {
	//constructor
	PurchaseList purchasesActual = new PurchaseList(CSV_FILE_NAME1);
	Assert.assertNotNull(purchasesActual);
	PurchaseList purchasesEmpty = new PurchaseList(CSV_FILE_NAME2);
	Assert.assertEquals(purchasesEmpty.toString(),"");
	
	//add method index out of bounds
	List <Purchase> purchases = purchasesActual.getPurchases();
	Purchase purchase = new Purchase("www", new Byn(2), 12);
	purchasesActual.add(-10, purchase);
	Assert.assertEquals(purchase, purchases.get(0));
	purchasesActual.add(20 ,purchase);
	Assert.assertEquals(purchase, purchases.get(purchases.size() - 1));
	
	//add method 
	purchasesActual.add(5 ,purchase);
	Assert.assertEquals(purchase, purchases.get(5));
	System.out.println(purchases);
	
   }
    @Test
    public void deleteOutOfBoundsTest() {
	PurchaseList purchasesActual = new PurchaseList(CSV_FILE_NAME1);
	purchasesActual.delete(-1, 0);
	Assert.assertEquals(8, purchasesActual.getPurchases().size());
	purchasesActual.delete(12, 13);
	Assert.assertEquals(8, purchasesActual.getPurchases().size());
    }
    
    @Test
    public void deleteTest() {
	PurchaseList purchasesActual = new PurchaseList(CSV_FILE_NAME1);
	purchasesActual.delete(1, 3);
	Assert.assertEquals(6, purchasesActual.getPurchases().size());
	purchasesActual.delete(4, 7);
	Assert.assertEquals(4, purchasesActual.getPurchases().size());
	purchasesActual.delete(5, 10);
	Assert.assertEquals(4, purchasesActual.getPurchases().size());
    }
    @Test
    public void sortAndSearchTest() {
	PurchaseList purchasesActual = new PurchaseList(CSV_FILE_NAME1);
	purchasesActual.sort(PurchaseComparatorKind.NUMBER_OF_PURCHASE_UNITS);
	Assert.assertEquals("bread;1.55;1;0.2", purchasesActual.getPurchases().get(0).toString());
	System.out.println(purchasesActual);
	int index = purchasesActual.binarySearch(PurchaseComparatorKind.NUMBER_OF_PURCHASE_UNITS,new Purchase("bwef",new Byn(23),3));
	Assert.assertEquals(6, index);
    }
    @Test
    public void getCostTest() {	
	PurchaseList purchasesActual = new PurchaseList(CSV_FILE_NAME1);
	Assert.assertEquals(new Byn(4692),purchasesActual.getCost());
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
	Assert.assertEquals(purchase, new Purchase("milk",new Byn(155),1));
	Assert.assertEquals(discountPurchase, new DiscountUnitsPurchase("milk",new Byn(155),1,new Byn(20)));
    }
}
