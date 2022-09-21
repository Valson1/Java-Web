package by.epam.lab.factories;

import java.sql.Date;
import java.text.ParseException;

import static by.epam.lab.utils.ConstantsUtility.*;

import by.epam.lab.beans.Result;
import by.epam.lab.exceptions.SourceException;
import by.epam.lab.implementation.ResultImplCsv;
import by.epam.lab.interfaces.ResultDao;

public class ResultFactory {
    public Result getResultFromFactory(String login, String test, Date date, int mark) {
	return new Result(login, test, date, mark);
    }
    
    public Result getResultFromFactory(String login, String test, String date, String mark) {
	return new Result(login, test, date, mark);
    }
    
    public ResultDao getResultDaoFromFactory(String sourceName,ResultFactory resultFactory) throws SourceException{
	return new ResultImplCsv(sourceName,resultFactory);
    }
    public String meanMarkFormat(double mark) {
	return String.format(TWO_DIGITS_AFTER_POINT_FORMAT,mark);
    }
}
