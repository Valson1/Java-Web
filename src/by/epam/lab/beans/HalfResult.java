package by.epam.lab.beans;

import java.sql.Date;
import static by.epam.lab.utils.ConstantsUtility.*;

public class HalfResult extends Result {
    
    private static final int HALF = 5;
    
    public HalfResult(String login, String test, String date, String mark) {
	super(login, test, Date.valueOf(date), (int) (Double.parseDouble(mark) * 2));
    }
    
    public HalfResult(String login, String test, Date date, int mark) {
	super(login,test,date,mark);
    }

    @Override
    protected String markFormat() {
	int mark = getMark();
	int beforePoint = mark / HALF_DIVIDOR;
	return String.valueOf(mark % HALF_DIVIDOR != 0 ? beforePoint + DECIMAL_POINT + HALF : beforePoint);
    }
}
