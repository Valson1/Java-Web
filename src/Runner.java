import by.epam.lab.beans.Result;
import by.epam.lab.services.*;
import static by.epam.lab.utils.Constants.*;

import java.io.IOException;
import java.util.List;

import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

public class Runner {
    private static void printResults(List<Result> results) {
	for (Result result : results) {
	    System.out.println(result);
	}
    }

    public static void main(String[] args) {
	try {
	    XMLReader reader = XMLReaderFactory.createXMLReader();
	    ResultHandler handler = new ResultHandler();
	    reader.setContentHandler(handler);
	    reader.parse(FILE_NAME);
	    printResults(handler.getResults());
	} catch (SAXException e) {
	    System.err.println(SAX_EXCEPTION_MESSAGE);
	} catch (IOException e) {
	    System.err.println(IO_EXCEPTION_MESSAGE);
	}
    }
}
