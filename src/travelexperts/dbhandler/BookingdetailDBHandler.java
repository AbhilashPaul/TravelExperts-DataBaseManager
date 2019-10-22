package travelexperts.dbhandler;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import travelexperts.models.Bookingdetail;
import travelexperts.models.Customer;
import travelexperts.models.TravelPackage;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;

public class BookingdetailDBHandler {
    public static ArrayList<Integer> getBookingdetailIds() throws Exception{
        ArrayList<Integer> bookingdetailIds = new ArrayList<Integer>();
        try(Connection conn = DBConnectionManager.getDBConnection();
            Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = stmt.executeQuery("SELECT BookingDetailId FROM bookingdetails")
        ) {
            //System.out.println("connected!");
            //loop through the result set and add the ids to the list
            while(rs.next()){
                bookingdetailIds.add(rs.getInt("BookingDetailId"));
            }
            //sort the list
            Collections.sort(bookingdetailIds);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new SQLException();
        }
        return bookingdetailIds;
    }






    public static Bookingdetail gatBookingdetail(int id) {
        Bookingdetail detail = null;
        try (Connection connection = DBConnectionManager.getDBConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * from bookingdetails where BookingDetailId =?",
                     ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);) {
            statement.setString(1, String.valueOf(id));
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    detail = new Bookingdetail();
                    detail.setBookingDetailId(Integer.parseInt(resultSet.getString("BookingDetailId")));
                    detail.setItineraryNo(resultSet.getDouble("ItineraryNo"));
                    detail.setTripStart(resultSet.getDate("TripStart"));
                    detail.setTripEnd(resultSet.getDate("TripEnd"));
                    detail.setDescription(resultSet.getString("Description"));
                    detail.setDestination(resultSet.getString("Destination"));
                    detail.setBasePrice(resultSet.getBigDecimal("BasePrice"));
                    detail.setAgencyCommission(resultSet.getBigDecimal("AgencyCommission"));
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return detail;
    }


    public static ObservableList<Bookingdetail> gatAlldetail() {
        ObservableList<Bookingdetail> detailList = FXCollections.observableArrayList();
        try (Connection connection = DBConnectionManager.getDBConnection();
             Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);) {
            ResultSet resultSet = statement.executeQuery("SELECT * from bookingdetails");

            while (resultSet.next()) {
                Bookingdetail detail = new Bookingdetail();
                detail.setBookingDetailId(Integer.parseInt(resultSet.getString("BookingDetailId")));
                detail.setItineraryNo(resultSet.getDouble("ItineraryNo"));
                detail.setTripStart(resultSet.getDate("TripStart"));
                detail.setTripEnd(resultSet.getDate("TripEnd"));
                detail.setDescription(resultSet.getString("Description"));
                detail.setDestination(resultSet.getString("Destination"));
                detail.setBasePrice(resultSet.getBigDecimal("BasePrice"));
                detail.setAgencyCommission(resultSet.getBigDecimal("AgencyCommission"));
                detailList.add(detail);
            }
            return detailList;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }

    }
}

