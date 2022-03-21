package by.epam.lab.interfaces;

import by.epam.lab.beans.Byn;

public interface Priceble extends Comparable<Priceble>{
    Byn getPrice();
}
