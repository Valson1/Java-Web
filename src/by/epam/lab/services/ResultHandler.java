package by.epam.lab.services;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import static by.epam.lab.utils.Constants.*;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import by.epam.lab.beans.Result;

public class ResultHandler extends DefaultHandler {
    private ResultEnum currentEnum;
    private List<Result> results = new ArrayList<>();
    private String login;

    private enum ResultEnum {
	RESULTS, STUDENT, LOGIN, TESTS, TEST
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
	currentEnum = ResultEnum.valueOf(localName.toUpperCase());
	if (currentEnum == ResultEnum.TEST) {
	    Result result = new Result();
	    try {
		result.setDate(attributes.getValue(DATE_INDEX));
	    } catch (ParseException e) {
		throw new IllegalArgumentException(DATE_PARSE_ERROR);
	    }
	    result.setTest(attributes.getValue(TEST_INDEX));
	    result.setMark(attributes.getValue(MARK_INDEX));
	    result.setLogin(login);
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
	    if(!elementValue.isBlank()) {
		login = elementValue;
	    }
	}
    }

    
}