package by.epam.lab.service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

import by.epam.lab.beans.Trial;

import static by.epam.lab.utils.Constants.*;

public class Producer extends Thread {
    
    private static final String FILE_NAME = "src/data/in.csv";
    
    private TrialBuffer trialBuffer;
    
    public Producer(TrialBuffer trialBuffer) {
	this.trialBuffer = trialBuffer;
    }
    @Override
    public void run() {
	try(Scanner scanner = new Scanner(new FileReader(FILE_NAME))){
	    while (scanner.hasNext()) {
		trialBuffer.take(new Trial(scanner.nextLine().split(SEPARATOR)));
	    }
	} catch (FileNotFoundException e) {
	    System.err.println(FILE_IS_NOT_FOUND);
	}
    }
    
    
}
