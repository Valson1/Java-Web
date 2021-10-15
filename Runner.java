import by.gsu.epamlab.Material;
import by.gsu.epamlab.Subject;
public class Runner {
    public static void main(String[] args) {
	final Material steel = new Material("Steel",7850.0);
	Subject wire = new Subject("Wire",steel,0.03);
	System.out.println(wire);
	final Material cooper = new Material("Cooper",8500.0);
	wire.setMaterial(cooper);
	System.out.println("The wire mass is: " + wire.getMass() + " kg");
    }
}
