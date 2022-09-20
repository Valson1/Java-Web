import by.epam.lab.factories.ResultFactory;
import by.epam.lab.services.RunnerLogic;

public class RunnerInt {
    private final static String SOURCE_NAME = "src/data/results1.txt";

    public static void main(String[] args) {
	ResultFactory resultFactory = new ResultFactory();
	RunnerLogic.execute(resultFactory, SOURCE_NAME);
    }
}
