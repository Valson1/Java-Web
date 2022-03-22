package by.epam.lab.beans;

import static by.epam.lab.utils.ConstantsUtility.*;

import by.epam.lab.interfaces.Priceable;
import by.epam.lab.services.RoundMethod;

public class Purchase<T extends Priceable, K extends Number> {
    private final T item;
    private final K units;

    public Purchase(T item, K units) {
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
	return item + SEPARATOR + units + SEPARATOR + getCost();
    }

}
