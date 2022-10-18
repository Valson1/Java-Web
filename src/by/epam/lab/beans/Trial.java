package by.epam.lab.beans;

import static by.epam.lab.utils.ConstantUtils.*;

public class Trial implements Comparable<Trial>{
    
    private final String account;
    private int mark1;
    private int mark2;
    
    public Trial(String account, int firstMark, int secondMark) {
	checkMarks(firstMark, secondMark);
	this.account = account;
	this.mark1 = firstMark;
	this.mark2 = secondMark;
    }

    private static void checkMarks(int mark1, int secondMark) {
	if ((mark1 < MIN_CONDITION_MARK || mark1 > MAX_CONDITION_MARK)
		|| (secondMark < MIN_CONDITION_MARK || secondMark > MAX_CONDITION_MARK)) {
	    throw new IllegalArgumentException(WRONG_MARKS);
	}
    }
    
    public Trial(Trial trial) {
	this(trial.account,trial.mark1,trial.mark2);
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
    
    public final int sumMarks() {
	return mark1 + mark2;
    }

    public boolean isTrialPass() {
	return sumMarks() >= TRIAL_PASS_MARK;
    }
    
    public void clearMarks() {
	mark1 = 0;
	mark2 = 0;
    }
    
    public Trial getCopy() {
	return new Trial(this);
    }
    
    public boolean isClear() {
	return mark1 == 0 && mark2 == 0;
    }

    @Override
    public String toString() {
	return account + SEPARATOR + mark1 + SEPARATOR + mark2;
    }

    @Override
    public int compareTo(Trial o) {
	return sumMarks() - o.sumMarks();
    }
}
