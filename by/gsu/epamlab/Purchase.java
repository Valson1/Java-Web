package by.gsu.epamlab;
public class Purchase implements Comparable<Purchase> {
    private final String name;
    private int price;
    private int numberOfPurchaseUnits;
    private int disountPercent;
    private WeekDay weekDay;
    public Purchase(String name, int price, int numberOfPurchaseUnits,
	    int disountPercent, WeekDay weekDay) {
	super();
	this.name = name;
	this.price = price;
	this.numberOfPurchaseUnits = numberOfPurchaseUnits;
	this.disountPercent = disountPercent;
	this.weekDay = weekDay;
    }
    
    public Purchase() {
	this.name = "";
	
    }

    public String getName() {
        return name;
    }
    
    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getNumberOfPurchaseUnits() {
        return numberOfPurchaseUnits;
    }

    public void setNumberOfPurchaseUnits(int numberOfPurchaseUnits) {
        this.numberOfPurchaseUnits = numberOfPurchaseUnits;
    }

    public int getDisountPercent() {
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
    public int getCost() {
	return price * numberOfPurchaseUnits * (100 - disountPercent) / 100;
    }
    
    @Override
    public String toString() {
	return name + ";" + PurchaseAction.toRubles(price) + ";" + numberOfPurchaseUnits + ";" 
		+ disountPercent + ";" + weekDay + ";" + PurchaseAction.roundtoRuble(getCost());
    }
    
    @Override
    public int compareTo(Purchase o) {
	return this.numberOfPurchaseUnits > o.numberOfPurchaseUnits ? 
		(this.numberOfPurchaseUnits == o.numberOfPurchaseUnits ? 0 : 1) : -1;
    }    
}