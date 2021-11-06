package by.gsu.epamlab;
import static java.lang.Math.round;
public class Purchase implements Comparable<Purchase> {
    public final static String NAME = "Milk";
    public final static int PRICE = 120;
    private int numberOfPurchaseUnits;
    private double disountPercent;
    private WeekDay weekDay;
    public Purchase(int numberOfPurchaseUnits,double disountPercent, 
    		WeekDay weekDay) {
		super();
		this.numberOfPurchaseUnits = numberOfPurchaseUnits;
		this.disountPercent = disountPercent;
		this.weekDay = weekDay;
    }
    public Purchase(int numberOfPurchaseUnits,double disountPercent, 
    		int weekDay) {
    	this(numberOfPurchaseUnits,disountPercent,WeekDay.values()[weekDay]);
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
        return disountPercent;
    }

    public void setDisountPercent(int disountPercent) {
        this.disountPercent = disountPercent;
    }

    public WeekDay getWeekDay() {
        return weekDay;
    }

    public void setWeekDay(WeekDay weekDay) {
        this.weekDay = weekDay;
    }
    public long getCost() {
    	return round(((PRICE * numberOfPurchaseUnits * (100 - disountPercent) / 100) / 100));
    }
    @Override
    public String toString() {
	return numberOfPurchaseUnits + ";" 
		+ disountPercent + ";" + weekDay + ";" + getCost();
    }
    
    @Override
    public int compareTo(Purchase o) {
	return this.numberOfPurchaseUnits > o.numberOfPurchaseUnits ? 
		(this.numberOfPurchaseUnits == o.numberOfPurchaseUnits ? 0 : 1) : -1;
    }    
}