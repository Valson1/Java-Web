package by.epam.lab.service;

public class Consumer extends Thread{
    
    private TrialBuffer trialBuffer;
    
    public Consumer(TrialBuffer trialBuffer) {
	this.trialBuffer = trialBuffer;
    }
    
    @Override
    public void run() {
	while(trialBuffer.isEmpty()) {
	    trialBuffer.put();
	}
    }
}
