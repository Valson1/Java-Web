package by.epam.lab.services;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import static by.epam.lab.utils.ConstantsUtility.*;
import org.xml.sax.SAXException;

import by.epam.lab.beans.Result;
import by.epam.lab.factories.ResultFactory;
import by.epam.lab.interfaces.ResultDao;

public class RunnerLogic {

    private final static String SELECT_ASC_CURRENT_MONTH = "SELECT logins.name, tests.name, date, mark FROM results INNER JOIN logins ON results.loginId = logins.idLogin INNER JOIN tests ON results.testId = tests.idTest WHERE MONTH(date) = MONTH(CURRENT_DATE()) ORDER BY 3;";
    private final static String SELECT_MEAN_MARKS = "SELECT name, ROUND(AVG(mark),2) AS mark FROM results INNER JOIN logins ON results.loginId = logins.idLogin GROUP BY 1 ORDER BY 2 DESC;";

    private final static String DELETE_LOGINS = "DELETE FROM logins";
    private final static String DELETE_TESTS = "DELETE FROM tests";
    private final static String DELETE_RESULTS = "DELETE FROM results";

    private final static String FILE_NOT_FOUND_MESSAGE = "File is not found";
    private final static String SAX_PARSING_ERROR_MESSAGE = "SAX parsing error";
    private final static String DATABASE_SQL_ERROR_MESSAGE = "Error to load data into database";

    public final static String MEAN_MARKS_MESSAGE = "Mean mark of every student:";
    public final static String CURRENT_MONTH_RESULTS_MESSAGE = "Results of current month:";
    public final static String LAST_DAY_RESULTS_MESSAGE = "Last day results in current month:";

    public static void execute(ResultFactory resultFactory, String sourceName) {
	try (Connection cn = DatabaseConnection.getInstance().getConnection();
		Statement st = cn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
	    try (ResultDao resultDao = resultFactory.getResultDaoFromFactory(sourceName, resultFactory);) {
		st.executeUpdate(DELETE_RESULTS);
		st.executeUpdate(DELETE_LOGINS);
		st.executeUpdate(DELETE_TESTS);
		ResultsLoader.loadResults(resultDao, cn);
	    } catch (IOException e) {
		System.err.println(FILE_NOT_FOUND_MESSAGE + SEPARATOR + e);
	    } catch (SAXException e) {
		System.err.println(SAX_PARSING_ERROR_MESSAGE + SEPARATOR + e);
	    } catch (SQLException e) {
		System.err.println(DATABASE_SQL_ERROR_MESSAGE + SEPARATOR + e);
	    }
	    try (ResultSet rs = st.executeQuery(SELECT_MEAN_MARKS)) {
		System.out.println(MEAN_MARKS_MESSAGE);
		while (rs.next()) {
		    System.out.println(rs.getString(1) + SEPARATOR + resultFactory.meanMarkFormat(rs.getDouble(2)));
		}
	    }
	    
	    List<Result> results = new LinkedList<>();
	    try (ResultSet rs = st.executeQuery(SELECT_ASC_CURRENT_MONTH)) {
		System.out.println(CURRENT_MONTH_RESULTS_MESSAGE);
		while (rs.next()) {
		    Result result = resultFactory.getResultFromFactory(rs.getString(RESULTS_LOGIN_COLUMN),
			    rs.getString(RESULTS_TEST_COLUMN), rs.getDate(RESULTS_DATE_COLUMN),
			    rs.getInt(RESULTS_MARK_COLUMN));
		    results.add(result);
		    System.out.println(result);
		}
	    }
	    if (!results.isEmpty()) {
		System.out.println(LAST_DAY_RESULTS_MESSAGE);
		Result lastResult = results.get(results.size() - 1);
		for (ListIterator<Result> listIterator = results.listIterator(results.size()); listIterator
			.hasPrevious();) {
		    Result result = listIterator.previous();
		    if (result.compareTo(lastResult) == 0) {
			System.out.println(result);
		    }
		}
	    }
	} catch (SQLException e) {
	    e.printStackTrace();
	}
    }
}
