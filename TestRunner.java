import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

import org.junit.Assert;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class TestRunner {
    private static int getResult(String csvName, StringBuilder strResult) throws FileNotFoundException {
	try (Scanner sc = new Scanner(new FileReader(csvName))) {
	    final String CSV_SEPARATOR = ";";
	    final String BEFORE_SIGN = " ";
	    final String AFTER_SIGN = " ";
	    final String MINUS = BEFORE_SIGN + "-" + AFTER_SIGN;
	    final String PLUS = BEFORE_SIGN + "+" + AFTER_SIGN;
	    final String RESULT_HEAD = "result(";
	    final String RESULT_TAIL = ") = ";
	    double result = 0;
	    int errorLines = 0;
	    while (sc.hasNextLine()) {
		String[] elements = sc.nextLine().split(CSV_SEPARATOR);
		try {
		    int position = Integer.parseInt(elements[0].trim());
		    double elementValueOfFirstIElementIndexValue = Double.parseDouble(elements[position]);
		    result += elementValueOfFirstIElementIndexValue;
		    if(elementValueOfFirstIElementIndexValue >= 0) {
			strResult.append(PLUS).append(elementValueOfFirstIElementIndexValue);
		    }else {
			strResult.append(MINUS).append(Math.abs(elementValueOfFirstIElementIndexValue));
		    }
		} catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
		    errorLines++;
		}
	    }
	    if(strResult.length() > 0) {
		final char CHAR_MINUS = '-';
		if(strResult.toString().startsWith(PLUS)) {
		    strResult.delete(0, 3);
		}else {
		    strResult.delete(0, 3).insert(0,CHAR_MINUS);
		}
		
	    }
	    strResult.insert(0, RESULT_HEAD).append(RESULT_TAIL).append(result);
	    System.out.println(strResult);
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

	final int EXPECTED_ERROR_LINES_FILE1 = 3;
	final int EXPECTED_ERROR_LINES_FILE2 = 0;
	final int EXPECTED_ERROR_LINES_FILE3 = 0;
	final int EXPECTED_ERROR_LINES_FILE4 = 0;
	final int EXPECTED_ERROR_LINES_FILE5 = 1;

	Assert.assertEquals(EXPECTED_ERROR_LINES_FILE1, errorLinesFile1);
	Assert.assertEquals(EXPECTED_ERROR_LINES_FILE2, errorLinesFile2);
	Assert.assertEquals(EXPECTED_ERROR_LINES_FILE3, errorLinesFile3);
	Assert.assertEquals(EXPECTED_ERROR_LINES_FILE4, errorLinesFile4);
	Assert.assertEquals(EXPECTED_ERROR_LINES_FILE5, errorLinesFile5);
	Assert.assertEquals(EXPECTED_ERROR_LINES_FILE5, errorLinesFile5);

	final String EXPECTED_RESULT_FILE1 = "result(5.2 - 3.14 + 0.0) = 2.06";
	final String EXPECTED_RESULT_FILE2 = "result(-3.1 - 1.0) = -4.1";
	final String EXPECTED_RESULT_FILE3 = "result(0.75) = 0.75";
	final String EXPECTED_RESULT_FILE4 = "result(0.0) = 0.0";
	final String EXPECTED_RESULT_FILE5 = "result() = 0.0";

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
