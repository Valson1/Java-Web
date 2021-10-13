import by.gsu.epamlab.Material;
import by.gsu.epamlab.Subject;
public class Runner {
    public static void main(String[] args) {
	Material material = new Material("Steel",7850.0);
	Subject subject = new Subject("Wire",material,0.03);
	System.out.println(subject);
	material.setName("Cooper");
	material.setDensity(8500.0);
	System.out.println("The wire mass is: " + subject.getMass() + " kg");
    }
}
