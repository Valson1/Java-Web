package by.epam.lab.services.factories;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.xml.sax.SAXException;

import by.epam.lab.interfaces.DataLoader;
import by.epam.lab.services.CsvLoader;
import by.epam.lab.services.XmlLoader;

public class LoaderFactory {
    private final static String SPLIT_POINT_REGEX = "\\.";

    private enum LoaderKind {
	CSV {
	    @Override
	    protected DataLoader getLoader(String fileName) throws FileNotFoundException {
		return new CsvLoader(fileName);
	    }
	},
	XML {
	    @Override
	    protected DataLoader getLoader(String fileName) throws SAXException, IOException {
		return new XmlLoader(fileName);
	    }
	};

	protected abstract DataLoader getLoader(String fileName)
		throws FileNotFoundException, SAXException, IOException;
    }

    public static DataLoader getLoaderFromFactory(String fileName)
	    throws FileNotFoundException, SAXException, IOException {
	return getLoaderKind(fileName).getLoader(fileName);
    }

    private static LoaderKind getLoaderKind(String fileName) {
	LoaderKind loaderKind = null;
	String[] pointSeparatedElements = fileName.split(SPLIT_POINT_REGEX);
	String extension = pointSeparatedElements[pointSeparatedElements.length - 1].toUpperCase();
	for (LoaderKind loader : LoaderKind.values()) {
	    if (loader.toString().equals(extension)) {
		loaderKind = loader;
		break;
	    }
	}
	return loaderKind;
    }
}
