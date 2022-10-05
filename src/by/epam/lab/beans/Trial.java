package by.epam.lab.beans;

import static by.epam.lab.utils.ConstantUtils.*;

public class Trial implements Comparable<Trial>{
    
    private final String account;
    private int firstMark;
    private int secondMark;

    public Trial(String account, int firstMark, int secondMark) {
	checkMarks(firstMark, secondMark);
	this.account = account;
	this.firstMark = firstMark;
	this.secondMark = secondMark;
    }

    private static void checkMarks(int firstMark, int secondMark) {
	if ((firstMark < MIN_CONDITION_MARK || firstMark > MAX_CONDITION_MARK)
		|| (secondMark < MIN_CONDITION_MARK || secondMark > MAX_CONDITION_MARK)) {
	    throw new IllegalArgumentException(WRONG_MARKS);
	}
    }

    public String getAccount() {
	return account;
    }

    public int getFirstMark() {
	return firstMark;
    }

    public int getSecondMark() {
	return secondMark;
    }
    
    public int sumMarks() {
	return firstMark + secondMark;
    }

    public boolean isTrialPass() {
	return sumMarks() >= TRIAL_PASS_MARK;
    }
    
    public void clearMarks() {
	firstMark = 0;
	secondMark = 0;
    }
    
    public boolean isClear() {
	return firstMark == 0 && secondMark == 0;
    }

    @Override
    public String toString() {
	return getClass().getSimpleName() + SEPARATOR + account + SEPARATOR + firstMark + SEPARATOR + secondMark;
    }

    @Override
    public int compareTo(Trial o) {
	return sumMarks() - o.sumMarks();
    }
}
