package by.epam.lab.beans;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import by.epam.lab.interfaces.MarkFormat;
import by.epam.lab.services.factories.MarkKind;

import static by.epam.lab.utils.ConstantsUtility.*;

import java.sql.Date;

public class Result implements Comparable<Result> {
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    private final String login;
    private final String test;
    private final Date date;
    private final MarkFormat mark;

    public static MarkKind kindMark;

    public Result(String login, String test, String date, String mark) {
	this(login, test, Date.valueOf(date), kindMark.getMarkFromFactory(mark));
    }

    public Result(String login, String test, Date date, MarkFormat mark) {
	this.login = login;
	this.test = test;
	this.date = date;
	this.mark = mark;
    }

    public Result(ResultSet rs) throws SQLException {
	this(rs.getString(RESULTS_LOGIN_COLUMN), rs.getString(RESULTS_TEST_COLUMN), rs.getDate(RESULTS_DATE_COLUMN),
		kindMark.getMarkFromFactory(rs.getInt(RESULTS_MARK_COLUMN)));
    }

    public String getLogin() {
	return login;
    }

    public String getTest() {
	return test;
    }

    public Date getDate() {
	return date;
    }

    public MarkFormat getMark() {
	return mark;
    }

    @Override
    public String toString() {
	return login + SEPARATOR + test + SEPARATOR + DATE_FORMAT.format(date) + SEPARATOR + mark;
    }

    @Override
    public int compareTo(Result o) {
	return date.compareTo(o.date);
    }

}
