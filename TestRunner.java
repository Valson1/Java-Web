import java.io.FileNotFoundException;
import java.util.Enumeration;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Assert;
import org.junit.Test;

public class TestRunner {
    private static final String FILE_NAME1 = "resources.in1";
    private static final String FILE_NAME2 = "resources.in2";

    private static final String RESULT = "sum = ";
    private static final String INDEX = "index";
    private static final String WITHOUT_INDEX = "[^(index)].*";
    private static final String NATURAL_NUMBER = "[1-9]+[0-9]*";

    private static final String EXPECTED_STRING_RESULT1 = String.format("%s8.24", RESULT);
    private static final String EXPECTED_STRING_RESULT2 = String.format("%s30.242", RESULT);
    private static final int EXPECTED_ERROR_LINES1 = 3;
    private static final int EXPECTED_ERROR_LINES2 = 9;

    private static int getResult(StringBuilder strResult, String fileName) throws MissingResourceException {
	ResourceBundle rb = ResourceBundle.getBundle(fileName);
	int errorLines = 0;
	double result = 0;
	Enumeration<String> keys = rb.getKeys();
	String key;
	String keyNumPlusValue = "";
	while (keys.hasMoreElements()) {
	    key = keys.nextElement();
	    Matcher index = Pattern.compile(INDEX).matcher(key);
	    Matcher keyValue = Pattern.compile(NATURAL_NUMBER).matcher(rb.getString(key));
	    Matcher withoutIndex = Pattern.compile(WITHOUT_INDEX).matcher(key);
	    System.out.println(key + "=" + rb.getString(key));
	    try {
		if (index.lookingAt()) {
		    withoutIndex.find();
		    if (Pattern.matches(NATURAL_NUMBER, withoutIndex.group())) {
			keyValue.lookingAt();
			keyNumPlusValue = withoutIndex.group() + keyValue.group();
			System.out.println(keyNumPlusValue);
		    } else {
			errorLines++;
			continue;
		    }
		}
		
	
	    } catch (NumberFormatException | IllegalStateException e) {
		errorLines++;
	    }
	}
	strResult.append(RESULT).append(result);
	return errorLines;

    }

    @Test
    public void testMainScenario() {
	StringBuilder result1 = new StringBuilder();
	StringBuilder result2 = new StringBuilder();
	
	int errorLines1 = getResult(result1, FILE_NAME1);
	int errorLines2 = getResult(result2, FILE_NAME2);
	
	Assert.assertEquals(errorLines1, EXPECTED_ERROR_LINES1);
	Assert.assertEquals(errorLines2, EXPECTED_ERROR_LINES2);
	
	Assert.assertEquals(result1, EXPECTED_STRING_RESULT1);
	Assert.assertEquals(result2, EXPECTED_STRING_RESULT2);
	
    }
}

    
