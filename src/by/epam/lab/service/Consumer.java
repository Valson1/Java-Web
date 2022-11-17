package by.epam.lab.service;

import by.epam.lab.beans.Trial;
import static by.epam.lab.utils.Constants.*;

public class Consumer extends Thread{
    
    private TrialBuffer trialBuffer;
    
    public Consumer(TrialBuffer trialBuffer) {
	this.trialBuffer = trialBuffer;
    }
    
    @Override
    public void run() {
	Trial trial = trialBuffer.take();
	while(!trial.equals(FAKE_TRIAL)) {
	    trial = trialBuffer.take();
	}
    }
}
