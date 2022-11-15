import by.epam.lab.service.Consumer;
import by.epam.lab.service.Producer;
import by.epam.lab.service.TrialBuffer;

public class Runner {
    public static void main(String[] args) {
	TrialBuffer data = new TrialBuffer();
	new Thread(new Producer(data)).start();
	new Thread(new Consumer(data)).start();	
    }
}
