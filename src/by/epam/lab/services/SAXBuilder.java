package by.epam.lab.services;

import java.io.IOException;
import java.util.List;

import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import by.epam.lab.beans.Result;

public class SAXBuilder {
    private List<Result> results;
    private ResultHandler resultHandler;
    private XMLReader reader;

    public SAXBuilder() {
	resultHandler = new ResultHandler();
	try {
	    reader = XMLReaderFactory.createXMLReader();
	    reader.setContentHandler(resultHandler);
	} catch (SAXException e) {
	    e.printStackTrace();
	}
    }

    public List<Result> getResults() {
	return results;
    }

    public void buildListResults(String fileName) {
	try {
	    reader.parse(fileName);
	} catch (IOException | SAXException e) {
	    e.printStackTrace();
	}
	results = resultHandler.getResults();
    }
}
