package by.epam.lab.utils;

import java.text.SimpleDateFormat;

public class ConstantsUtility {
    public static final String SEPARATOR = ";";

    public final static int RESULTS_LOGIN_COLUMN = 1;
    public final static int RESULTS_TEST_COLUMN = 2;
    public final static int RESULTS_DATE_COLUMN = 3;
    public final static int RESULTS_MARK_COLUMN = 4;

    public final static int LOGIN_LINE_ELEMENT = 0;
    public final static int TEST_LINE_ELEMENT = 1;
    public final static int DATE_LINE_ELEMENT = 2;
    public final static int MARK_LINE_ELEMENT = 3;

    public static final int TEST_ATTRIBUTE = 0;
    public static final int DATE_ATTRIBUTE = 1;
    public static final int MARK_ATTRIBUTE = 2;

    public static final int NAME_COLUMN = 1;
    
    public static final int LOGIN_COLUMN = 1;
    public static final int AVG_COLUMN = 2;
    
    public final static String DECIMAL_POINT = ".";

    public final static String TWO_DIGITS_AFTER_POINT_FORMAT = "%.2f";

    public final static int DECIMAL_DIVIDOR = 10;
    public final static int HALF_DIVIDOR = 2;
    public final static int HALF = 5;

    public final static String RESULT_FILE_NAME = "src/data/results1.txt";
    public final static String DECIMAL_FILE_NAME = "src/data/results2.xml";
    public final static String HALF_FILE_NAME = "src/data/results3.txt";

    public final static SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    
    public final static String WRONG_FILE_NAME = "Wrong source name: ";
    public final static String SAX_PARSING_ERROR = "Sax parsing error: ";
    public final static String ERROR_DATA_LOAD= "Error load data into database";
    
}
