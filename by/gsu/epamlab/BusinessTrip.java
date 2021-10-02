package by.gsu.epamlab;

public class BusinessTrip {
	private static final int DAILY_ALLOWANCE_RATE = 5000;
	private String nameEmployeeAccount;
	private int transportationExpences;
	private int numberOfDays;
	
	public BusinessTrip() {
		super();
		this.nameEmployeeAccount = "Zhernosek Valentin";
		this.transportationExpences = 4399 ;
		this.numberOfDays = 14;
	}
	public BusinessTrip(String nameEmployeeAccount, int transportationExpences, int numberOfDays) {
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
		int totalOfExpences = transportationExpences + (DAILY_ALLOWANCE_RATE * numberOfDays);
		return totalOfExpences;
	}
	public void show() {
		System.out.println("--------------------");
		System.out.println("account = " + nameEmployeeAccount);
		System.out.println(String.format("rate = %.2f",(double)DAILY_ALLOWANCE_RATE/100).replace(',', '.'));
		System.out.println(String.format("transport = %.2f",(double)transportationExpences/100).replace(',', '.'));
		System.out.println(String.format("total = %.2f",(double)getTotal()/100).replace(',', '.'));
		System.out.println("days = " + numberOfDays);		
		System.out.println("--------------------");
	}
	@Override
	public String toString() {
		return String.format("%s;%.2f;%d;%.2f;",nameEmployeeAccount,(double)DAILY_ALLOWANCE_RATE/100,numberOfDays,(double)transportationExpences/100).replace(',', '.');
	}
}
