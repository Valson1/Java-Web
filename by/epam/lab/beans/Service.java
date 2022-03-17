package by.epam.lab.beans;
import static by.epam.lab.utils.ConstantsUtility.*;

import by.epam.lab.exceptions.NonPositiveArgumentException;

public class Service extends Product{
    private final int numberOfUsers;
    
    public Service(String name,Byn totalCost,int numberOfUsers) {
	super(name,totalCost.div(numberOfUsers));
	if (numberOfUsers <= 0) {
	    throw new NonPositiveArgumentException(EXCEPTION_MESSAGE_NUMBER_OF_USERS + numberOfUsers);
	}
	this.numberOfUsers = numberOfUsers;
    }

    public Service() {
	this("",new Byn(0),0);
    }

    public int getNumberOfUsers() {
        return numberOfUsers;
    }     
}
