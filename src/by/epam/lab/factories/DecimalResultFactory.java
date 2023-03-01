package by.epam.lab.factories;

import java.sql.Date;

import static by.epam.lab.utils.ConstantsUtility.*;

import by.epam.lab.beans.DecimalResult;
import by.epam.lab.beans.Result;
import by.epam.lab.exceptions.SourceException;
import by.epam.lab.implementation.ResultImplXml;
import by.epam.lab.interfaces.ResultDao;

public class DecimalResultFactory extends ResultFactory{
    @Override
    public Result getResultFromFactory(String login, String test, Date date, int mark) {
        return new DecimalResult(login, test, date, mark);
    }
    
    @Override
    public Result getResultFromFactory(String login, String test, String date, String mark) {
        return new DecimalResult(login,test,date,mark);
    }
    
    
    public ResultDao getResultDaoFromFactory(String sourceName,ResultFactory resultFactory) throws SourceException{
        return new ResultImplXml(sourceName);
    }
    
    @Override
    public String meanMarkFormat(double mark) {
        return String.format(TWO_DIGITS_AFTER_POINT_FORMAT,mark / DECIMAL_DIVIDOR); 
    }
}
