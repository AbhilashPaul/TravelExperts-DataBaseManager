package travelexperts.dbhandler;

import travelexperts.models.TravelPackage;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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


        public static ArrayList<TravelPackage> gatAllPackages() {
            TravelPackage pkg = null;
            ArrayList<TravelPackage> packages = null;
            try (Connection connection = DBConnectionManager.getDBConnection();
                 PreparedStatement statement = connection.prepareStatement("SELECT * from packages",
                         ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);) {
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
                        packages.add(pkg);
                    }
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
            return packages;
        }
    }
