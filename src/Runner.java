import by.epam.lab.services.MarkKind;

public class Runner {
    private final static String FILE_NAME = "src/data/results1.csv";

    public static void main(String[] args) {
	MainLogic.logic(FILE_NAME, MarkKind.MARK);
    }
}
