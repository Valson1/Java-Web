import java.util.Comparator;
import by.epam.lab.beans.*;
import by.epam.lab.services.PurchaseList;
import static by.epam.lab.utils.ConstantsUtility.*;
import by.epam.lab.utils.PurchaseUtils;

public class Runner {
    public static void main(String[] args) {
	//create purchase1 instance
	Purchase purchase1 = new Purchase(new Product("milk",new Byn(170)),20);
	// create instance purchaseUtils1 and output purchase1 and cost
	PurchaseUtils purchaseUtils1 = new PurchaseUtils(purchase1);
	purchaseUtils1.printPurchase();
	purchaseUtils1.printCost();
	//create purchase2 
	Purchase purchase2 = new Purchase(new Product("sugar",new Byn(300)),12.5);
	//output cost of purchase2 and cost difference between purchase2 and purchase1
	PurchaseUtils purchaseUtils2 = new PurchaseUtils(purchase2);
	purchaseUtils2.printCost();
	purchaseUtils2.printCostDiff(purchase1);
	//create purchase3
	Purchase purchase3 = new Purchase(new DiscountProduct("sugar",new Byn(280),new Byn(10)),60);
	//create instance purchaseUtils3 without purchase instance
	Service service = new Service("gym workout",new Byn(7560),5);
	PurchaseUtils purchaseUtils3 = new PurchaseUtils(service,2.25);
	//output item of last purchase
	Product item = service;
	System.out.println(item);
	//create PurchaseList
	PurchaseList purchases = new PurchaseList(new Comparator<Purchase>(){
	    @Override
	    public int compare(Purchase o1, Purchase o2) {
		return o1.getProduct().getPrice().compareTo(o2.getProduct().getPrice());
	    }
	});
	purchases.add(FIRST_ELEMENT, purchase1);
	purchases.add(SECOND_ELEMENT, purchase3);
	purchases.add(THIRD_ELEMENT,purchaseUtils3.getPurchase());
	//output last purchase getCost
	purchaseUtils3.printCost();
	//check the same purchase
	purchaseUtils2.printIsSameCost(purchases);
    }
}
