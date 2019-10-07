package travelexperts.dbhandler;

import travelexperts.models.Agent;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;

//this class is defined to handle agent table CRUD operations
public class AgentDBHandler {

    //method to retrieve agent IDs from database
    public static ArrayList<Integer> getAgentIds() throws Exception{
        ArrayList<Integer> agentIds = new ArrayList<Integer>();

        try(Connection conn = DBConnectionManager.getDBConnection();
            Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = stmt.executeQuery("SELECT AgentId FROM agents")
        ) {
            //System.out.println("connected!");
            //loop through the result set and add the ids to the list
            while(rs.next()){
                agentIds.add(rs.getInt("AgentId"));
            }
            //sort the list
            Collections.sort(agentIds);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new SQLException();
        }
        return agentIds;
    }

    //method to retrieve agent details
    public static Agent getAgentDetails(int id) {

        try (Connection connection = DBConnectionManager.getDBConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM agents WHERE AgentId=?;",
                     ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);) {
            statement.setInt(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                //gather agent details and store them in an agent object
                if(resultSet.next()){
                    Agent agent = new Agent();
                    agent.setAgentID(resultSet.getInt("AgentId"));
                    agent.setAgentAgencyID(resultSet.getInt("AgencyId"));
                    agent.setAgentPosition(resultSet.getString("AgtPosition"));
                    agent.setAgentFirstName(resultSet.getString("AgtFirstName"));
                    agent.setAgentMidInitial(resultSet.getString("AgtMiddleInitial"));
                    agent.setAgentLastName(resultSet.getString("AgtLastName"));
                    agent.setAgentPhoneNumber(resultSet.getString("AgtBusPhone"));
                    agent.setAgentEmail(resultSet.getString("AgtEmail"));
                    agent.setAgentUsername(resultSet.getString("AgtUsername"));
                    agent.setAgentPassword(resultSet.getString("AgtPassword"));
                    return agent;
                }else{
                    return null;
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getStackTrace());
            return null;
        }
    }

    //method to update the database with updated agent data
    public static boolean updateAgentDetails(Agent agent) {

        try (Connection connection = DBConnectionManager.getDBConnection();
             PreparedStatement statement = connection.prepareStatement("UPDATE agents SET AgtFirstName=?, " +
                     "AgtMiddleInitial=?, AgtLastName=?, AgtBusPhone=?, AgtEmail=?, AgtPosition=?, AgencyId=? WHERE AgentId=?"))
        {
            //databind sql query with values from gui
            statement.setString(1,agent.getAgentFirstName());
            statement.setString(2,agent.getAgentMidInitial());
            statement.setString(3,agent.getAgentLastName());
            statement.setString(4,agent.getAgentPhoneNumber());
            statement.setString(5,agent.getAgentEmail());
            statement.setString(6,agent.getAgentPosition());
            //if agency id is 0(i.e, user left the agency text field empty in gui), set the AgencyId to NULL.
            statement.setObject(7,agent.getAgentAgencyID()==0 ? null: agent.getAgentAgencyID() );
            statement.setInt(8, agent.getAgentID());
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

    //this method retrieves the agent password for a given username
    public static String Login(String usrname) {
        String password=null;
        try (Connection connection = DBConnectionManager.getDBConnection();
             PreparedStatement statement =connection.prepareStatement("SELECT AgtPassword from agents where AgtUsername =?",
                     ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);) {
            statement.setString(1, usrname);
            try(ResultSet resultSet =statement.executeQuery()){
                while (resultSet.next()) {
                    password=resultSet.getString("AgtPassword");
                }
            }
        }
        catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        return password;
    }

    //this method retrieves the agent details for a given username
    public static Agent getAgentDetailsByUsername(String usrname) {
        Agent agent = new Agent();
        try (Connection connection = DBConnectionManager.getDBConnection();
             PreparedStatement statement =connection.prepareStatement("SELECT * from agents where AgtUsername =?",
                     ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);) {
            statement.setString(1, usrname);
            try(ResultSet resultSet =statement.executeQuery()){
                if (resultSet.next()) {
                    agent.setAgentID(resultSet.getInt("AgentId"));
                    agent.setAgentAgencyID(resultSet.getInt("AgencyId"));
                    agent.setAgentPosition(resultSet.getString("AgtPosition"));
                    agent.setAgentFirstName(resultSet.getString("AgtFirstName"));
                    agent.setAgentMidInitial(resultSet.getString("AgtMiddleInitial"));
                    agent.setAgentLastName(resultSet.getString("AgtLastName"));
                    agent.setAgentPhoneNumber(resultSet.getString("AgtBusPhone"));
                    agent.setAgentEmail(resultSet.getString("AgtEmail"));
                    agent.setAgentUsername(resultSet.getString("AgtUsername"));
                    agent.setAgentPassword(resultSet.getString("AgtPassword"));
                }
            }
        }
        catch(SQLException ex){
            System.out.println(ex.getMessage());
            return null;
        }
        return agent;
    }

}
