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

    private static final double EXPECTED_RESULT1 = 8.24;
    private static final double EXPECTED_RESULT2 = 30.242;
    private static final double EXPECTED_RESULT3 = 1.9;

    private static final int EXPECTED_ERROR_LINES1 = 3;
    private static final int EXPECTED_ERROR_LINES2 = 9;
    private static final int EXPECTED_ERROR_LINES3 = 0;

    static class ErrorTest {
	private int errorLines;
	private double result;

	public ErrorTest(int errorLines, double result) {
	    this.errorLines = errorLines;
	    this.result = result;
	}

	public ErrorTest() {
	}

	public int getErrorLines() {
	    return errorLines;
	}

	public double getResult() {
	    return result;
	}

    }

    private static ErrorTest getResult(String fileName) {
	final String VALUE = "value";
	final String INDEX = "index(.*)";
	final String NATURAL_NUMBER = "[1-9]\\d*";
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
			result += Double.parseDouble(rb.getString(VALUE + indexNum + indexValue).trim());
		    } catch (MissingResourceException | NumberFormatException e) {
			errorLines++;
		    }
		} else {
		    errorLines++;
		}
	    }
	}
	return new ErrorTest(errorLines, result);
    }

    @Test
    public void testMainScenario() {
	class TestCase {
	    private ErrorTest errorTest;
	    private String fileName;
	    private static final double DELTA = 0.000000000000001;

	    public TestCase(ErrorTest errorTest, String fileName) {
		this.errorTest = errorTest;
		this.fileName = fileName;
	    }
	}
	TestCase[] testCases = { new TestCase(new ErrorTest(EXPECTED_ERROR_LINES1, EXPECTED_RESULT1), FILE_NAME1),
		new TestCase(new ErrorTest(EXPECTED_ERROR_LINES2, EXPECTED_RESULT2), FILE_NAME2),
		new TestCase(new ErrorTest(EXPECTED_ERROR_LINES3, EXPECTED_RESULT3), FILE_NAME3), };

	for (TestCase testCase : testCases) {
	    ErrorTest test = getResult(testCase.fileName);
	    Assert.assertEquals(testCase.errorTest.errorLines, test.errorLines);
	    Assert.assertEquals(testCase.errorTest.result, test.result, TestCase.DELTA);
	}
    }

    @Test(expected = MissingResourceException.class)
    public void testFileName() {
	getResult("erdf");
    }

}
