package by.epam.lab.beans;

public class LigthTrial extends Trial {
    
    private final static int LIGTH_TRIAL_PASS_MARK = 60;

    public LigthTrial(String account, int firstMark, int secondMark) {
	super(account, firstMark, secondMark);
    }
    
    private LigthTrial(LigthTrial trial) {
   	super(trial.getAccount(),trial.getFirstMark(),trial.getSecondMark());
       }
    
    @Override
    public boolean isTrialPass() {
        return getFirstMark() >= LIGTH_TRIAL_PASS_MARK && getSecondMark() >= LIGTH_TRIAL_PASS_MARK; 
    }
    
    
}
