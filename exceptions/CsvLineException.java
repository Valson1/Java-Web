package exceptions;

import by.epam.lab.ConstantsUtility;

public class CsvLineException extends Exception {
    public CsvLineException(String csvLine, Throwable e) {
	super(csvLine + ConstantsUtility.SPACE + e.getMessage());
    }

    public CsvLineException() {
    }
}
