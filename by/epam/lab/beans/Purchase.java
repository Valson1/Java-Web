package by.epam.lab.beans;

import static by.epam.lab.utils.ConstantsUtility.*;

import by.epam.lab.exceptions.NonPositiveArgumentException;
import by.epam.lab.interfaces.Priceble;
import by.epam.lab.services.RoundMethod;

public class Purchase <T extends Priceble,K extends Number> implements Comparable<Purchase<? extends Priceble, ? extends Number>> {
    private final T item;
    private final K units;

    public Purchase(T item, K units) {
	if (units.doubleValue() <= 0) {
	    throw new NonPositiveArgumentException(EXCEPTION_MESSAGE_NUMBER_OF_UNITS + units);
	}
	this.item = item;
	this.units = units;
    }
    
    public T getItem() {
	return item;
    }

    public K getUnits() {
	return units;
    }

    public Byn getCost() {
	return item.getPrice().multiply(units.doubleValue(), RoundMethod.ROUND, 0);
    }

    @Override
    public String toString() {
	return getClass().getSimpleName() + SEPARATOR + item + SEPARATOR + units + SEPARATOR + getCost();
    }

    @Override
    public int compareTo(Purchase<? extends Priceble, ? extends Number> o) {
	return item.getPrice().compareTo(o.getItem().getPrice());
    }

}
