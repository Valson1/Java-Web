package by.epam.lab.service;

public class Consumer extends Thread{
    
    private TakeOutputData data;
    
    public Consumer(TakeOutputData data) {
	this.data = data;
    }
    
    @Override
    public void run() {
	while(data.isEmpty()) {
	    data.outputData();
	}
    }
}
