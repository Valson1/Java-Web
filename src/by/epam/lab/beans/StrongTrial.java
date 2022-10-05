package by.epam.lab.beans;

import static by.epam.lab.utils.ConstantUtils.*;

public class StrongTrial extends Trial {

    public StrongTrial(String account, int firstMark, int secondMark) {
	super(account, firstMark, secondMark);
    }
    
    @Override
    public boolean isTrialPass() {
        return getFirstMark() / 2 + getSecondMark() >= TRIAL_PASS_MARK;
    }
}
