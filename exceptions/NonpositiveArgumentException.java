package exceptions;

import by.epam.lab.ConstantsUtility;

public class NonpositiveArgumentException extends IllegalArgumentException {
    private final int value;

    public NonpositiveArgumentException(int value) {
	super(ConstantsUtility.EXCEPTION_MESSAGE_BYN_VALUE + value);
	this.value = value;
    }

    public int getValue() {
	return value;
    }
}
