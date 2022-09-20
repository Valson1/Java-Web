package by.epam.lab.services;

import static by.epam.lab.utils.ConstantsUtility.*;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import by.epam.lab.beans.Result;
import by.epam.lab.interfaces.ResultDao;

public class ResultsLoader {

    private final static String SELECT_LOGINS_NAME = "SELECT idLogin FROM logins WHERE name = ?";
    private final static String SELECT_TESTS_NAME = "SELECT idTest FROM tests WHERE name = ?";

    private final static String INSERT_LOGINS = "INSERT INTO logins(name) values (?);";
    private final static String INSERT_TESTS = "INSERT INTO tests(name) values(?);";
    private final static String INSERT_RESULTS = "INSERT INTO results(loginId,testId,date,mark) values(?,?,?,?)";
    
    


    public static void loadResults(ResultDao reader, Connection connection) throws SQLException {
	try (PreparedStatement psSelectLogin = connection.prepareStatement(SELECT_LOGINS_NAME);
		PreparedStatement psSelectTest = connection.prepareStatement(SELECT_TESTS_NAME);
		PreparedStatement psInsertLogin = connection.prepareStatement(INSERT_LOGINS);
		PreparedStatement psInsertTest = connection.prepareStatement(INSERT_TESTS);
		PreparedStatement psInsertResults = connection.prepareStatement(INSERT_RESULTS);) {
	    while (reader.hasResult()) {
		Result result = reader.nextResult();
		String login = result.getLogin();
		String test = result.getTest();
		Date date = result.getDate();
		int mark = result.getMark();
		System.out.println(result);
		int idLogin = getId(login, psSelectLogin, psInsertLogin);
		int idTest = getId(test, psSelectTest, psInsertTest);
		psInsertResults.setInt(RESULTS_LOGIN_COLUMN, idLogin);
		psInsertResults.setInt(RESULTS_TEST_COLUMN, idTest);
		psInsertResults.setDate(RESULTS_DATE_COLUMN, date);
		psInsertResults.setInt(RESULTS_MARK_COLUMN, mark);
		psInsertResults.executeUpdate();
	    }
	}
    }

    private static int getId(String element, PreparedStatement selectStatement, PreparedStatement insertStatement)
	    throws SQLException {
	selectStatement.setString(1, element);
	try (ResultSet rs = selectStatement.executeQuery()) {
	    if (!rs.next()) {
		insertStatement.setString(1, element);
		insertStatement.executeUpdate();
	    }
	}
	try (ResultSet rs = selectStatement.executeQuery()) {
	    rs.next();
	    return rs.getInt(1);
	}
    }
}
