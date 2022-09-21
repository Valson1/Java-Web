package by.epam.lab.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import static by.epam.lab.utils.DatabaseConstants.*;

public class DatabaseConnection {

    private static Connection connection;
    private static DatabaseConnection instance;

    private DatabaseConnection() {
	try {
	    connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
	} catch (SQLException e) {
	    e.printStackTrace();
	}
    }

    public Connection getConnection() {
	return connection;
    }

    public static DatabaseConnection getInstance() {
	if (instance == null) {
	    instance = new DatabaseConnection();
	}
	return instance;
    }
    
    public void close() {
	try {
	    connection.close();
	} catch (SQLException e) {
	    e.printStackTrace();
	}
    }
}
