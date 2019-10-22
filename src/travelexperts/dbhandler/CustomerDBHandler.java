package travelexperts.dbhandler;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import travelexperts.models.Agent;
import travelexperts.models.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerDBHandler {

    public static ObservableList<Customer> getCustomers(String searchString){
        ObservableList<Customer> customerList = FXCollections.observableArrayList();
        String query="";
        String fnameSearch="";
        String lnameSearch="";

        //if there is no user input, retrieve the full customer list
        if (searchString == " "){
            query = "SELECT * FROM customers ORDER BY CustFirstName;";
        }else if(isNumeric(searchString)){                              //if user enters a numeric value, use it as customer id
            query = "SELECT * FROM customers WHERE CustomerId = ?;";    //use sql query to retrieve customer info using specific customer id
        } else{                                                         //if user enters a text as input
            String[] search =searchString.split(" ");            //split the strings in the text input
            if (search.length == 1){                                   //if the entry contains only one string
                fnameSearch= "%"+search[0]+"%";                        //retrieve list of customers with the input string in first name or last name
                lnameSearch= "%"+search[0]+"%";
                query = "SELECT * FROM customers WHERE CustFirstName LIKE ? OR CustLastName LIKE ? ORDER BY CustFirstName;";
            }else {                                                    //if the input is more than one string
                fnameSearch= "%"+search[0]+"%";                        //treat the first string as first name
                lnameSearch= "%"+search[1]+"%";                        //treat the second string as second name
                query = "SELECT * FROM customers WHERE CustFirstName LIKE ? AND CustLastName LIKE ? ORDER BY CustFirstName;"; //retrieve customer record with corresponding first name and last name
            }
        }


        try (Connection connection = DBConnectionManager.getDBConnection();
             PreparedStatement statement = connection.prepareStatement(query,
                     ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);) {

            //bind parameters to the sql query depending on the kind of input received
            if (searchString != " ") {                              //check for non-empty use input
                if(isNumeric(searchString)){                        //if user entered an integer value
                    int id = Integer.parseInt(searchString);
                    statement.setString(1,String.valueOf(id));
                }else{                                              //if user entered a text as input
                statement.setString(1, fnameSearch);
                statement.setString(2, lnameSearch);
                }
            }

            //execute query
            try (ResultSet resultSet = statement.executeQuery()) {
                //gather agent details and store them in an agent object
                while(resultSet.next()){
                    Customer cust = new Customer(resultSet.getInt("CustomerId"),
                            resultSet.getString("CustFirstName"),
                            resultSet.getString("CustLastName"),
                            resultSet.getString("CustAddress"),
                            resultSet.getString("CustCity"),
                            resultSet.getString("CustProv"),
                            resultSet.getString("CustPostal"),
                            resultSet.getString("CustCountry"),
                            resultSet.getString("CustHomePhone"),
                            resultSet.getString("CustBusPhone"),
                            resultSet.getString("CustEmail"),
                            resultSet.getInt("AgentId"),
                            resultSet.getString("CustUsername"),
                            resultSet.getString("CustPassword"));
                    customerList.add(cust);
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getStackTrace());
            return null;
        }
        return customerList;
    }

    //method to update the database with updated agent data
    public static boolean updateCustomerDetails(Customer customer) {

        try (Connection connection = DBConnectionManager.getDBConnection();
             PreparedStatement statement = connection.prepareStatement("UPDATE customers SET CustFirstName=?," +
                     "CustLastName=?,CustAddress=?,CustCity=?,CustProv=?,CustPostal=?,CustCountry=?,CustHomePhone=?," +
                     "CustBusPhone=?,CustEmail=?,AgentId=?,CustUsername=?,CustPassword=? WHERE CustomerId=?"))
        {
            //databind sql query with values from gui
            statement.setString(1,customer.getCustomerFirstName());
            statement.setString(2,customer.getCustomerLastName());
            statement.setString(3,customer.getCustomerAddress());
            statement.setString(4,customer.getCustomerCity());
            statement.setString(5,customer.getCustomerProvince());
            statement.setString(6,customer.getCustomerPostalCode());
            statement.setString(7,customer.getCustomerCountry());
            statement.setString(8,customer.getCustomerHomePhone());
            statement.setString(9,customer.getCustomerBusPhone());
            statement.setString(10,customer.getCustomerEmail());
            statement.setObject(11,customer.getAssignedAgentID()==0 ? null: customer.getAssignedAgentID());
            statement.setString(12,customer.getCustomerUsername());
            statement.setString(13,customer.getCustomerPassword());
            statement.setInt(14,customer.getCustomerID());
            //System.out.println(statement);
            //execute update
            int affected = statement.executeUpdate();
            if(affected ==1){
                return true;
            }else{
                return false;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getStackTrace());
            return false;
        }
    }

    //method t verify if an input is an integer value
    public static boolean isNumeric(String strNum) {
        try {
            int d = Integer.parseInt(strNum);
        } catch (NumberFormatException | NullPointerException nfe) {
            return false;
        }
        return true;
    }

    public static Customer getCustomerDetails(int id) {
        Customer cust = null;
        try (Connection connection = DBConnectionManager.getDBConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * from customers where CustomerId =?",
                     ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);) {
            statement.setString(1, String.valueOf(id));
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    cust = new Customer();
                    cust.setCustomerID(resultSet.getInt("CustomerId"));
                    cust.setCustomerFirstName(resultSet.getString("CustFirstName"));
                    cust.setCustomerLastName(resultSet.getString("CustLastName"));
                    cust.setCustomerAddress(resultSet.getString("CustAddress"));
                    cust.setCustomerCity(resultSet.getString("CustCity"));
                    cust.setCustomerProvince(resultSet.getString("CustProv"));
                    cust.setCustomerPostalCode(resultSet.getString("CustPostal"));
                    cust.setCustomerCountry(resultSet.getString("CustCountry"));
                    cust.setCustomerHomePhone(resultSet.getString("CustHomePhone"));
                    cust.setCustomerBusPhone(resultSet.getString("CustBusPhone"));
                    cust.setCustomerEmail(resultSet.getString("CustEmail"));
                    cust.setAssignedAgentID(resultSet.getInt("AgentId"));

                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return cust;
    }
}
