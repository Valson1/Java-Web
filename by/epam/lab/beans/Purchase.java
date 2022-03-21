package by.epam.lab.beans;

import static by.epam.lab.utils.ConstantsUtility.*;

import by.epam.lab.interfaces.Priceable;
import by.epam.lab.services.RoundMethod;

public class Purchase implements Comparable<Purchase> {
    private final Priceable item;
    private final Number units;

    public Purchase(Priceable item, Number units) {
	this.item = item;
	this.units = units;
    }

    public Priceable getItem() {
	return item;
    }

    public Number getUnits() {
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
    public int compareTo(Purchase o) {
	return item.getPrice().compareTo(o.getItem().getPrice());
    }

}
