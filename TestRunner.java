import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

import org.junit.Assert;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class TestRunner {

    private static int getResult(String csvName, StringBuilder strResult) throws FileNotFoundException {
	try (Scanner sc = new Scanner(new FileReader(csvName))) {
	    double result = 0;
	    int errorLines = 0;
	    while (sc.hasNext()) {
		String line = sc.nextLine();
		String[] elements = line.split(";");
		if (isInt(elements[0])) {
		    int firstElementValue = Integer.parseInt(elements[0]);
		    if (checkFirstElementValue(firstElementValue, elements.length)
			    && isDouble(elements[firstElementValue])) {
			result += Double.parseDouble(elements[firstElementValue]);
		    } else {
			errorLines++;
		    }
		} else {
		    errorLines++;
		}
	    }
	    strResult.append(result);
	    return errorLines;
	}
    }

    private static boolean isInt(String s) throws NumberFormatException {
	boolean isNumeric = false;
	try {
	    Integer.parseInt(s);
	    isNumeric = true;
	} catch (NumberFormatException e) {
	}
	return isNumeric;
    }

    private static boolean isDouble(String s) throws NumberFormatException {
	boolean isNumeric = false;
	try {
	    Double.parseDouble(s);
	    isNumeric = true;
	} catch (NumberFormatException e) {
	}
	return isNumeric;
    }

    private static boolean checkFirstElementValue(int firstElementValue, int arrayLength) {
	return firstElementValue < arrayLength && firstElementValue > 0;
    }

    @Test
    public void testMainScenario() throws FileNotFoundException {
	StringBuilder result = new StringBuilder();
	int errorLines = getResult("src/src.txt", result);
	Assert.assertEquals(3, errorLines);
	String expected = "0.0";
	Assert.assertEquals(expected, result.toString());
    }

    @Test(expected = FileNotFoundException.class)
    public void testWrongCsvName() throws FileNotFoundException {
	StringBuilder result = new StringBuilder();
	int errorLines = getResult("src/srcwefw.txt", result);
    }
}
