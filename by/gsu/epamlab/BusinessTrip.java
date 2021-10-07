package by.gsu.epamlab;

public class BusinessTrip {
	private final static int DAILY_ALLOWANCE_RATE = 5001;
	private String nameEmployeeAccount;
	private int transportationExpences;
	private int numberOfDays;
	
	public BusinessTrip() {
		super();
	}
	public BusinessTrip(String nameEmployeeAccount, int transportationExpences,
				int numberOfDays) {
		super();
		this.nameEmployeeAccount = nameEmployeeAccount;
		this.transportationExpences = transportationExpences;
		this.numberOfDays = numberOfDays;
	}
	
	public String getNameEmployeeAccount() {
		return nameEmployeeAccount;
	}
	public void setNameEmployeeAccount(String nameEmployeeAccount) {
		this.nameEmployeeAccount = nameEmployeeAccount;
	}
	public int getTransportationExpences() {
		return transportationExpences;
	}
	public void setTransportationExpences(int transportationExpences) {
		this.transportationExpences = transportationExpences;
	}
	public int getNumberOfDays() {
			return numberOfDays;			
	}
	public void setNumberOfDays(int numberOfDays) {
				this.numberOfDays = numberOfDays;	
		}
	
	public int getTotal() {
		int totalOfExpences = transportationExpences 
				+ DAILY_ALLOWANCE_RATE * numberOfDays;
		return totalOfExpences;
	}
	public void show() {
		System.out.println("--------------------");
		System.out.println("rate = " + toRubles(DAILY_ALLOWANCE_RATE));
		System.out.println("account = "+ nameEmployeeAccount);
		System.out.println("transport = " + toRubles(transportationExpences));
		System.out.println("days = " + numberOfDays);		
		System.out.println("total = " + toRubles(getTotal()));
		System.out.println("--------------------");
	}
	@Override
	public String toString() {
		return nameEmployeeAccount +";" + toRubles(transportationExpences) + ";"
				+ numberOfDays + ";" + toRubles(getTotal());
	}
	public String toRubles(int value) {
		int valueBefore = value / 100;
		int valueTenthPart = value / 10 % 10;
		int valueHundredthPart = value % 10;
		return valueBefore + "." + valueTenthPart + valueHundredthPart;
	}
}
