package by.epam.lab.services;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import by.epam.lab.beans.Result;
import by.epam.lab.interfaces.DataLoader;
import by.epam.lab.services.factories.MarkKind;

public class XmlLoader implements DataLoader {
    private final ResultHandler handler;
    private final ResultDAO resultDAO;

    public XmlLoader(String fileName) throws SAXException, IOException {
	XMLReader reader = XMLReaderFactory.createXMLReader();
	handler = new ResultHandler();
	reader.setContentHandler(handler);
	reader.parse(fileName);
	resultDAO = new ResultDAO();
    }

    @Override
    public void loadDatabase(String fileName, PreparedStatement loginsStatement, PreparedStatement testsStatement,
	    PreparedStatement resultsStatement, PreparedStatement selectLoginsStatement,
	    PreparedStatement selectTestsStatement, PreparedStatement selectStatement, MarkKind kindMark)
	    throws SQLException {

	for (Result result : handler.getResults()) {
	    String login = result.getLogin();
	    String test = result.getTest();
	    System.out.println(result);
	    resultDAO.insertDataChainedTables(selectLoginsStatement, loginsStatement, login);
	    resultDAO.insertDataChainedTables(selectTestsStatement, testsStatement, test);
	    resultDAO.insertResultingTable(selectStatement, resultsStatement, result, kindMark);
	}
    }
}
