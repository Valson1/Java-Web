package by.epam.lab.beans;

import static by.epam.lab.utils.ConstantsUtility.*;

import by.epam.lab.exceptions.NonPositiveArgumentException;
import by.epam.lab.interfaces.Priceble;
import by.epam.lab.services.RoundMethod;

public class Service implements Priceble {
    private final String name;
    private final Byn totalCost;
    private final int numberOfUsers;

    public Service(String name, Byn totalCost, int numberOfUsers) {
	if (numberOfUsers <= 0) {
	    throw new NonPositiveArgumentException(EXCEPTION_MESSAGE_NUMBER_OF_USERS + numberOfUsers);
	}
	if (name.isEmpty()) {
	    throw new NonPositiveArgumentException(EXCEPTION_MESSAGE_NAME + name);
	}
	if (totalCost.compareTo(new Byn(0)) <= 0) {
	    throw new NonPositiveArgumentException(EXCEPTION_MESSAGE_NAME + name);
	}
	this.name = name;
	this.totalCost = totalCost;
	this.numberOfUsers = numberOfUsers;
    }

    public int getNumberOfUsers() {
	return numberOfUsers;
    }

    @Override
    public String toString() {
	return name + SEPARATOR + totalCost + SEPARATOR + numberOfUsers + SEPARATOR + getPrice();
    }

    @Override
    public Byn getPrice() {
	return totalCost.multiply(1.0 / numberOfUsers, RoundMethod.CEIL, 0);
    }
    @Override
    public int compareTo(Priceble price) {
	return getPrice().compareTo(price.getPrice());
    }
}
