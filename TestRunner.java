import java.util.Enumeration;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.junit.Assert;
import org.junit.Test;

public class TestRunner {
    private static final String FILE_NAME1 = "resources.in1";
    private static final String FILE_NAME2 = "resources.in2";
    private static final String FILE_NAME3 = "resources.in3";

    private static final StringBuilder RESULT_FILE1 = new StringBuilder();
    private static final StringBuilder RESULT_FILE2 = new StringBuilder();
    private static final StringBuilder RESULT_FILE3 = new StringBuilder();

    private static final String RESULT = "sum = ";
    private static final String VALUE = "value";
    private static final String INDEX = "index(.*)";
    private static final String NATURAL_NUMBER = "[1-9]+[0-9]*";

    private static final String EXPECTED_STRING_RESULT1 = String.format("%s8.24", RESULT);
    private static final String EXPECTED_STRING_RESULT2 = String.format("%s30.242", RESULT);
    private static final String EXPECTED_STRING_RESULT3 = String.format("%s1.9", RESULT);

    private static final int EXPECTED_ERROR_LINES1 = 3;
    private static final int EXPECTED_ERROR_LINES2 = 9;
    private static final int EXPECTED_ERROR_LINES3 = 0;

    private static int getResult(StringBuilder strResult, String fileName) {
	ResourceBundle rb = ResourceBundle.getBundle(fileName);
	int errorLines = 0;
	double result = 0;
	Enumeration<String> keys = rb.getKeys();
	String key;
	while (keys.hasMoreElements()) {
	    key = keys.nextElement();
	    Matcher indexMatcher = Pattern.compile(INDEX).matcher(key);
	    if (indexMatcher.matches()) {
		String indexNum = indexMatcher.group(1);
		String indexValue = rb.getString(key);
		Matcher indexNumMatcher = Pattern.compile(NATURAL_NUMBER).matcher(indexNum);
		Matcher indexValueMatcher = Pattern.compile(NATURAL_NUMBER).matcher(indexValue);
		if (indexValueMatcher.matches() && indexNumMatcher.matches()) {
		    try {
			result += Double.parseDouble(rb.getString(VALUE + indexNum + indexValue));
		    } catch (MissingResourceException | NumberFormatException e) {
			errorLines++;
		    }
		} else {
		    errorLines++;
		}
	    }
	}
	strResult.append(RESULT).append(result);
	return errorLines;
    }

    @Test
    public void testMainScenario() {
	int errorLines1 = getResult(RESULT_FILE1, FILE_NAME1);
	int errorLines2 = getResult(RESULT_FILE2, FILE_NAME2);
	int errorLines3 = getResult(RESULT_FILE3, FILE_NAME3);

	Assert.assertEquals(EXPECTED_ERROR_LINES1, errorLines1);
	Assert.assertEquals(EXPECTED_ERROR_LINES2, errorLines2);
	Assert.assertEquals(EXPECTED_ERROR_LINES3, errorLines3);

	Assert.assertEquals(EXPECTED_STRING_RESULT1, RESULT_FILE1.toString());
	Assert.assertEquals(EXPECTED_STRING_RESULT2, RESULT_FILE2.toString());
	Assert.assertEquals(EXPECTED_STRING_RESULT3, RESULT_FILE3.toString());

    }
}
