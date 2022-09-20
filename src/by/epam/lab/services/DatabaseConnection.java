package by.epam.lab.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private final static String DB_URL = "jdbc:mysql://localhost:3306/results";
    private final static String DB_PASSWORD = "Qwerty147258369";
    private final static String DB_USER = "root";

    private Connection connection;
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
}
