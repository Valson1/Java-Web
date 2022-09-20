import by.epam.lab.factories.DecimalResultFactory;
import by.epam.lab.factories.ResultFactory;
import by.epam.lab.services.RunnerLogic;

public class RunnerDecimal {
    private static final String FILE_NAME = "src/data/results2.xml";
    
    public static void main(String[] args) {
	ResultFactory resultFactory = new DecimalResultFactory();
	RunnerLogic.execute(resultFactory, FILE_NAME);
    }
}
