import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

import by.epam.lab.beans.*;
import by.epam.lab.services.PurchaseFactory;

import static by.epam.lab.utils.ConstantsUtility.*;

public class Runner {
    private static <K, V> void printMap(Map<K, V> map, String message) {
	System.out.println(message);
	for (Map.Entry<K, V> entry : map.entrySet()) {
	    System.out.println(entry.getKey() + ELEMENT_SEPARATOR + entry.getValue());
	}
    }

    private static <K, V> void findElement(Map<K, V> map, K searchKey, String header) {
	V value = map.get(searchKey);
	System.out.println(value != null ? header + VALUE_FOUND + value : KEY_NOT_FOUND);
    }

    public interface EntryChecker<K, V> {
	boolean check(Map.Entry<K, V> entry);
    }

    private static <K, V> void removeEntries(Map<K, V> map, EntryChecker<K, V> checker) {
	for (Iterator<Map.Entry<K, V>> it = map.entrySet().iterator(); it.hasNext();) {
	    if (checker.check(it.next())) {
		it.remove();
	    }
	}
    }

    private static Byn totalCost(List<? extends Purchase> purchases) {
	Byn totalCost = new Byn();
	for (Purchase purchase : purchases) {
	    totalCost = totalCost.sum(purchase.getCost());
	}
	return totalCost;
    }

    public static void main(String[] args) {
	try (Scanner sc = new Scanner(new FileReader(FILE_NAME))) {
	    Map<Purchase, WeekDay> lastPurchasesMap = new HashMap<>();
	    Map<Purchase, WeekDay> firstPurchasesMap = new HashMap<>();
	    List<PricePurchase> pricePurchases = new ArrayList<>();
	    Map<WeekDay, List<Purchase>> weekDayPurchasesMap = new EnumMap<>(WeekDay.class);
	    while (sc.hasNextLine()) {
		Purchase purchase = PurchaseFactory.getPurchaseFromFactory(sc.nextLine());
		WeekDay weekDay = WeekDay.valueOf(sc.nextLine());
		// load content into map, where key = Purchase, value = WeekDay of last purchase
		lastPurchasesMap.put(purchase, weekDay);
		// load content into map, where key = Purchase, value = WeekDay of first
		// purchase
		firstPurchasesMap.putIfAbsent(purchase, weekDay);
		// load purchases with discount into the list
		if (purchase instanceof PricePurchase) {
		    pricePurchases.add((PricePurchase) purchase);
		}
		// load content into enum map, where key = WeekDay, value = List<Purchase>
		List<Purchase> purchases = weekDayPurchasesMap.get(weekDay);
		if (purchases == null) {
		    weekDayPurchasesMap.put(weekDay, purchases = new ArrayList<>());
		}
		purchases.add(purchase);
	    }
	    // output lastPurchaseMap
	    printMap(lastPurchasesMap, LAST_PURCHASE_MAP);
	    // output firstPurchaseMap
	    printMap(firstPurchasesMap, FIRST_PURCHASE_MAP);
	    // find the first and the last weekdays for bread with price 1.55;
	    findElement(firstPurchasesMap, new Purchase(new Product("bread", new Byn(155)), 1), FIRST_PURCHASE_MAP);
	    findElement(lastPurchasesMap, new Purchase(new Product("bread", new Byn(155)), 1), LAST_PURCHASE_MAP);
	    // find the first weekday for bread with price 1.70;
	    findElement(firstPurchasesMap, new Purchase(new Product("bread", new Byn(170)), 1), FIRST_PURCHASE_MAP);
	    // remove all entries from the first map where the purchase name is meat;
	    removeEntries(lastPurchasesMap, new EntryChecker<>() {
		@Override
		public boolean check(Entry<Purchase, WeekDay> entry) {
		    return entry.getKey().getProduct().getName().equals("meat");
		}
	    });
	    // remove all entries from weekDayMap where list has purchases with name "milk";
	    removeEntries(firstPurchasesMap, new EntryChecker<>() {
		@Override
		public boolean check(Entry<Purchase, WeekDay> entry) {
		    return entry.getValue().equals(WeekDay.FRIDAY);
		}
	    });
	    printMap(lastPurchasesMap, LAST_PURCHASE_MAP);
	    printMap(firstPurchasesMap, FIRST_PURCHASE_MAP);
	    // print the total cost of these purchases;
	    totalCost(pricePurchases);
	    // output weekDayMap
	    printMap(weekDayPurchasesMap, WEEKDAY_MAP);
	    // print the total cost of all purchases for each weekday;
	    for (Map.Entry<WeekDay, List<Purchase>> entry : weekDayPurchasesMap.entrySet()) {
		System.out.println(entry.getKey().toString() + COST + totalCost(entry.getValue()) + BYN);
	    }
	    // find all purchases on MONDAY.
	    findElement(weekDayPurchasesMap, WeekDay.MONDAY, WeekDay.MONDAY.toString());
	    // remove all entries from the second map on FRIDAY;
	    removeEntries(weekDayPurchasesMap, new EntryChecker<>() {
		@Override
		public boolean check(Entry<WeekDay, List<Purchase>> entry) {
		    boolean isExist = false;
		    for (Purchase purchase : entry.getValue()) {
			if (purchase.getProduct().getName().equals("milk")) {
			    isExist = true;
			    break;
			}
		    }
		    return isExist;
		}
	    });
	    printMap(weekDayPurchasesMap, WEEKDAY_MAP);
	} catch (FileNotFoundException e) {
	    System.err.println(FILE_NOT_FOUND);
	}
    }
}
