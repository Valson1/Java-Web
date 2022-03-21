package by.epam.lab.interfaces;

import by.epam.lab.beans.Byn;

public interface Priceable extends Comparable<Priceable> {
    Byn getPrice();
}
