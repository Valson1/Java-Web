package by.epam.lab.service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

import by.epam.lab.beans.Trial;

import static by.epam.lab.utils.Constants.*;

public class Producer extends Thread {
    
    private final TrialBuffer trialBuffer;
    private final String path;
    
    public Producer(TrialBuffer trialBuffer,String path) {
	this.trialBuffer = trialBuffer;
	this.path = path;
    }
    @Override
    public void run() {
	try(Scanner scanner = new Scanner(new FileReader(path))){
	    while (scanner.hasNext()) {
		trialBuffer.put(new Trial(scanner.nextLine().split(SEPARATOR)));
	    }
	} catch (FileNotFoundException e) {
	    System.err.println(FILE_IS_NOT_FOUND);
	}finally {
	    trialBuffer.put(FAKE_TRIAL);
	}
    }
    
    
}
