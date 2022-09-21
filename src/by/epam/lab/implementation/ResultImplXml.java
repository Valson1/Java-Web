package by.epam.lab.implementation;

import java.io.IOException;
import java.util.Iterator;

import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;
import static by.epam.lab.utils.ConstantsUtility.*;
import by.epam.lab.beans.Result;
import by.epam.lab.exceptions.ParseRuntimeException;
import by.epam.lab.exceptions.SourceException;
import by.epam.lab.interfaces.ResultDao;
import by.epam.lab.services.ResultHandler;

public class ResultImplXml implements ResultDao {

    private Iterator<Result> iterator;

    public ResultImplXml(String sourceName) throws SourceException {
	try {
	    XMLReader reader = XMLReaderFactory.createXMLReader();
	    ResultHandler handler = new ResultHandler();
	    reader.setContentHandler(handler);
	    reader.parse(sourceName);
	    iterator = handler.getResults().iterator();
	} catch (IOException e) {
	    throw new SourceException(WRONG_FILE_NAME + sourceName);
	} catch (SAXException e) {
	    throw new ParseRuntimeException(SAX_PARSING_ERROR);
	}

    }

    @Override
    public void close() throws SourceException {
	iterator = null;
    }

    @Override
    public Result nextResult() {
	return iterator.next();
    }

    @Override
    public boolean hasResult() {
	return iterator.hasNext();
    }

}
