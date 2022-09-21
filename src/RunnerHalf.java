import by.epam.lab.factories.HalfResultFactory;
import by.epam.lab.services.RunnerLogic;
import static by.epam.lab.utils.ConstantsUtility.*;

public class RunnerHalf {

    public static void main(String[] args) {
	RunnerLogic.execute(new HalfResultFactory(), HALF_FILE_NAME);
    }
}
