package by.epam.lab.service;

import static by.epam.lab.utils.Constants.*;

import by.epam.lab.beans.Trial;

public class TrialBuffer {

    private Trial trial;

    private boolean empty = true;

    public boolean isEmpty() {
	return empty;
    }
    
    public synchronized void take(Trial trial) {
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

    public synchronized void put() {
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
