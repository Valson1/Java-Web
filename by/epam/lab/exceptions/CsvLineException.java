package by.epam.lab.exceptions;

public class CsvLineException extends Exception {
    public CsvLineException(String message, Exception e) {
	super(message, e);
    }

    public CsvLineException() {
    }
}
