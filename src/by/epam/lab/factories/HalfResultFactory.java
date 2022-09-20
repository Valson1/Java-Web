package by.epam.lab.factories;

import java.io.IOException;
import java.sql.Date;
import static by.epam.lab.utils.ConstantsUtility.*;
import org.xml.sax.SAXException;

import by.epam.lab.beans.HalfResult;
import by.epam.lab.beans.Result;
import by.epam.lab.interfaces.ResultDao;

public class HalfResultFactory extends ResultFactory{
    @Override
    public Result getResultFromFactory(String login, String test, Date date, int mark) {
        return new HalfResult(login, test, date, mark);
    }
    
    @Override
    public Result getResultFromFactory(String login, String test, String date, String mark) {
	return new HalfResult(login, test, date, mark);
    }
    
    @Override
    public ResultDao getResultDaoFromFactory(String sourceName,ResultFactory resultFactory) throws IOException, SAXException {
        return super.getResultDaoFromFactory(sourceName,resultFactory);
    }
    
    @Override
    public String meanMarkFormat(double mark) {
        return String.format(TWO_DIGITS_AFTER_POINT_FORMAT,mark / HALF_DIVIDOR);
    }
}
