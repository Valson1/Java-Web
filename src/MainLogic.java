import static by.epam.lab.utils.ConstantsUtility.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import by.epam.lab.beans.Result;
import by.epam.lab.services.MarkKind;

public class MainLogic {
    public static void logic(String fileName, MarkKind kindMark) {
	try (Connection cn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
		PreparedStatement loginsStatement = cn.prepareStatement(INSERT_LOGINS, Statement.RETURN_GENERATED_KEYS);
		PreparedStatement testsStatement = cn.prepareStatement(INSERT_TESTS, Statement.RETURN_GENERATED_KEYS);
		PreparedStatement resultsStatement = cn.prepareStatement(INSERT_RESULTS,
			Statement.RETURN_GENERATED_KEYS);
		PreparedStatement selectStatement = cn.prepareStatement(SELECT_ID);
		Statement st = cn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);) {
	    Result.kindMark = kindMark;
	    // delete everything from tables
	    st.executeUpdate(DELETE_RESULTS);
	    st.executeUpdate(DELETE_LOGINS);
	    st.executeUpdate(DELETE_TESTS);
	    try (Scanner sc = new Scanner(new FileReader(fileName))) {
		// two sets for unique values
		Set<String> loginsSet = new HashSet<>();
		Set<String> testsSet = new HashSet<>();
		cn.setAutoCommit(false);
		while (sc.hasNextLine()) {
		    String[] elements = sc.nextLine().split(SEPARATOR);
		    // insert names into logins
		    if (loginsSet.add(elements[LOGIN_LINE_ELEMENT])) {
			loginsStatement.setString(LOGINS_NAME_COLUMN, elements[LOGIN_LINE_ELEMENT]);
			loginsStatement.executeUpdate();
		    }
		    // insert names into tests
		    if (testsSet.add(elements[TEST_LINE_ELEMENT])) {
			testsStatement.setString(TESTS_NAME_COLUMN, elements[TEST_LINE_ELEMENT]);
			testsStatement.executeUpdate();
		    }
		    // insert id into results
		    selectStatement.setString(RESULTS_LOGIN_COLUMN, elements[LOGIN_LINE_ELEMENT]);
		    selectStatement.setString(RESULTS_TEST_COLUMN, elements[TEST_LINE_ELEMENT]);
		    try (ResultSet loginsTestsResultSet = selectStatement.executeQuery()) {
			if (loginsTestsResultSet.next()) {
			    resultsStatement.setInt(RESULTS_LOGIN_COLUMN, loginsTestsResultSet.getInt(RESULTS_LOGIN_COLUMN));
			    resultsStatement.setInt(RESULTS_TEST_COLUMN, loginsTestsResultSet.getInt(RESULTS_TEST_COLUMN));
			}
		    }
		    // insert date and mark into results
		    resultsStatement.setDate(RESULTS_DATE_COLUMN, Date.valueOf(elements[DATE_LINE_ELEMENT]));
		    resultsStatement.setInt(RESULTS_MARK_COLUMN,
			    Result.kindMark.getMarkFromFactory(elements[MARK_LINE_ELEMENT]).getMark());
		    resultsStatement.executeUpdate();
		    cn.commit();

		}
	    } catch (FileNotFoundException e) {
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
