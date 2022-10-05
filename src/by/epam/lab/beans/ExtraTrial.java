package by.epam.lab.beans;

public class ExtraTrial extends Trial {

    private final static int EXTRA_TRIAL_PASS_MARK = 85;
    
    private final int thirdMark;
    
    public ExtraTrial(String account, int firstMark, int secondMark,int thirdMark) {
	super(account, firstMark, secondMark);
	this.thirdMark = thirdMark;
    }
    
    @Override
    public boolean isTrialPass() {
        return super.isTrialPass() && thirdMark >= EXTRA_TRIAL_PASS_MARK;
    }
}
