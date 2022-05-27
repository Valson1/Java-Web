import static by.epam.lab.utils.ConstantsUtility.*;

import by.epam.lab.services.MarkKind;

public class Runner2 {
    private static final String FILE_NAME = "src/data/results3.csv";
    
    public static void main(String[] args) {
	MainLogic.logic(FILE_NAME, MarkKind.HALF_MARK);
    }
}
