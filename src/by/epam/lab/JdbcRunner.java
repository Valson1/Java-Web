package by.epam.lab;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import by.epam.lab.beans.*;

public class JdbcRunner {
    private final static String CLASS_NAME = "com.mysql.cj.jdbc.Driver";
    private final static String DB_URL = "jdbc:mysql://localhost:3306/segments";
    private final static String DB_PASSWORD = "Qwerty147258369";
    private final static String DB_USER = "root";
    private final static String SQL_QUERY_SELECT_COORDINATES = "SELECT ABS(CEIL(x1-x2)) AS len, COUNT(*) AS num FROM Coordinates GROUP BY len ORDER BY len";
    private final static String SQL_QUERY_DELETE_FREQUENCIES = "DELETE FROM Frequencies";
    private final static String SQL_QUERY_SELECT_FREQUENCIES = "SELECT len,num FROM Frequencies";
    private final static String SQL_QUERY_LEN_MORE_NUM_FREQUENCIES = "SELECT (len > num) FROM Frequencies;";

    public static void main(String[] args) {
	try {
	    Class.forName(CLASS_NAME);
	    Connection cn = null;
	    Statement st = null;
	    ResultSet rs = null;
	    try {
		cn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
		st = cn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		rs = st.executeQuery(SQL_QUERY_SELECT_COORDINATES);
		List<SegmentNumber> segments = new ArrayList<>();
		while (rs.next()) {
		    segments.add(new SegmentNumber(rs.getInt(1), rs.getInt(2)));
		}
		st.executeUpdate(SQL_QUERY_DELETE_FREQUENCIES);
		rs = st.executeQuery(SQL_QUERY_SELECT_FREQUENCIES);
		for (SegmentNumber segmentNumber : segments) {
		    rs.moveToInsertRow();
		    rs.updateObject(1, segmentNumber.getLen());
		    rs.updateObject(2, segmentNumber.getNum());
		    rs.insertRow();
		    rs.moveToCurrentRow();
		}
		rs = st.executeQuery(SQL_QUERY_LEN_MORE_NUM_FREQUENCIES);
		System.out.println(segments);
		for (SegmentNumber segmentNumber : segments) {
		    rs.next();
		    if (rs.getInt(1) == 1) {
			System.out.println(segmentNumber);
		    }
		}
	    } finally {
		if (rs != null && !rs.isClosed()) {
		    rs.close();
		}
		if (st != null) {
		    st.close();
		}
		if (cn != null) {
		    cn.close();
		}
	    }
	} catch (SQLException | ClassNotFoundException e) {
	    e.printStackTrace();
	}
    }
}
