
import by.gsu.epamlab.*;
public class Runner {
	public static void main(String[] args) {
		BusinessTrip[] businessTrips = {
								new BusinessTrip("Ivan Ivanov",4000,10),
								null,
								new BusinessTrip("Kirill Meshkov",3500,12),
								new BusinessTrip("Alexandr Medvedev",5000,14),
								new BusinessTrip()
									 	};
		int maxTotalCost =  businessTrips[0].getTotal();
		for  (BusinessTrip businessTrip : businessTrips) {
			if(businessTrip != null) {
				businessTrip.show();
				if (maxTotalCost < businessTrip.getTotal()) {
					maxTotalCost = businessTrip.getTotal();
				}
			}else {
				System.out.println("null");
			}
		}
		for (BusinessTrip businessTrip : businessTrips) {
			if(businessTrip != null) {
				if(businessTrip.getTotal() == maxTotalCost) {
					System.out.println("The business trip with maximum cost: ");
					businessTrip.show();
				}
			}
		}
		businessTrips[businessTrips.length - 1].setTransportationExpences(5500);
		System.out.println("Duration = "+ (businessTrips[0].getNumberOfDays() + businessTrips[2].getNumberOfDays()));
		for (BusinessTrip businessTrip : businessTrips) {
			System.out.println(businessTrip);
		}
	}
}

