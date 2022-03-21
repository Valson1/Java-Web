import by.epam.lab.beans.*;
import by.epam.lab.interfaces.Priceable;
import by.epam.lab.utils.PurchaseUtils;

public class Runner {
    public static void main(String[] args) {
	// create purchase1 instance
	Purchase purchase1 = new Purchase(new Product("milk", new Byn(170)), 20);
	// create instance purchaseUtils1 and output purchase1 and cost
	PurchaseUtils purchaseUtils1 = new PurchaseUtils(purchase1);
	purchaseUtils1.printPurchase();
	purchaseUtils1.printCost();
	// create purchase2
	Purchase purchase2 = new Purchase(new Product("sugar", new Byn(300)), 12.5);
	// output cost of purchase2 and cost difference between purchase2 and purchase1
	PurchaseUtils purchaseUtils2 = new PurchaseUtils(purchase2);
	purchaseUtils2.printCost();
	purchaseUtils2.printCostDiff(purchase1);
	// create purchase3
	Purchase purchase3 = new Purchase(new DiscountProduct("sugar", new Byn(280), new Byn(10)), 60);
	// create instance purchaseUtils3 without purchase instance
	PurchaseUtils purchaseUtils3 = new PurchaseUtils(new Service("gym workout", new Byn(7560), 5), 2.25);
	// output item of last purchase
	Priceable item = purchaseUtils3.getPurchase().getItem();
	System.out.println(item);
	// create PurchaseList
	Purchase[] purchases = { purchase1, purchase2, purchaseUtils3.getPurchase() };
	// output last purchase getCost
	purchaseUtils3.printCost();
	// check the same purchase
	purchaseUtils2.printIsSameCost(purchases);
    }
}
