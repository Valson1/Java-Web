package by.epam.lab.service;

import static by.epam.lab.utils.Constants.*;

import by.epam.lab.beans.Trial;

public class TakeOutputData {

    private Trial trial;

    private boolean empty = true;

    public boolean isEmpty() {
	return empty;
    }
    
    public synchronized void getData(Trial trial) {
	while (!empty) {
	    try {
		wait();
	    } catch (InterruptedException e) {
		e.printStackTrace();
	    }
	}
	    empty = false;
	    this.trial = trial;
	    System.out.println(BUFFER_MESSAGE + this.trial);
	    notifyAll();
    }

    public synchronized void outputData() {
	while (empty) {
	    try {
		wait();
	    } catch (InterruptedException e) {
		e.printStackTrace();
	    }
	}
	empty = true;
	System.out.println(CONSOLE_MESSAGE + trial); 
	notifyAll();
	
    }
}
