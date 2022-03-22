package by.epam.lab.beans;

import static by.epam.lab.utils.ConstantsUtility.*;

import by.epam.lab.interfaces.Priceable;
import by.epam.lab.services.RoundMethod;

public class Purchase<K extends Priceable, N extends Number> {
    private final K item;
    private final N units;

    public Purchase(K item, N units) {
	this.item = item;
	this.units = units;
    }

    public K getItem() {
	return item;
    }

    public N getUnits() {
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
