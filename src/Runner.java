import by.epam.lab.services.*;
import static by.epam.lab.utils.Constants.*;

import java.io.IOException;

import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

public class Runner {
    public static void main(String[] args) {
	XMLReader reader;
	try {
	    reader = XMLReaderFactory.createXMLReader();
	    ResultHandler handler = new ResultHandler();
	    reader.setContentHandler(handler);
	    reader.parse(FILE_NAME);
	    handler.printResults();
	} catch (SAXException | IOException e) {
	    e.printStackTrace();
	}
    }
}
