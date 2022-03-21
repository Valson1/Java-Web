package by.epam.lab.beans;

import static by.epam.lab.utils.ConstantsUtility.*;

import by.epam.lab.interfaces.Priceable;
import by.epam.lab.services.RoundMethod;

public class Service implements Priceable {
    private final String name;
    private final Byn totalCost;
    private final int numberOfUsers;

    public Service(String name, Byn totalCost, int numberOfUsers) {
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
    public int compareTo(Priceable price) {
	return getPrice().compareTo(price.getPrice());
    }
}
