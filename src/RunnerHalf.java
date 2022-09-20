import by.epam.lab.factories.HalfResultFactory;
import by.epam.lab.factories.ResultFactory;
import by.epam.lab.services.RunnerLogic;

public class RunnerHalf {

    private static final String FILE_NAME = "src/data/results3.txt";

    public static void main(String[] args) {
	ResultFactory resultFactory = new HalfResultFactory();
	RunnerLogic.execute(resultFactory, FILE_NAME);
    }
}
