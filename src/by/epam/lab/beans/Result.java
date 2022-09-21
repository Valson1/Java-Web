package by.epam.lab.beans;

import static by.epam.lab.utils.ConstantsUtility.*;

import java.sql.Date;

public class Result implements Comparable<Result> {

    private final String login;
    private final String test;
    private final Date date;
    private final int mark;

    public Result(String login, String test, String date, String mark) {
	this(login, test, Date.valueOf(date), Integer.parseInt(mark));
    }

    public Result(String login, String test, Date date, int mark) {
	this.login = login;
	this.test = test;
	this.date = date;
	this.mark = mark;
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

    public int getMark() {
	return mark;
    }

    @Override
    public String toString() {
	return login + SEPARATOR + test + SEPARATOR + stringDate() + SEPARATOR + markFormat();
    }

    protected String markFormat() {
	return String.valueOf(mark);
    }

    private String stringDate() {
	return DATE_FORMAT.format(date);
    }

    @Override
    public int compareTo(Result o) {
	return date.compareTo(o.date);
    }

}
