import by.epam.lab.services.factories.MarkKind;

public class Runner2 {
    private static final String FILE_NAME = "src/data/results2.xml";

    public static void main(String[] args) {
	MainLogic.logic(FILE_NAME, MarkKind.DECIMAL_MARK);
    }
}
