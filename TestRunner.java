import java.math.BigDecimal;
import java.util.Enumeration;
import java.util.MissingResourceException;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.junit.Assert;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class TestRunner {
    private static final String FILE_NAME1 = "resources.in1";
    private static final String FILE_NAME2 = "resources.in2";
    private static final String FILE_NAME3 = "resources.in3";

    private static final String VALUE = "value";
    private static final String INDEX = "index(.*)";
    private static final String NATURAL_NUMBER = "[1-9]+[0-9]*";

    private static final double EXPECTED_RESULT1 = 8.24;
    private static final double EXPECTED_RESULT2 = 30.242;
    private static final double EXPECTED_RESULT3 = 1.9;

    private static final int EXPECTED_ERROR_LINES1 = 3;
    private static final int EXPECTED_ERROR_LINES2 = 9;
    private static final int EXPECTED_ERROR_LINES3 = 0;

    private static final ErrorTest EXPECTED_ERROR_TEST1 = new ErrorTest(EXPECTED_ERROR_LINES1, EXPECTED_RESULT1);
    private static final ErrorTest EXPECTED_ERROR_TEST2 = new ErrorTest(EXPECTED_ERROR_LINES2, EXPECTED_RESULT2);
    private static final ErrorTest EXPECTED_ERROR_TEST3 = new ErrorTest(EXPECTED_ERROR_LINES3, EXPECTED_RESULT3);

    public static class ErrorTest {
	public int errorLines;
	public double result;

	public ErrorTest(int errorLines, double result) {
	    this.errorLines = errorLines;
	    this.result = result;
	}

	@Override
	public boolean equals(Object obj) {
	    if (this == obj)
		return true;
	    if (obj == null)
		return false;
	    if (getClass() != obj.getClass())
		return false;
	    ErrorTest other = (ErrorTest) obj;
	    return errorLines == other.errorLines && result == other.result;
	}

    }

    private static ErrorTest getResult(String fileName) {
	ResourceBundle rb = ResourceBundle.getBundle(fileName);
	int errorLines = 0;
	BigDecimal result = new BigDecimal(0);
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
			result = result.add(new BigDecimal(rb.getString(VALUE + indexNum + indexValue).trim()));
		    } catch (MissingResourceException | NumberFormatException e) {
			errorLines++;
		    }
		} else {
		    errorLines++;
		}
	    }
	}
	return new ErrorTest(errorLines, result.doubleValue());

    }

    @Test
    public void testMainScenario() {
	ErrorTest testFile1 = getResult(FILE_NAME1);
	ErrorTest testFile2 = getResult(FILE_NAME2);
	ErrorTest testFile3 = getResult(FILE_NAME3);

	Assert.assertTrue(testFile1.equals(EXPECTED_ERROR_TEST1));
	Assert.assertTrue(testFile2.equals(EXPECTED_ERROR_TEST2));
	Assert.assertTrue(testFile3.equals(EXPECTED_ERROR_TEST3));

    }

    @Test(expected = MissingResourceException.class)
    public void testFileName() {
	getResult("erdf");
    }

}
