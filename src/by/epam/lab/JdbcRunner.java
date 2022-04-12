package by.epam.lab;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import by.epam.lab.beans.*;

public class JdbcRunner {
    private final static String SEPARATOR = ";";

    private final static String DB_URL = "jdbc:mysql://localhost:3306/segments";
    private final static String DB_PASSWORD = "Qwerty147258369";
    private final static String DB_USER = "root";

    private final static int LEN_COLUMN = 1;
    private final static int NUM_COLUMN = 2;

    private final static String SELECT_COORDINATES = "SELECT ROUND(ABS(x2-x1)) AS len, COUNT(*) AS num FROM Coordinates GROUP BY 1 ORDER BY 1";
    private final static String DELETE_FREQUENCIES = "DELETE FROM Frequencies";
    private final static String INSERT_FREQUENCIES = "INSERT INTO Frequencies(len, num) VALUES (?, ?)";
    private final static String LEN_MORE_NUM_FREQUENCIES = "SELECT len,num FROM Frequencies WHERE len > num;";

    public static void main(String[] args) {
	try (Connection cn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
		Statement st = cn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		PreparedStatement ps = cn.prepareStatement(INSERT_FREQUENCIES)) {
	    List<SegmentNumber> segments = new ArrayList<>();
	    st.executeUpdate(DELETE_FREQUENCIES);
	    try (ResultSet rs = st.executeQuery(SELECT_COORDINATES)) {
		// add content from table Coordinates to list
		while (rs.next()) {
		    segments.add(new SegmentNumber(rs.getInt(LEN_COLUMN), rs.getInt(NUM_COLUMN)));
		}
	    }
		// insert content from list to table Frequencies and output list
		for (SegmentNumber segmentNumber : segments) {
		    ps.setInt(LEN_COLUMN, segmentNumber.getLen());
		    ps.setInt(NUM_COLUMN, segmentNumber.getNum());
		    ps.addBatch();
		    System.out.println(segmentNumber);
		}
		ps.executeBatch();
	    try (ResultSet rs = st.executeQuery(LEN_MORE_NUM_FREQUENCIES)) {
		// find content where len > num and output
		while (rs.next()) {
		    System.out.println(rs.getInt(LEN_COLUMN) + SEPARATOR + rs.getInt(NUM_COLUMN));
		}
	    }
	} catch (SQLException e) {
	    e.printStackTrace();
	}
    }
}
