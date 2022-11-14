import by.epam.lab.service.Consumer;
import by.epam.lab.service.Producer;
import by.epam.lab.service.TakeOutputData;

public class Runner {
    public static void main(String[] args) {
	TakeOutputData data = new TakeOutputData();
	new Thread(new Producer(data)).start();	
	new Thread(new Consumer(data)).start();	
    }
}
