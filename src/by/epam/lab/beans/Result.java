package by.epam.lab.beans;

import java.util.Date;

import static by.epam.lab.utils.Constants.*;

public class Result {

    private final String login;
    private final String test;
    private final Date date;
    private final int mark;

    public Result(String login, String test, String date, String mark) {
	this.login = login;
	this.test = test;
	this.date = java.sql.Date.valueOf(date);
	this.mark = (int) (Double.parseDouble(mark) * DIVISOR);
    }

    private String markFormat() {
	return String.format(STRING_DATE_FORMAT, mark / DIVISOR, mark % DIVISOR);
    }

    private String dateFormat() {
	return DATE_FORMAT.format(date);
    }

    @Override
    public String toString() {
	return login + SEPARATOR + test + SEPARATOR + dateFormat() + SEPARATOR + markFormat();
    }

}
