package by.epam.lab.implementation;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import by.epam.lab.beans.Result;
import by.epam.lab.interfaces.ResultDao;
import by.epam.lab.services.ResultHandler;

public class ResultImplXml implements ResultDao {
   
    
    private Iterator<Result> iterator;

    public ResultImplXml(String sourceName) throws IOException, SAXException {
	XMLReader reader = XMLReaderFactory.createXMLReader();
	ResultHandler handler = new ResultHandler();
	reader.setContentHandler(handler);
	reader.parse(sourceName);
	iterator = handler.getResults().iterator();
    }

    @Override
    public void close() throws IOException {
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
