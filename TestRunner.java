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

    private static final double EXPECTED_RESULT1 = 8.24;
    private static final double EXPECTED_RESULT2 = 30.242;
    private static final double EXPECTED_RESULT3 = 1.9;

    private static final int EXPECTED_ERROR_LINES1 = 3;
    private static final int EXPECTED_ERROR_LINES2 = 9;
    private static final int EXPECTED_ERROR_LINES3 = 0;

    private static class ErrorTest {
	private int errorLines;
	private double result;

	public ErrorTest(int errorLines, double result) {
	    this.errorLines = errorLines;
	    this.result = result;
	}
    }

    private static ErrorTest getResult(String fileName) {
	final String VALUE = "value";
	final String INDEX = "index(.*)";
	final String NATURAL_NUMBER = "[1-9]+[0-9]*";
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
	class TestCase {
	    private int errorLines;
	    private double result;
	    private String fileName;

	    public TestCase(int errorLines, double result, String fileName) {
		this.errorLines = errorLines;
		this.result = result;
		this.fileName = fileName;
	    }

	    public TestCase(ErrorTest errorTest, String fileName) {
		this.errorLines = errorTest.errorLines;
		this.result = errorTest.result;
		this.fileName = fileName;
	    }

	    @Override
	    public boolean equals(Object obj) {
		if (this == obj)
		    return true;
		if (obj == null)
		    return false;
		if (getClass() != obj.getClass())
		    return false;
		TestCase other = (TestCase) obj;
		return errorLines == other.errorLines && fileName.equals(other.fileName) && result == other.result;
	    }
	}
	TestCase[] testCases = { new TestCase(EXPECTED_ERROR_LINES1, EXPECTED_RESULT1, FILE_NAME1),
		new TestCase(EXPECTED_ERROR_LINES2, EXPECTED_RESULT2, FILE_NAME2),
		new TestCase(EXPECTED_ERROR_LINES3, EXPECTED_RESULT3, FILE_NAME3), };

	for (int i = 0; i < testCases.length; i++) {
	    String fileName = testCases[i].fileName;
	    Assert.assertEquals(testCases[i], new TestCase(getResult(fileName), fileName));
	}
    }

    @Test(expected = MissingResourceException.class)
    public void testFileName() {
	getResult("erdf");
    }

}
