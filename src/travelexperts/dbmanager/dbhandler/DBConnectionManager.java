package travelexperts.dbmanager.dbhandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//class to handle connection to the database
//implements DBUtil interface
public class DBConnectionManager implements DBUtil {

    public static Connection getDBConnection() throws SQLException {
        Connection conn = DriverManager.getConnection(DBUtil.CONN_STRING, USERNAME, PASSWORD);
        return conn;
    }
}
