package by.epam.lab.exceptions;

public class CsvLineException extends Exception {
    public CsvLineException(String message, Throwable e) {
	super(message, e);
    }

    public CsvLineException() {
    }
}
