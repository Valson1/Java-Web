import by.epam.lab.factories.ResultFactory;
import static by.epam.lab.utils.ConstantsUtility.*;
import by.epam.lab.services.RunnerLogic;

public class RunnerInt {

    public static void main(String[] args) {
	RunnerLogic.execute(new ResultFactory(), RESULT_FILE_NAME);
    }
}
