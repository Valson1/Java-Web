package exceptions;

public class NegativeArgumentException extends IllegalArgumentException {
    private int value;
    public NegativeArgumentException() {
    }

    public NegativeArgumentException(String message,int value) {
	super(message);
	this.value = value;
    }
}
