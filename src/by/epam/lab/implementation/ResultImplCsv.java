package by.epam.lab.implementation;

import java.io.FileNotFoundException;
import static by.epam.lab.utils.ConstantsUtility.*;
import java.io.FileReader;
import java.util.Scanner;

import by.epam.lab.beans.Result;
import by.epam.lab.exceptions.SourceException;
import by.epam.lab.factories.ResultFactory;
import by.epam.lab.interfaces.ResultDao;

public class ResultImplCsv implements ResultDao {

    private Scanner scanner;
    private final ResultFactory resultFactory;

    public ResultImplCsv(String sourceName, ResultFactory resultFactory) throws SourceException {
	try {
	  scanner = new Scanner(new FileReader(sourceName));
	} catch (FileNotFoundException e) {
	    throw new SourceException(WRONG_FILE_NAME + sourceName);
	}
	this.resultFactory = resultFactory;
    }

    @Override
    public void close() throws SourceException{
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
