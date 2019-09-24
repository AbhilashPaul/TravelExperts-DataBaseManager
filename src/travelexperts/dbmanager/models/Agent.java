package travelexperts.dbmanager.models;

//this class defines an agent bean
public class Agent {

    //fields
    private int agentID;
    private int agentAgencyID;
    private String agentPosition;
    private String agentFirstName;
    private String agentMidInitial;
    private String agentLastName;
    private String agentPhoneNumber;
    private String agentEmail;
    private String agentUsername;
    private String agentPassword;

    //getters and setters
    public int getAgentID() {
        return agentID;
    }
    public void setAgentID(int agentID) {
        this.agentID = agentID;
    }
    public int getAgentAgencyID() {
        return agentAgencyID;
    }
    public void setAgentAgencyID(int agentAgencyID) {
        this.agentAgencyID = agentAgencyID;
    }
    public String getAgentPosition() {
        return agentPosition;
    }
    public void setAgentPosition(String agentPosition) {
        this.agentPosition = agentPosition;
    }
    public String getAgentFirstName() {
        return agentFirstName;
    }
    public void setAgentFirstName(String agentFirstName) {
        this.agentFirstName = agentFirstName;
    }
    public String getAgentMidInitial() {
        return agentMidInitial;
    }
    public void setAgentMidInitial(String agentMidInitial) {
        this.agentMidInitial = agentMidInitial;
    }
    public String getAgentLastName() {
        return agentLastName;
    }
    public void setAgentLastName(String agentLastName) {
        this.agentLastName = agentLastName;
    }
    public String getAgentPhoneNumber() {
        return agentPhoneNumber;
    }
    public void setAgentPhoneNumber(String agentPhoneNumber) {
        this.agentPhoneNumber = agentPhoneNumber;
    }
    public String getAgentEmail() {
        return agentEmail;
    }
    public void setAgentEmail(String agentEmail) {
        this.agentEmail = agentEmail;
    }
    public String getAgentUsername() {
        return agentUsername;
    }
    public void setAgentUsername(String agentUsername) {
        this.agentUsername = agentUsername;
    }
    public String getAgentPassword() {
        return agentPassword;
    }
    public void setAgentPassword(String agentPassword) {
        this.agentPassword = agentPassword;
    }

    //constructors
    public Agent() {
    }

    public Agent(int agentID, int agentAgencyID, String agentPosition, String agentFirstName,
                 String agentMidInitial, String agentLastName, String agentPhoneNumber,
                 String agentEmail, String agentUsername, String agentPassword) {
        this.agentID = agentID;
        this.agentAgencyID = agentAgencyID;
        this.agentPosition = agentPosition;
        this.agentFirstName = agentFirstName;
        this.agentMidInitial = agentMidInitial;
        this.agentLastName = agentLastName;
        this.agentPhoneNumber = agentPhoneNumber;
        this.agentEmail = agentEmail;
        this.agentUsername = agentUsername;
        this.agentPassword = agentPassword;
    }
}

