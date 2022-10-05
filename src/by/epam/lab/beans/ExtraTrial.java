package by.epam.lab.beans;

import static by.epam.lab.utils.ConstantUtils.*;

public class ExtraTrial extends Trial {

    private final static int EXTRA_TRIAL_PASS_MARK = 85;

    private int mark3;

    public ExtraTrial(String account, int mark1, int mark2, int mark3) {
	super(account, mark1, mark2);
	if (mark3 < MIN_CONDITION_MARK || mark3 > MAX_CONDITION_MARK) {
	    throw new IllegalArgumentException(WRONG_MARKS);
	}
	this.mark3 = mark3;
    }

    @Override
    public boolean isTrialPass() {
	return super.isTrialPass() && mark3 >= EXTRA_TRIAL_PASS_MARK;
    }

    @Override
    public void clearMarks() {
	super.clearMarks();
	mark3 = 0;
    }
    @Override
    public String toString() {
        return super.toString() + SEPARATOR + mark3;
    }
}
