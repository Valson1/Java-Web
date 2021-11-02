package by.gsu.epamlab;

import java.util.Arrays;

public class PurchaseAction {
    public static void showCostInformation(int sumOfPurchasesCost, 
	    int sumOfPurchasesCostOnMonday,Purchase maxCostOfPurchase){
	System.out.println("Mean cost = " + sumOfPurchasesCost / 1000 + "." 
		+ sumOfPurchasesCost % 1000);
	System.out.println("The total cost on Monday = " 
		+ toRubles(sumOfPurchasesCostOnMonday));
	System.out.println("The day with the maximum cost purchase is " 
		+ maxCostOfPurchase.getWeekDay());
    }
    public static void findPurchase(Purchase[]purchases) {
    	int location = Arrays.binarySearch(purchases,purchases[4]);
		if (location < 0) {
			System.out.println("Requred purchase is not found");		
		}else {
			System.out.println("Requred purchase is " + location);
		}
    }
    public static String toRubles(int value) {
	int valueBefore = value / 100;
	int valueTenthPart = value / 10 % 10;
	int valueHundredthPart = value % 10;
	return valueBefore + "." + valueTenthPart + valueHundredthPart;	
	}
    
    public static int roundtoRuble(int value) {
	int valueBefore = value / 100;
	int valueTenthPart = value / 10 % 10;
	int valueHundredthPart = value % 10;
	if(valueTenthPart != 0 || valueHundredthPart != 0) {
	    if(valueHundredthPart >= 5) {
		valueTenthPart += 1;
	    }
	    else {
		valueTenthPart -= 1;
	    }
	    if (valueTenthPart >= 5) {
		return (valueBefore + 1); 
	    }
	    else {
		return (valueBefore - 1); 
	    }
	}
	return valueBefore;
    } 
}
