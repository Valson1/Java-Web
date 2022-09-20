package by.epam.lab.interfaces;

import java.io.Closeable;

import by.epam.lab.beans.Result;
import by.epam.lab.factories.ResultFactory;

public interface ResultDao extends Closeable{
    Result nextResult();
    boolean hasResult();
}
