package by.epam.lab.services;

import static by.epam.lab.utils.ConstantsUtility.*;
import static by.epam.lab.utils.DatabaseConstants.*;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import by.epam.lab.beans.Result;
import by.epam.lab.interfaces.ResultDao;

public class ResultsLoader {

    public static void loadResults(ResultDao reader) throws SQLException {
	Connection connection = DatabaseConnection.getInstance().getConnection();
	try (PreparedStatement psSelectLogin = connection.prepareStatement(SELECT_LOGINS_NAME);
		PreparedStatement psSelectTest = connection.prepareStatement(SELECT_TESTS_NAME);
		PreparedStatement psInsertLogin = connection.prepareStatement(INSERT_LOGINS);
		PreparedStatement psInsertTest = connection.prepareStatement(INSERT_TESTS);
		PreparedStatement psInsertResults = connection.prepareStatement(INSERT_RESULTS);
		Statement st = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
			ResultSet.CONCUR_UPDATABLE)) {
	    st.executeUpdate(DELETE_TABLES);
	    while (reader.hasResult()) {
		Result result = reader.nextResult();
		String login = result.getLogin();
		String test = result.getTest();
		Date date = result.getDate();
		int mark = result.getMark();
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
	int id = 0;
	selectStatement.setString(NAME_COLUMN, element);
	try (ResultSet rs = selectStatement.executeQuery()) {
	    if (!rs.next()) {
		insertStatement.setString(NAME_COLUMN, element);
		id = insertStatement.executeUpdate();
	    } else {
		id = rs.getInt(NAME_COLUMN);
	    }
	    return id;
	}
    }
}
