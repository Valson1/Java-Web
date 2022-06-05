package by.epam.lab.services;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;
import by.epam.lab.interfaces.DataLoader;
import by.epam.lab.services.factories.MarkKind;

import static by.epam.lab.utils.ConstantsUtility.*;

public class CsvLoader implements DataLoader {
    private final Scanner sc;
    private final ResultDAO resultDAO;

    public CsvLoader(String fileName) throws FileNotFoundException {
	sc = new Scanner(new FileReader(fileName));
	resultDAO = new ResultDAO();
    }

    @Override
    public void loadDatabase(String fileName, PreparedStatement loginsStatement, PreparedStatement testsStatement,
	    PreparedStatement resultsStatement, PreparedStatement selectLoginsStatement,
	    PreparedStatement selectTestsStatement, PreparedStatement selectStatement, MarkKind kindMark)
	    throws SQLException {

	while (sc.hasNextLine()) {
	    String[] elements = sc.nextLine().split(SEPARATOR);
	    resultDAO.insertDataChainedTables(selectLoginsStatement, loginsStatement, elements[LOGIN_LINE_ELEMENT]);
	    resultDAO.insertDataChainedTables(selectTestsStatement, testsStatement, elements[TEST_LINE_ELEMENT]);
	    resultDAO.insertResultingTable(selectStatement, resultsStatement, elements, kindMark);
	}
    }

}
