import by.epam.lab.services.*;
import by.epam.lab.beans.Result;
import static by.epam.lab.utils.Constants.*;

public class Runner {
    public static void main(String[] args) {
	SAXBuilder builder = new SAXBuilder();
	builder.buildListResults(FILE_NAME);
	for (Result result : builder.getResults()) {
	    System.out.println(result);
	}
    }
}
