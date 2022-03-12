package exceptions;

import utils.ConstantsUtility;

public class NonpositiveArgumentException extends IllegalArgumentException {

    public NonpositiveArgumentException(String message) {
	super(message);
    }

}
