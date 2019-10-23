package travelexperts.dbhandler;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import travelexperts.models.Bookings;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;

public class BookingsDBHandler {

    public static ArrayList<Integer> getBookingIds() throws Exception{
        ArrayList<Integer> bookingIds = new ArrayList<Integer>();
        try(Connection conn = DBConnectionManager.getDBConnection();
            Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = stmt.executeQuery("SELECT BookingId FROM bookings")
        ) {
            //System.out.println("connected!");
            //loop through the result set and add the ids to the list
            while(rs.next()){
                bookingIds.add(rs.getInt("BookingId"));
            }
            //sort the list
            Collections.sort(bookingIds);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new SQLException();
        }
        return bookingIds;
    }

    public static Bookings gatBookingsDetails(int id) {
        Bookings book = null;
        try (Connection connection = DBConnectionManager.getDBConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * from bookings where BookingId =?",
                     ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);) {
            statement.setString(1, String.valueOf(id));
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    book = new Bookings();
                    book.setBookingId(resultSet.getInt("BookingId"));
                    book.setBookingDate(resultSet.getDate("BookingDate"));
                    book.setBookingNo(resultSet.getString("BookingNo"));
                    book.setTravelerCount(resultSet.getInt("TravelerCount"));
                    book.setCustomerId(resultSet.getInt("CustomerId"));
                    book.setTripTypeId(resultSet.getString("TripTypeId"));
                    book.setPackageId(resultSet.getInt("PackageId"));

                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return book;
    }


    public static ObservableList<Bookings> gatAllBookings() {
        ObservableList<Bookings> bookingsList = FXCollections.observableArrayList();
        try (Connection connection = DBConnectionManager.getDBConnection();
             Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);) {
            ResultSet resultSet = statement.executeQuery("SELECT * from bookings");

            while (resultSet.next()) {
                Bookings book= new Bookings();
                book.setBookingId(Integer.parseInt(resultSet.getString("BookingId")));
                book.setBookingDate(resultSet.getDate("BookingDate"));
                book.setBookingNo(resultSet.getString("BookingNo"));
                book.setTravelerCount(resultSet.getInt("TravelerCount"));
                book.setCustomerId(Integer.parseInt(resultSet.getString("CustomerId")));
                book.setTripTypeId(resultSet.getString("TripTypeId"));
                //System.out.println("Setting Package ID to: "+resultSet.getInt("PackageId"));
                book.setPackageId(resultSet.getInt("PackageId"));

                bookingsList.add(book);
            }
            return bookingsList;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }

    }
}

