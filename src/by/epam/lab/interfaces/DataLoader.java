package by.epam.lab.interfaces;

import java.io.FileNotFoundException;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import by.epam.lab.services.factories.MarkKind;

public interface DataLoader {
    void loadDatabase(String fileName, PreparedStatement loginsStatement, PreparedStatement testsStatement,
	    PreparedStatement resultsStatement, PreparedStatement selectLoginsStatement,
	    PreparedStatement selectTestsStatement, PreparedStatement selectStatement, MarkKind kindMark)
	    throws SQLException;

}
