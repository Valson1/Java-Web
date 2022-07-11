import by.epam.lab.services.factories.MarkKind;

public class Runner1 {
    private final static String FILE_NAME = "src/data/results1.csv";

    public static void main(String[] args) {
	MainLogic.logic(FILE_NAME, MarkKind.MARK);
    }
}
