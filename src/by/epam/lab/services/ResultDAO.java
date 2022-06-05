package by.epam.lab.services;

import static by.epam.lab.utils.ConstantsUtility.*;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import by.epam.lab.beans.Result;
import by.epam.lab.services.factories.MarkKind;

public class ResultDAO {
    private static final int NAME_COLUMN = 1;

    public void insertDataChainedTables(PreparedStatement selectStatement, PreparedStatement insertStatement,
	    String value) throws SQLException {
	selectStatement.setString(NAME_COLUMN, value);
	try (ResultSet rs = selectStatement.executeQuery()) {
	    if (!rs.next()) {
		insertStatement.setString(NAME_COLUMN, value);
		insertStatement.executeUpdate();
	    }
	}
    }

    public void insertResultingTable(PreparedStatement selectStatement, PreparedStatement insertStatement,
	    String[] values, MarkKind kindMark) throws SQLException {
	selectStatement.setString(RESULTS_LOGIN_COLUMN, values[LOGIN_LINE_ELEMENT]);
	selectStatement.setString(RESULTS_TEST_COLUMN, values[TEST_LINE_ELEMENT]);
	try (ResultSet loginsTestsResultSet = selectStatement.executeQuery()) {
	    if (loginsTestsResultSet.next()) {
		insertStatement.setInt(RESULTS_LOGIN_COLUMN, loginsTestsResultSet.getInt(RESULTS_LOGIN_COLUMN));
		insertStatement.setInt(RESULTS_TEST_COLUMN, loginsTestsResultSet.getInt(RESULTS_TEST_COLUMN));
	    }
	}
	insertStatement.setDate(RESULTS_DATE_COLUMN, Date.valueOf(values[DATE_LINE_ELEMENT]));
	insertStatement.setInt(RESULTS_MARK_COLUMN, kindMark.getMarkFromFactory(values[MARK_LINE_ELEMENT]).getMark());
	insertStatement.executeUpdate();
    }

    public void insertResultingTable(PreparedStatement selectStatement, PreparedStatement insertStatement,
	    Result result, MarkKind kindMark) throws SQLException {
	selectStatement.setString(RESULTS_LOGIN_COLUMN, result.getLogin());
	selectStatement.setString(RESULTS_TEST_COLUMN, result.getTest());
	try (ResultSet loginsTestsResultSet = selectStatement.executeQuery()) {
	    if (loginsTestsResultSet.next()) {
		insertStatement.setInt(RESULTS_LOGIN_COLUMN, loginsTestsResultSet.getInt(RESULTS_LOGIN_COLUMN));
		insertStatement.setInt(RESULTS_TEST_COLUMN, loginsTestsResultSet.getInt(RESULTS_TEST_COLUMN));
	    }
	}
	insertStatement.setDate(RESULTS_DATE_COLUMN, result.getDate());
	insertStatement.setInt(RESULTS_MARK_COLUMN, result.getMark().getMark());
	insertStatement.executeUpdate();
    }
}
