package by.gsu.epamlab;

import static java.lang.Math.round;

public class Purchase implements Comparable<Purchase> {
    public final static String NAME = "Milk";
    public final static int PRICE = 120;
    private int numberOfPurchaseUnits;
    private double disñountPercent;
    private WeekDay weekDay;

    public Purchase(int numberOfPurchaseUnits, double disountPercent, WeekDay weekDay) {
	super();
	this.numberOfPurchaseUnits = numberOfPurchaseUnits;
	this.disñountPercent = disountPercent;
	this.weekDay = weekDay;
    }

    public Purchase(int numberOfPurchaseUnits, double disountPercent, int weekDay) {
	this(numberOfPurchaseUnits, disountPercent, WeekDay.values()[weekDay]);
    }

    public Purchase() {

    }

    public int getNumberOfPurchaseUnits() {
	return numberOfPurchaseUnits;
    }

    public void setNumberOfPurchaseUnits(int numberOfPurchaseUnits) {
	this.numberOfPurchaseUnits = numberOfPurchaseUnits;
    }

    public double getDisountPercent() {
	return disñountPercent;
    }

    public void setDisountPercent(int disountPercent) {
	this.disñountPercent = disountPercent;
    }

    public WeekDay getWeekDay() {
	return weekDay;
    }

    public void setWeekDay(WeekDay weekDay) {
	this.weekDay = weekDay;
    }

    public int getCost() {
	return (int) round((PRICE * numberOfPurchaseUnits * (100 - disñountPercent) / 100) / 100) * 100;
    }

    @Override
    public String toString() {
	return numberOfPurchaseUnits + ";" 
		+ disñountPercent + ";" + weekDay + ";" + UtilityClass.toRubles(getCost());
    }

    @Override
    public int compareTo(Purchase purchase) {
	return this.numberOfPurchaseUnits - purchase.numberOfPurchaseUnits;
    }
}