package by.epam.lab.beans;

import static by.epam.lab.utils.ConstantUtils.*;

public class StrongTrial extends Trial {

    public StrongTrial(String account, int firstMark, int secondMark) {
	super(account, firstMark, secondMark);
    }
    
    private StrongTrial(StrongTrial trial) {
	super(trial.getAccount(),trial.getFirstMark(),trial.getSecondMark());
    }
    
    @Override
    public boolean isTrialPass() {
        return getFirstMark() / HALF_DIVIDOR + getSecondMark() >= TRIAL_PASS_MARK;
    }
}
