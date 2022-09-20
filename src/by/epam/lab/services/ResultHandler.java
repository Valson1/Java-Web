package by.epam.lab.services;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import by.epam.lab.beans.DecimalResult;
import by.epam.lab.beans.Result;

public class ResultHandler extends DefaultHandler {
    private static final int TEST_ATTRIBUTE = 0;
    private static final int DATE_ATTRIBUTE = 1;
    private static final int MARK_ATTRIBUTE = 2;
    
    private ResultEnum currentEnum;
    private final List<Result> results = new ArrayList<>();
    private String login;

    private enum ResultEnum {
	RESULTS, STUDENT, LOGIN, TESTS, TEST
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
	currentEnum = ResultEnum.valueOf(localName.toUpperCase());
	if (currentEnum == ResultEnum.TEST) {
	    Result result = new DecimalResult(login, attributes.getValue(TEST_ATTRIBUTE), attributes.getValue(DATE_ATTRIBUTE),
		    attributes.getValue(MARK_ATTRIBUTE));
	    results.add(result);
	}
    }

    public List<Result> getResults() {
	return results;
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
	if (currentEnum == ResultEnum.LOGIN) {
	    String elementValue = new String(ch, start, length);
	    if (!elementValue.isBlank()) {
		login = elementValue;
	    }
	}
    }

}