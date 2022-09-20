package by.epam.lab.beans;

import static by.epam.lab.utils.ConstantsUtility.*;

import java.sql.Date;

public class DecimalResult extends Result {
    
    
    
    public DecimalResult(String login,String test, String date, String mark) {
	super(login,test,Date.valueOf(date),(int)(Double.parseDouble(mark) * 10));
    }
    
    public DecimalResult(String login, String test, Date date, int mark) {
	super(login, test, date, mark);
    }
    
    @Override
    protected String markFormat() {
	int mark = getMark();
	return mark / DECIMAL_DIVIDOR + DECIMAL_POINT + mark % DECIMAL_DIVIDOR;
    }
}
