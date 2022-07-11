import by.epam.lab.services.factories.MarkKind;

public class Runner3 {
    private final static String FILE_NAME = "src/data/results3.csv";

    public static void main(String[] args) {
	MainLogic.logic(FILE_NAME, MarkKind.HALF_MARK);
    }
}
