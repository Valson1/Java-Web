package by.epam.lab.implementation;

import java.io.FileNotFoundException;
import static by.epam.lab.utils.ConstantsUtility.*;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import by.epam.lab.beans.Result;
import by.epam.lab.factories.ResultFactory;
import by.epam.lab.interfaces.ResultDao;

public class ResultImplCsv implements ResultDao {

    private static final int LOGIN_LINE_ELEMENT = 0;
    private static final int TEST_LINE_ELEMENT = 1;
    private static final int DATE_LINE_ELEMENT = 2;
    private static final int MARK_LINE_ELEMENT = 3;

    private final Scanner scanner;
    private final ResultFactory resultFactory;

    public ResultImplCsv(String sourceName, ResultFactory resultFactory) {
	Scanner scanner = null;
	try {
	    scanner = new Scanner(new FileReader(sourceName));
	} catch (FileNotFoundException e) {
	    System.err.println(e.getMessage());
	}
	this.scanner = scanner;
	this.resultFactory = resultFactory;
    }

    @Override
    public void close() throws IOException {
	scanner.close();
    }

    @Override
    public Result nextResult() {
	String[] elements = scanner.nextLine().split(SEPARATOR);
	return resultFactory.getResultFromFactory(elements[LOGIN_LINE_ELEMENT], elements[TEST_LINE_ELEMENT],
		elements[DATE_LINE_ELEMENT], elements[MARK_LINE_ELEMENT]);
    }

    @Override
    public boolean hasResult() {
	return scanner.hasNextLine();
    }
}
