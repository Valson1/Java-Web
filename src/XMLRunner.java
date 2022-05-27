import static by.epam.lab.utils.ConstantsUtility.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import by.epam.lab.beans.Result;
import by.epam.lab.services.MarkKind;
import by.epam.lab.services.ResultHandler;

public class XMLRunner {
    private final static String FILE_NAME = "src/data/results2.xml";

    public static void main(String[] args) {
	try (Connection cn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
		PreparedStatement loginsStatement = cn.prepareStatement(INSERT_LOGINS);
		PreparedStatement testsStatement = cn.prepareStatement(INSERT_TESTS);
		PreparedStatement resultsStatement = cn.prepareStatement(INSERT_RESULTS);
		PreparedStatement selectStatement = cn.prepareStatement(SELECT_ID);
		Statement st = cn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);) {
	    Result.kindMark = MarkKind.DECIMAL_MARK;
	    // delete everything from tables
	    st.executeUpdate(DELETE_RESULTS);
	    st.executeUpdate(DELETE_LOGINS);
	    st.executeUpdate(DELETE_TESTS);
	    try {
		// SAX parsing
		XMLReader reader = XMLReaderFactory.createXMLReader();
		ResultHandler handler = new ResultHandler();
		reader.setContentHandler(handler);
		reader.parse(FILE_NAME);
		// two sets for unique values
		Set<String> loginsSet = new HashSet<>();
		Set<String> testsSet = new HashSet<>();
		cn.setAutoCommit(false);

		for (Result result : handler.getResults()) {
		    String login = result.getLogin();
		    String test = result.getTest();
		    // insert names into logins
		    if (loginsSet.add(login)) {
			loginsStatement.setString(LOGINS_NAME_COLUMN, login);
			loginsStatement.executeUpdate();
		    }
		    // insert names into tests
		    if (testsSet.add(test)) {
			testsStatement.setString(TESTS_NAME_COLUMN, test);
			testsStatement.executeUpdate();
		    }

		    // insert id into results
		    selectStatement.setString(RESULTS_LOGIN_COLUMN, login);
		    selectStatement.setString(RESULTS_TEST_COLUMN, test);
		    try (ResultSet loginsTestsResultSet = selectStatement.executeQuery()) {
			if (loginsTestsResultSet.next()) {
			    resultsStatement.setInt(RESULTS_LOGIN_COLUMN, loginsTestsResultSet.getInt(RESULTS_LOGIN_COLUMN));
			    resultsStatement.setInt(RESULTS_TEST_COLUMN, loginsTestsResultSet.getInt(RESULTS_TEST_COLUMN));
			}
		    }
		    // insert date and mark into results
		    resultsStatement.setDate(RESULTS_DATE_COLUMN, result.getDate());
		    resultsStatement.setInt(RESULTS_MARK_COLUMN, result.getMark().getMark());
		    resultsStatement.executeUpdate();
		    cn.commit();
		}
	    } catch (IOException e) {
		System.err.println(FILE_NOT_FOUND_MESSAGE);
	    } catch (SAXException e) {
		System.err.println(SAX_PARSING_ERROR_MESSAGE);
	    }
	    // Print a mean value of marks (2 digits after a decimal point) on every student
	    // in descending order by a mean value.
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
	    try (ResultSet dateStatement = st.executeQuery(SELECT_ASC_DATE)) {
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
