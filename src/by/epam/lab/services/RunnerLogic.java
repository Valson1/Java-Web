package by.epam.lab.services;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import static by.epam.lab.utils.ConstantsUtility.*;
import static by.epam.lab.utils.DatabaseConstants.*;
import org.xml.sax.SAXException;

import by.epam.lab.beans.Result;
import by.epam.lab.factories.ResultFactory;
import by.epam.lab.interfaces.ResultDao;

public class RunnerLogic {

    private static List<Result> results;

    public static void execute(ResultFactory resultFactory, String sourceName) {
	try {
	    loadResults(sourceName, resultFactory);
	    printAvgMarks(resultFactory);
	    results = getCurrentMonthResults(resultFactory);
	    printCurrentMonthTests(resultFactory);
	    printLastDayResults(resultFactory);
	} catch (IOException e) {
	    System.err.println(FILE_NOT_FOUND_MESSAGE + SEPARATOR + e);
	} catch (SAXException e) {
	    System.err.println(SAX_PARSING_ERROR_MESSAGE + SEPARATOR + e);
	} catch (SQLException e) {
	    System.err.println(DATABASE_SQL_ERROR_MESSAGE + SEPARATOR + e);
	    e.printStackTrace();
	}finally {
	    DatabaseConnection.getInstance().close();
	}
    }

    private static void loadResults(String sourceName, ResultFactory resultFactory)
	    throws IOException, SAXException, SQLException {
	try (ResultDao reader = resultFactory.getResultDaoFromFactory(sourceName, resultFactory)) {
	    ResultsLoader.loadResults(reader);
	}

    }

    private static void printLastDayResults(ResultFactory resultFactory) throws SQLException {
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
    }

    private static void printCurrentMonthTests(ResultFactory resultFactory) throws SQLException {
	for (Result result : getCurrentMonthResults(resultFactory)) {
	    System.out.println(result);
	}
    }

    private static List<Result> getCurrentMonthResults(ResultFactory resultFactory) throws SQLException {
	List<Result> results = new LinkedList<>();
	try (Statement st = DatabaseConnection.getInstance().getConnection().createStatement();
		ResultSet rs = st.executeQuery(SELECT_ASC_CURRENT_MONTH)) {
	    System.out.println(CURRENT_MONTH_RESULTS_MESSAGE);
	    while (rs.next()) {
		Result result = resultFactory.getResultFromFactory(rs.getString(RESULTS_LOGIN_COLUMN),
			rs.getString(RESULTS_TEST_COLUMN), rs.getDate(RESULTS_DATE_COLUMN),
			rs.getInt(RESULTS_MARK_COLUMN));
		results.add(result);
	    }
	    return results;
	}
    }

    private static void printAvgMarks(ResultFactory resultFactory) throws SQLException {
	try (Statement st = DatabaseConnection.getInstance().getConnection().createStatement();
		ResultSet rs = st.executeQuery(SELECT_MEAN_MARKS)) {
	    System.out.println(MEAN_MARKS_MESSAGE);
	    while (rs.next()) {
		System.out.println(rs.getString(1) + SEPARATOR + resultFactory.meanMarkFormat(rs.getDouble(2)));
	    }
	}
    }
}
