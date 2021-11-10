import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import by.gsu.epamlab.*;
public class Runner {

    public static void main(String[] args) {
	try (Scanner sc = new Scanner(new FileReader("src/Purchases.txt"))){
	    final int PURCHASES_NUMBER = 6;
	    Purchase []purchases = new Purchase[PURCHASES_NUMBER];
	} catch (FileNotFoundException e) {
	    System.err.println("File not found");;
	}
    }

}
