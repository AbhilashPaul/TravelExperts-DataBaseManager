package travelexperts.dbhandler;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import travelexperts.models.Customer;
import travelexperts.models.TravelPackage;

import java.sql.*;
import java.util.ArrayList;

public class TravelPackageDBHandler {
        public static TravelPackage gatPackageDetails(int id) {
            TravelPackage pkg = null;
            try (Connection connection = DBConnectionManager.getDBConnection();
                 PreparedStatement statement = connection.prepareStatement("SELECT * from packages where PackageId =?",
                         ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);) {
                statement.setString(1, String.valueOf(id));
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        pkg = new TravelPackage();
                        pkg.setPkgId(Integer.parseInt(resultSet.getString("PackageId")));
                        pkg.setPkgName(resultSet.getString("PkgName"));
                        pkg.setPkgDesc(resultSet.getString("PkgDesc"));
                        pkg.setPkgStartDate(resultSet.getDate("PkgStartDate"));
                        pkg.setPkgEndDate(resultSet.getDate("PkgEndDate"));
                        pkg.setPkgBasePrice(resultSet.getDouble("PkgBasePrice"));
                        pkg.setPkgCommission(resultSet.getDouble("PkgAgencyCommission"));
                        pkg.setPkgTripType(resultSet.getString("TripTypeId"));
                    }
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
            return pkg;
        }


        public static ObservableList<TravelPackage> gatAllPackages() {
            ObservableList<TravelPackage> packageList = FXCollections.observableArrayList();
            try (Connection connection = DBConnectionManager.getDBConnection();
                 Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);) {
                    ResultSet resultSet = statement.executeQuery("SELECT * from packages");

                    while (resultSet.next()) {
                        TravelPackage pkg = new TravelPackage();
                        pkg.setPkgId(Integer.parseInt(resultSet.getString("PackageId")));
                        pkg.setPkgName(resultSet.getString("PkgName"));
                        pkg.setPkgDesc(resultSet.getString("PkgDesc"));
                        pkg.setPkgStartDate(resultSet.getDate("PkgStartDate"));
                        pkg.setPkgEndDate(resultSet.getDate("PkgEndDate"));
                        pkg.setPkgBasePrice(resultSet.getDouble("PkgBasePrice"));
                        pkg.setPkgCommission(resultSet.getDouble("PkgAgencyCommission"));
                        pkg.setPkgTripType(resultSet.getString("TripTypeId"));
                        packageList.add(pkg);
                    }
                return packageList;
                } catch (SQLException ex) {
                System.out.println(ex.getMessage());
                return null;
            }

        }
    }
