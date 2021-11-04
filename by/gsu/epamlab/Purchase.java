package by.gsu.epamlab;
public class Purchase implements Comparable<Purchase> {
    private final static String NAME = "Milk";
    private final static int PRICE = 120;
    private int numberOfPurchaseUnits;
    private int disountPercent;
    private WeekDay weekDay;
    public Purchase(int numberOfPurchaseUnits,int disountPercent, 
    		WeekDay weekDay) {
		super();
		this.numberOfPurchaseUnits = numberOfPurchaseUnits;
		this.disountPercent = disountPercent;
		this.weekDay = weekDay;
    }
    public Purchase() {
    	
    }

    public String getName() {
        return NAME;
    }
    
    public int getPrice() {
        return PRICE;
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
    	return PRICE * numberOfPurchaseUnits * (100 - disountPercent) / 100;
    }
    
    @Override
    public String toString() {
	return NAME + ";" + toRubles(PRICE) + ";" + numberOfPurchaseUnits + ";" 
		+ disountPercent + ";" + weekDay + ";" + roundtoRuble(getCost());
    }
    
    @Override
    public int compareTo(Purchase o) {
	return this.numberOfPurchaseUnits > o.numberOfPurchaseUnits ? 
		(this.numberOfPurchaseUnits == o.numberOfPurchaseUnits ? 0 : 1) : -1;
    }    
    private String toRubles(int value) {
    	return value / 100 + "." + value / 10 % 10 + value % 10;	
	}
    
    private int roundtoRuble(int value) {
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