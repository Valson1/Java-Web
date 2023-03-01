package by.epam.lab.services;

import java.net.ConnectException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import by.epam.lab.exceptions.ConnectionException;

import static by.epam.lab.utils.DatabaseConstants.*;

public class DatabaseConnection {

    private static Connection connection;
    private static DatabaseConnection instance;

    private DatabaseConnection() throws ConnectionException {
	try {
	    connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
	} catch (SQLException e) {
	    throw new ConnectionException(e.getMessage());
	}
    }

    public Connection getConnection() {
	return connection;
    }

    public static DatabaseConnection getInstance() throws ConnectionException {
	if (instance == null) {
	    instance = new DatabaseConnection();
	}
	return instance;
    }
    
    public void close() throws ConnectionException {
	try {
	    connection.close();
	} catch (SQLException e) {
	    throw new ConnectionException(e.getMessage());
	}
    }
}
