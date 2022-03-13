package by.epam.lab.exceptions;

public class NegativeArgumentException extends IllegalArgumentException {
    public NegativeArgumentException() {
    }

    public NegativeArgumentException(String message) {
	super(message);
    }
}
