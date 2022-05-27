package by.epam.lab.utils;

import java.text.SimpleDateFormat;

public class ConstantsUtility {
    public static final String SEPARATOR = ";";
    	
    public static final int TESTS_NAME_COLUMN = 1;
    public static final int LOGINS_NAME_COLUMN = 1;
    
    public static final int RESULTS_LOGIN_COLUMN = 1;
    public static final int RESULTS_TEST_COLUMN = 2;
    public static final int RESULTS_DATE_COLUMN = 3;
    public static final int RESULTS_MARK_COLUMN = 4;
    
    public static final int LOGIN_LINE_ELEMENT = 0;
    public static final int TEST_LINE_ELEMENT = 1;
    public static final int DATE_LINE_ELEMENT = 2;
    public static final int MARK_LINE_ELEMENT = 3;

    public final static String DB_URL = "jdbc:mysql://localhost:3306/results";
    public final static String DB_PASSWORD = "Qwerty147258369";
    public final static String DB_USER = "root";
    
    public final static String INSERT_LOGINS = "INSERT INTO logins(name) values (?);";
    public final static String INSERT_TESTS = "INSERT INTO tests(name) values(?)";
    public final static String INSERT_RESULTS = "INSERT INTO results(loginId,testId,date,mark) values(?,?,?,?)";
    public final static String DELETE_LOGINS = "DELETE FROM logins";
    public final static String DELETE_TESTS = "DELETE FROM tests";
    public final static String DELETE_RESULTS = "DELETE FROM results";
    public final static String SELECT_MEAN_MARKS = "SELECT name, CAST(AVG(mark) as decimal(5,2)) AS mark FROM results INNER JOIN logins ON results.loginId = logins.idLogin GROUP BY 1 ORDER BY 2 DESC;";
    public final static String SELECT_ID= "SELECT idLogin,idTest FROM logins,tests WHERE logins.name = ? AND tests.name = ?";
    public final static String SELECT_ASC_DATE = "SELECT logins.name, tests.name, date, mark FROM results INNER JOIN logins ON results.loginId = logins.idLogin INNER JOIN tests ON results.testId = tests.idTest WHERE date BETWEEN '2022-04-30' AND '2022-05-31' ORDER BY 3;";

    public final static String FILE_NOT_FOUND_MESSAGE = "File is not found";
    public final static String SAX_PARSING_ERROR_MESSAGE = "SAX parsing error";
    public final static String DATABASE_SQL_ERROR_MESSAGE = "Error to load data into database";
    public final static String SQL_ERROR_MESSAGE = "Query error";
    
    public final static String MEAN_MARKS_MESSAGE = "Mean mark of every student:";
    public final static String CURRENT_MONTH_RESULTS_MESSAGE = "Mean mark of every student:";
    public final static String LAST_DAY_RESULTS_MESSAGE = "Mean mark of every student:";

    public static final String TWO_DIGITS_DECIMAL_FORMAT = "%d.%02d";
    public static final String ONE_DIGIT_DECIMAL_FORMAT = "%d.%01d";
    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    public static final int MARK_DIVIDER_TEN = 10;
    public static final int MARK_DIVIDER_HUNDRED = 100;
}
