import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

import org.junit.Assert;
import org.junit.Test;

public class TestRunner {
    private static final int EXPECTED_ERROR_LINES_FILE1 = 3;
    private static final int EXPECTED_ERROR_LINES_FILE2 = 0;
    private static final int EXPECTED_ERROR_LINES_FILE3 = 0;
    private static final int EXPECTED_ERROR_LINES_FILE4 = 0;
    private static final int EXPECTED_ERROR_LINES_FILE5 = 1;

    private static final String CSV_SEPARATOR = ";";
    private static final String BEFORE_SIGN = " ";
    private static final String AFTER_SIGN = " ";
    private static final String MINUS = BEFORE_SIGN + "-" + AFTER_SIGN;
    private static final String PLUS = BEFORE_SIGN + "+" + AFTER_SIGN;
    private static final String RESULT_HEAD = "result(";
    private static final String RESULT_TAIL = ") = ";

    private static final String EXPECTED_RESULT_FILE1 = String.format("%s5.2%s3.14%s0.0%s2.06", RESULT_HEAD, MINUS, PLUS, RESULT_TAIL);
    private static final String EXPECTED_RESULT_FILE2 = String.format("%s-3.1%s1.0%s-4.1",RESULT_HEAD, MINUS, RESULT_TAIL);
    private static final String EXPECTED_RESULT_FILE3 = String.format("%s0.75%s0.75",RESULT_HEAD, RESULT_TAIL);
    private static final String EXPECTED_RESULT_FILE4 = String.format("%s0.0%s0.0",RESULT_HEAD, RESULT_TAIL);
    private static final String EXPECTED_RESULT_FILE5 = String.format("%s%s0.0", RESULT_HEAD, RESULT_TAIL);

    private static int getResult(String csvName, StringBuilder strResult) throws FileNotFoundException {
	try (Scanner sc = new Scanner(new FileReader(csvName))) {
	    double result = 0;
	    int errorLines = 0;
	    while (sc.hasNextLine()) {
		String[] elements = sc.nextLine().split(CSV_SEPARATOR);
		try {
		    int position = Integer.parseInt(elements[0].trim());
		    double elementValueOfFirstIElementIndexValue = Double.parseDouble(elements[position]);
		    result += elementValueOfFirstIElementIndexValue;
		    strResult = elementValueOfFirstIElementIndexValue >= 0
			    ? strResult.append(PLUS).append(elementValueOfFirstIElementIndexValue)
			    : strResult.append(MINUS).append(Math.abs(elementValueOfFirstIElementIndexValue));
		} catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
		    errorLines++;
		}
	    }
	    if (strResult.length() > 0) {
		final char CHAR_MINUS = '-';
		final int MINUS_LENGTH = MINUS.length();
		final int PLUS_LENGTH = PLUS.length();
		if (strResult.toString().startsWith(PLUS)) {
		    strResult.delete(0, PLUS_LENGTH);
		} else {
		    strResult.delete(0, MINUS_LENGTH).insert(0, CHAR_MINUS);
		}

	    }
	    strResult.insert(0, RESULT_HEAD).append(RESULT_TAIL).append(result);
	    return errorLines;
	}
    }

    @Test
    public void testMainScenario() throws FileNotFoundException {
	StringBuilder resultFile1 = new StringBuilder();
	StringBuilder resultFile2 = new StringBuilder();
	StringBuilder resultFile3 = new StringBuilder();
	StringBuilder resultFile4 = new StringBuilder();
	StringBuilder resultFile5 = new StringBuilder();

	final String FILE1_NAME = "src/src1.csv";
	final String FILE2_NAME = "src/src2.csv";
	final String FILE3_NAME = "src/src3.csv";
	final String FILE4_NAME = "src/src4.csv";
	final String FILE5_NAME = "src/src5.csv";

	int errorLinesFile1 = getResult(FILE1_NAME, resultFile1);
	int errorLinesFile2 = getResult(FILE2_NAME, resultFile2);
	int errorLinesFile3 = getResult(FILE3_NAME, resultFile3);
	int errorLinesFile4 = getResult(FILE4_NAME, resultFile4);
	int errorLinesFile5 = getResult(FILE5_NAME, resultFile5);

	Assert.assertEquals(EXPECTED_ERROR_LINES_FILE1, errorLinesFile1);
	Assert.assertEquals(EXPECTED_ERROR_LINES_FILE2, errorLinesFile2);
	Assert.assertEquals(EXPECTED_ERROR_LINES_FILE3, errorLinesFile3);
	Assert.assertEquals(EXPECTED_ERROR_LINES_FILE4, errorLinesFile4);
	Assert.assertEquals(EXPECTED_ERROR_LINES_FILE5, errorLinesFile5);
	Assert.assertEquals(EXPECTED_ERROR_LINES_FILE5, errorLinesFile5);

	Assert.assertEquals(EXPECTED_RESULT_FILE1, resultFile1.toString());
	Assert.assertEquals(EXPECTED_RESULT_FILE2, resultFile2.toString());
	Assert.assertEquals(EXPECTED_RESULT_FILE3, resultFile3.toString());
	Assert.assertEquals(EXPECTED_RESULT_FILE4, resultFile4.toString());
	Assert.assertEquals(EXPECTED_RESULT_FILE5, resultFile5.toString());

    }

    @Test(expected = FileNotFoundException.class)
    public void testWrongCsvName() throws FileNotFoundException {
	getResult("", new StringBuilder(""));
    }
}
