import by.epam.lab.factories.DecimalResultFactory;
import by.epam.lab.services.RunnerLogic;
import static by.epam.lab.utils.ConstantsUtility.*;

public class RunnerDecimal {

    public static void main(String[] args) {
	RunnerLogic.execute(new DecimalResultFactory(), DECIMAL_FILE_NAME);
    }
}
