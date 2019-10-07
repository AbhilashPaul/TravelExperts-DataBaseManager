package travelexperts.dbhandler;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class AnalyticsDB {

    public static HashMap<String, Integer> getTopDestinations() throws SQLException {

        HashMap<String, Integer> topDestinationList = new HashMap<String, Integer>();
        String query = "SELECT T.Destination,T.CountOfBookings FROM (SELECT Destination, COUNT(*) " +
                "as CountOfBookings FROM bookingdetails GROUP BY Destination) AS T " +
                "WHERE Destination != \" \" ORDER BY T.CountOfBookings DESC LIMIT 5";

        try(Connection conn = DBConnectionManager.getDBConnection();
            Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = stmt.executeQuery(query)
        ) {
            //loop through the result set and add the records to the hashmap
            while(rs.next()){
                topDestinationList.put(rs.getString(1),rs.getInt(2));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new SQLException();
        }
        return topDestinationList;
    }


    String query = "SELECT T.CustomerId, C.CustFirstName, C.CustLastName, T.NumberOfBookings FROM " +
            "(SELECT COUNT(*) AS NumberOfBookings, CustomerId FROM bookings GROUP BY CustomerId) AS T " +
            "INNER JOIN customers As C ON C.CustomerId = T.CustomerId ORDER BY T.NumberOfBookings DESC LIMIT 10";
}
