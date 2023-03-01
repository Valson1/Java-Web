package by.epam.lab.services;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import static by.epam.lab.utils.ConstantsUtility.*;
import static by.epam.lab.utils.DatabaseConstants.*;


import by.epam.lab.beans.Result;
import by.epam.lab.exceptions.ConnectionException;
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
	} catch (IOException | ConnectionException e) {
	    System.err.println(e.getMessage());
	} finally {
	    try {
		DatabaseConnection.getInstance().close();
	    } catch (ConnectionException e) {
		System.err.println(e.getMessage());
	    }
	}
    }

    private static void loadResults(String sourceName, ResultFactory resultFactory)
	    throws ConnectionException,IOException {
	try (ResultDao reader = resultFactory.getResultDaoFromFactory(sourceName, resultFactory)) {
	    ResultsLoader.loadResults(reader);
	}

    }

    private static void printLastDayResults(ResultFactory resultFactory) {
	if (!results.isEmpty()) {
	    System.out.println(LAST_DAY_RESULTS_MESSAGE);
	    Result lastResult = results.get(results.size() - 1);
	    for (ListIterator<Result> listIterator = results.listIterator(results.size()); listIterator
		    .hasPrevious();) {
		Result result = listIterator.previous();
		if (result.compareTo(lastResult) == 0) {
		    System.out.println(result);
		} else {
		    break;
		}
	    }
	}
    }

    private static void printCurrentMonthTests(ResultFactory resultFactory){
	for (Result result : results) {
	    System.out.println(result);
	}
    }

    private static List<Result> getCurrentMonthResults(ResultFactory resultFactory) throws ConnectionException  {
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
	} catch (SQLException e) {
	    throw new ConnectionException(e.getMessage());
	}

    }

    private static void printAvgMarks(ResultFactory resultFactory) throws ConnectionException {
	try (Statement st = DatabaseConnection.getInstance().getConnection().createStatement();
		ResultSet rs = st.executeQuery(SELECT_MEAN_MARKS)) {
	    System.out.println(MEAN_MARKS_MESSAGE);
	    while (rs.next()) {
		System.out.println(rs.getString(LOGIN_COLUMN) + SEPARATOR
			+ resultFactory.meanMarkFormat(rs.getDouble(AVG_COLUMN)));
	    }
	} catch (SQLException e) {
	    throw new ConnectionException(e.getMessage());
	}
    }
}
