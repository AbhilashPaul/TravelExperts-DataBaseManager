package exercise6.dbhandler;

//this interface defines connection string, username  and password used to connect to the database
public interface DBUtil {
    String USERNAME = "agent";
    String PASSWORD = "dbpassword";
    String CONN_STRING = "jdbc:mysql://localhost/travelexperts";
}