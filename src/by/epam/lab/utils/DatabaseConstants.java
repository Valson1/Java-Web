package by.epam.lab.utils;

public class DatabaseConstants {
    public final static String DB_URL = "jdbc:mysql://localhost:3306/results";
    public final static String DB_PASSWORD = "Qwerty147258369";
    public final static String DB_USER = "root";

    public final static String SELECT_LOGINS_NAME = "SELECT idLogin FROM logins WHERE name = ?";
    public final static String SELECT_TESTS_NAME = "SELECT idTest FROM tests WHERE name = ?";

    public final static String INSERT_LOGINS = "INSERT INTO logins(name) values (?);";
    public final static String INSERT_TESTS = "INSERT INTO tests(name) values(?);";
    public final static String INSERT_RESULTS = "INSERT INTO results(loginId,testId,date,mark) values(?,?,?,?)";

    public final static String SELECT_ASC_CURRENT_MONTH = "SELECT logins.name, tests.name, date, mark FROM results INNER JOIN logins ON results.loginId = logins.idLogin INNER JOIN tests ON results.testId = tests.idTest WHERE MONTH(date) = MONTH(CURRENT_DATE()) AND YEAR(date) = YEAR(NOW()) ORDER BY 3;";
    public final static String SELECT_MEAN_MARKS = "SELECT name, ROUND(AVG(mark),2) AS mark FROM results INNER JOIN logins ON results.loginId = logins.idLogin GROUP BY 1 ORDER BY 2 DESC;";

    public final static String DELETE_TABLES = "TRUNCATE TABLE results";
    
    public final static String FILE_NOT_FOUND_MESSAGE = "File is not found";
    public final static String SAX_PARSING_ERROR_MESSAGE = "SAX parsing error";
    public final static String DATABASE_SQL_ERROR_MESSAGE = "Error to load data into database";

    public final static String MEAN_MARKS_MESSAGE = "Mean mark of every student:";
    public final static String CURRENT_MONTH_RESULTS_MESSAGE = "Results of current month:";
    public final static String LAST_DAY_RESULTS_MESSAGE = "Last day results in current month:";
}
