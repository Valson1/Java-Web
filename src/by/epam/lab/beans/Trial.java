package by.epam.lab.beans;

import static by.epam.lab.utils.Constants.*;

    public class Trial {

	private final String account;
	private final int mark1;
	private final int mark2;

	public Trial(String account, int firstMark, int secondMark) {
	    checkMarks(firstMark, secondMark);
	    this.account = account;
	    this.mark1 = firstMark;
	    this.mark2 = secondMark;
	}

	public Trial(String[] elements) {
	    this(elements[ACCOUNT_FIELD],Integer.parseInt(elements[MARK1_FIELD]),Integer.parseInt(elements[MARK2_FIELD]));
	}
	
	private static void checkMarks(int mark1, int secondMark) {
	    if ((mark1 < MIN_CONDITION_MARK || mark1 > MAX_CONDITION_MARK)
		    || (secondMark < MIN_CONDITION_MARK || secondMark > MAX_CONDITION_MARK)) {
		throw new IllegalArgumentException(WRONG_MARKS);
	    }
	}

	public Trial(Trial trial) {
	    this(trial.account, trial.mark1, trial.mark2);
	}

	public String getAccount() {
	    return account;
	}

	public int getFirstMark() {
	    return mark1;
	}

	public int getSecondMark() {
	    return mark2;
	}

	private boolean isTrialPass() {
	    return (mark1 + mark2) >= PASS_MARK;

	}
	
	@Override
	public String toString() {
	    return account + SEPARATOR + mark1 + SEPARATOR + mark2 + SEPARATOR + isTrialPass();
	}

	
    }

