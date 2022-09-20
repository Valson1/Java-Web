package by.epam.lab.factories;

import java.io.IOException;
import java.sql.Date;

import static by.epam.lab.utils.ConstantsUtility.*;

import org.xml.sax.SAXException;

import by.epam.lab.beans.Result;
import by.epam.lab.implementation.ResultImplCsv;
import by.epam.lab.interfaces.ResultDao;

public class ResultFactory {
    public Result getResultFromFactory(String login, String test, Date date, int mark) {
	return new Result(login, test, date, mark);
    }
    
    public Result getResultFromFactory(String login, String test, String date, String mark) {
	return new Result(login, test, date, mark);
    }
    
    public ResultDao getResultDaoFromFactory(String sourceName,ResultFactory resultFactory) throws IOException, SAXException{
	return new ResultImplCsv(sourceName,resultFactory);
    }
    public String meanMarkFormat(double mark) {
	return String.format(TWO_DIGITS_AFTER_POINT_FORMAT,mark);
    }
}
