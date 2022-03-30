import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import by.epam.lab.beans.*;
import by.epam.lab.services.PurchaseFactory;

import static by.epam.lab.utils.ConstantsUtility.*;

public class Runner {
    private static <T, E> void printMap(Map<T, E> map) {
	for (Map.Entry<T, E> entry : map.entrySet()) {
	    System.out.println(entry.getKey() + ELEMENT_SEPARATOR + entry.getValue());
	}
    }

    @SafeVarargs
    private static <T, E> void findElement(T element, Map<T, E>... purchasesMap) {
	for (Map<T, E> map : purchasesMap) {
	    if (map.containsKey(element)) {
		System.out.println(map.get(element));
	    } else {
		System.out.println(KEY_NOT_FOUND);
		break;
	    }
	}
    }

    private static <K extends Purchase> void removeEntries(Map<K, WeekDay> map, String criterion) {
	for (Iterator<Map.Entry<K, WeekDay>> it = map.entrySet().iterator(); it.hasNext();) {
	    Map.Entry<K, WeekDay> next = it.next();
	    if (next.getKey().getProduct().getName().equals(criterion)
		    || next.getValue().toString().equals(criterion)) {
		it.remove();
	    }
	}

    }

    @SafeVarargs
    private static <T extends Collection<? extends Purchase>> void totalCost(T... purchases) {
	for (T list : purchases) {
	    Byn totalCost = new Byn();
	    for (Purchase purchase : list) {
		totalCost = totalCost.sum(purchase.getCost());
	    }
	    System.out.println(COST + totalCost + BYN);
	}
    }

    public static void main(String[] args) {
	try (Scanner sc = new Scanner(new FileReader(FILE_NAME))) {
	    Map<Purchase, WeekDay> lastPurchaseMap = new HashMap<>();
	    Map<Purchase, WeekDay> firstPurchaseMap = new HashMap<>();
	    List<PricePurchase> pricePurchases = new ArrayList<>();
	    Map<WeekDay, List<Purchase>> weekDayMap = new EnumMap<>(WeekDay.class);
	    while (sc.hasNextLine()) {
		String purchaseLine = sc.nextLine();
		String weekDayLine = sc.nextLine();
		WeekDay weekDay = WeekDay.valueOf(weekDayLine);
		Purchase purchase = PurchaseFactory.getPurchaseFromFactory(purchaseLine);
		// load content into map, where key = Purchase, value = WeekDay of last purchase
		lastPurchaseMap.put(purchase, weekDay);
		// load content into map, where key = Purchase, value = WeekDay of first
		// purchase
		firstPurchaseMap.putIfAbsent(purchase, weekDay);
		// load purchases with discount into the list
		if (purchase instanceof PricePurchase) {
		    pricePurchases.add((PricePurchase) purchase);
		}
		// load content into enum map, where key = WeekDay, value = List<Purchase>
		List<Purchase> purchases = new ArrayList<Purchase>();
		if (weekDayMap.containsKey(weekDay)) {
		    weekDayMap.get(weekDay).add(purchase);
		} else {
		    purchases.add(purchase);
		    weekDayMap.put(weekDay, purchases);
		}
	    }
	    // output lastPurchaseMap
	    System.out.println(LAST_PURCHASE_MAP);
	    printMap(lastPurchaseMap);
	    // output firstPurchaseMap
	    System.out.println(FIRST_PURCHASE_MAP);
	    printMap(firstPurchaseMap);
	    // find the first and the last weekdays for bread with price 1.55;
	    findElement(new Purchase(new Product("bread", new Byn(155)), 1), firstPurchaseMap, lastPurchaseMap);
	    // find the first weekday for bread with price 1.70;
	    findElement(new Purchase(new Product("bread", new Byn(170)), 1), firstPurchaseMap);
	    // remove all entries from the first map where the purchase name is meat;
	    removeEntries(lastPurchaseMap, "meat");
	    // remove all entries from the second map on FRIDAY;
	    removeEntries(firstPurchaseMap, WeekDay.FRIDAY.toString());
	    printMap(lastPurchaseMap);
	    printMap(firstPurchaseMap);
	    // print the total cost of these purchases;
	    totalCost(pricePurchases);
	    // output weekDayMap
	    System.out.println(WEEKDAY_MAP);
	    printMap(weekDayMap);
	    // print the total cost of all purchases for each weekday;
	    totalCost(weekDayMap.get(WeekDay.MONDAY), weekDayMap.get(WeekDay.WEDNESDAY),
		    weekDayMap.get(WeekDay.THURSDAY), weekDayMap.get(WeekDay.FRIDAY), weekDayMap.get(WeekDay.SUNDAY));
	    // find all purchases on MONDAY.
	    findElement(WeekDay.MONDAY, weekDayMap);
	} catch (FileNotFoundException e) {
	    System.err.println(FILE_NOT_FOUND);
	}
    }
}
