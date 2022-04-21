package by.epam.lab.beans;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;


import static by.epam.lab.utils.Constants.*;

public class Result {

    private String login;
    private String test;
    private Date date;
    private int mark;

    public Result() {
    }

    public String getLogin() {
	return login;
    }

    public void setLogin(String login) {
	this.login = login;
    }

    public String getTest() {
	return test;
    }

    public void setTest(String test) {
	this.test = test;
    }

    public Date getDate() {
	return date;
    }
    
    public void setDate(String date) throws ParseException{
	this.date = DATE_FORMAT.parse(date);
    }
    
    public int getMark() {
	return mark;
    }

    public void setMark(String mark) {
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
