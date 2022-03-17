package by.epam.lab.beans;

import static by.epam.lab.utils.ConstantsUtility.*;

import by.epam.lab.exceptions.NonPositiveArgumentException;
import by.epam.lab.services.RoundMethod;

public class Purchase {
    private final Product product;
    private final double units;

    public Purchase(Product product, double units) {
	if(units <= 0) {
	    throw new NonPositiveArgumentException(EXCEPTION_MESSAGE_NUMBER_OF_UNITS + units);
	}
	this.product = product;
	this.units = units;
    }

    public Product getProduct() {
	return product;
    }

    public double getUnits() {
	return units;
    }
    
    public Byn getCost() {
	return product.getPrice().multiply(units, RoundMethod.ROUND, 0);
    }
    
    @Override
    public String toString() {
	return getClass().getSimpleName() + SEPARATOR + product + SEPARATOR + units + SEPARATOR + getCost();
    }

}
