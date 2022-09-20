package by.epam.lab.implementation;

import java.io.IOException;
import java.util.List;

import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import by.epam.lab.beans.Result;
import by.epam.lab.interfaces.ResultDao;
import by.epam.lab.services.ResultHandler;

public class ResultImplXml implements ResultDao{
    private final ResultHandler handler;
    private final List<Result> results;
    private int listCount = 0;
    
    public ResultImplXml(String sourceName) throws IOException, SAXException {
	XMLReader reader = XMLReaderFactory.createXMLReader();
	handler = new ResultHandler();
	reader.setContentHandler(handler);
	reader.parse(sourceName);
	results = handler.getResults();
    }
    
    @Override
    public void close() throws IOException {
	
    }

    @Override
    public Result nextResult() {
	return results.get(listCount++);
    }

    @Override
    public boolean hasResult() {
	return !results.isEmpty() && listCount < results.size();
    }
    
}
