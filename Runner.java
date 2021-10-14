import by.gsu.epamlab.Material;
import by.gsu.epamlab.Subject;
public class Runner {
    public static void main(String[] args) {
	final Material steel = new Material("Steel",7850.0);
	Subject subject = new Subject("Wire",steel,0.03);
	System.out.println(subject);
	final Material cooper = new Material("Cooper",8500.0);
	subject.setMaterial(cooper);
	System.out.println("The wire mass is: " + subject.getMass() + " kg");
    }
}
