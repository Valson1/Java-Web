import static by.epam.lab.utils.ConstantsUtility.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import org.xml.sax.SAXException;

import by.epam.lab.beans.Result;
import by.epam.lab.services.factories.LoaderFactory;
import by.epam.lab.services.factories.MarkKind;

public class MainLogic {
    private final static String INSERT_LOGINS = "INSERT INTO logins(name) values (?);";
    private final static String INSERT_TESTS = "INSERT INTO tests(name) values(?);";
    private final static String INSERT_RESULTS = "INSERT INTO results(loginId,testId,date,mark) values(?,?,?,?)";
    
    private final static String DELETE_LOGINS = "DELETE FROM logins";
    private final static String DELETE_TESTS = "DELETE FROM tests";
    private final static String DELETE_RESULTS = "DELETE FROM results";
    
    private final static String SELECT_LOGINS_NAME = "SELECT name FROM logins WHERE name = ?";
    private final static String SELECT_TESTS_NAME = "SELECT name FROM tests WHERE name = ?";
    private final static String SELECT_MEAN_MARKS = "SELECT name, CAST(AVG(mark) as decimal(5,2)) AS mark FROM results INNER JOIN logins ON results.loginId = logins.idLogin GROUP BY 1 ORDER BY 2 DESC;";
    private final static String SELECT_ID= "SELECT idLogin,idTest FROM logins,tests WHERE logins.name = ? AND tests.name = ?";
    private final static String SELECT_ASC_CURRENT_MONTH = "SELECT logins.name, tests.name, date, mark FROM results INNER JOIN logins ON results.loginId = logins.idLogin INNER JOIN tests ON results.testId = tests.idTest WHERE date BETWEEN '2022-04-30' AND '2022-05-31' ORDER BY 3;";
    
    private final static String FILE_NOT_FOUND_MESSAGE = "File is not found";
    private final static String SAX_PARSING_ERROR_MESSAGE = "SAX parsing error";
    private final static String DATABASE_SQL_ERROR_MESSAGE = "Error to load data into database";
    private final static String SQL_ERROR_MESSAGE = "Query error";
    
    private final static String MEAN_MARKS_MESSAGE = "Mean mark of every student:";
    private final static String CURRENT_MONTH_RESULTS_MESSAGE = "Mean mark of every student:";
    private final static String LAST_DAY_RESULTS_MESSAGE = "Mean mark of every student:";
    
    private final static String DB_URL = "jdbc:mysql://localhost:3306/results";
    private final static String DB_PASSWORD = "Qwerty147258369";
    private final static String DB_USER = "root";
    
    public static void logic(String fileName, MarkKind kindMark) {
	try (Connection cn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
		PreparedStatement loginsStatement = cn.prepareStatement(INSERT_LOGINS);
		PreparedStatement testsStatement = cn.prepareStatement(INSERT_TESTS);
		PreparedStatement resultsStatement = cn.prepareStatement(INSERT_RESULTS);
		PreparedStatement selectLoginsStatement = cn.prepareStatement(SELECT_LOGINS_NAME);
		PreparedStatement selectTestsStatement = cn.prepareStatement(SELECT_TESTS_NAME);
		PreparedStatement selectStatement = cn.prepareStatement(SELECT_ID);
		Statement st = cn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);) {
	    Result.kindMark = kindMark;
	    // delete everything from tables
	    st.executeUpdate(DELETE_RESULTS);
	    st.executeUpdate(DELETE_LOGINS);
	    st.executeUpdate(DELETE_TESTS);
	    try {
		cn.setAutoCommit(false);
		LoaderFactory.getLoaderFromFactory(fileName).loadDatabase(fileName, loginsStatement, testsStatement, resultsStatement, selectLoginsStatement, selectTestsStatement, selectStatement, kindMark);
		cn.commit();
	    } catch (SAXException e) {
		System.err.println(SAX_PARSING_ERROR_MESSAGE);
	    } catch (IOException e) {
		System.err.println(FILE_NOT_FOUND_MESSAGE);
	    }
	    // Print a mean value of marks (2 digits after a decimal point) on every student
	    // in descending order by a mean value
	    try (ResultSet marksStatement = st.executeQuery(SELECT_MEAN_MARKS)) {
		System.out.println(MEAN_MARKS_MESSAGE);
		while (marksStatement.next()) {
		    System.out.println(marksStatement.getString(1) + SEPARATOR
			    + Result.kindMark.getMarkFromFactory(marksStatement.getDouble(2)).meanMarkFormat());
		}
	    } catch (SQLException e) {
		System.err.println(SQL_ERROR_MESSAGE);
	    }
	    // Create a LinkedList implementation of tests results for the current month
	    // sorting by a date ascending and print it.
	    try (ResultSet dateStatement = st.executeQuery(SELECT_ASC_CURRENT_MONTH)) {
		System.out.println(CURRENT_MONTH_RESULTS_MESSAGE);
		List<Result> results = new LinkedList<>();
		while (dateStatement.next()) {
		    Result result = new Result(dateStatement);
		    results.add(result);
		    System.out.println(result);
		}
		// Print tests results in the latest day of the current month (without SQL
		// request).
		System.out.println(LAST_DAY_RESULTS_MESSAGE);
		Result lastResult = results.get(results.size() - 1);
		for (Result result : results) {
		    if (lastResult.compareTo(result) == 0) {
			System.out.println(result);
		    }
		}
	    }
	} catch (SQLException e) {
	    System.err.println(DATABASE_SQL_ERROR_MESSAGE);
	}
    }
}
