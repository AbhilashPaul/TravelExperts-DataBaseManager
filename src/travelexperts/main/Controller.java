package travelexperts.main;

import travelexperts.dbhandler.AgentDBHandler;
import travelexperts.models.AgenciesUtil;
import travelexperts.models.Agent;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.util.HashMap;

public class Controller {
    @FXML
    public TextField tfEmail;
    @FXML
    public TextField tfLastName;
    @FXML
    public TextField tfMiddleInitial;
    @FXML
    public TextField tfFirstName;
    @FXML
    public TextField tfPosition;
    @FXML
    private TextField tfAgency;
    @FXML
    private TextField tfPhone;
    @FXML
    private ComboBox<Integer> cboAgentId;
    @FXML
    private Button btnEdit;
    @FXML
    private Button btnSave;
    @FXML
    private Text txtUserFeedback;

    @FXML
    void initialize() {
        //bind combobox with agent ids
        dataBindComboBox();
        //adds change event listener to the combobox
        cboAgentId.valueProperty().addListener(new ChangeListener<Integer>() {
            @Override
            public void changed(ObservableValue<? extends Integer> observable, Integer oldValue, Integer newValue) {
                //retrieve agent details and display it in the gui
                Agent agent = AgentDBHandler.getAgentDetails(newValue);
                if(agent != null){
                    displayAgentDetails(agent);
                }else{
                    txtUserFeedback.setText("No record found!");
                }
                setTextFieldsEditable(false);       //make text fields non-editable
                txtUserFeedback.setText("");        //clears user feedback
                btnEdit.setDisable(false);          //enable edit button
                btnSave.setDisable(true);           //disable save button
            }
        });
    }

    //Edit button click event handler
    @FXML
    void onActionBtnEdit(ActionEvent event) {
        setTextFieldsEditable(true);            //make tet fields editable
        txtUserFeedback.setText("");            //clears user feedback
        btnSave.setDisable(false);              //enable save button
    }

    //Save button click event handler
    @FXML
    void onActionBtnSave(ActionEvent event) {
        //get values from gui
        Agent agent = new Agent();
        agent.setAgentID(cboAgentId.valueProperty().getValue());
        agent.setAgentFirstName(tfFirstName.getText());
        agent.setAgentMidInitial(tfMiddleInitial.getText());
        agent.setAgentLastName(tfLastName.getText());
        agent.setAgentEmail(tfEmail.getText());
        agent.setAgentPhoneNumber(tfPhone.getText());
        //create agency name and id hashmap
        HashMap<Integer,String> agencies = AgenciesUtil.createAgencyList();
        if(agencies.containsValue(tfAgency.getText())){                                //if the agency name exist in the hashmap,
            agent.setAgentAgencyID(AgenciesUtil.getAgencyId(agencies,tfAgency.getText())); //get the agency id for the agency name
        }else{                                                                         //agency name is not found in hashmap
            agent.setAgentAgencyID(0);                                                 //set agency id to 0,
        }
        agent.setAgentPosition(tfPosition.getText());
        //update the agent record in database
        AgentDBHandler.updateAgentDetails(agent);
        if(AgentDBHandler.updateAgentDetails(agent)){
            txtUserFeedback.setFill(Color.GREEN);
            txtUserFeedback.setText("Successfully updated agent details!");
            btnSave.setDisable(true);
            setTextFieldsEditable(false);
        }else{
            txtUserFeedback.setFill(Color.MAROON);
            txtUserFeedback.setText("Error while trying to update. Please try again!");
        }
    }

    //this method retrieves agent ids from database and bind them to combo box
    private void dataBindComboBox() {
        ObservableList<Integer> agentIdList = FXCollections.observableArrayList();
        try{
            agentIdList.addAll(AgentDBHandler.getAgentIds());
            cboAgentId.setItems(agentIdList);
        } catch (Exception e) {
            txtUserFeedback.setFill(Color.MAROON);
            txtUserFeedback.setText("Unable to connect to the database!");
        }
    }

    //display agent details on gui
    private void displayAgentDetails(Agent agent){
        tfFirstName.setText(agent.getAgentFirstName());
        tfLastName.setText(agent.getAgentLastName());
        tfMiddleInitial.setText(agent.getAgentMidInitial());
        tfEmail.setText(agent.getAgentEmail());
        HashMap<Integer,String> agencies = AgenciesUtil.createAgencyList();
        tfAgency.setText(agencies.get(agent.getAgentAgencyID()));
        tfPosition.setText(agent.getAgentPosition());
        tfPhone.setText(agent.getAgentPhoneNumber());
    }

    //method to set the editable property of text fields
    private void setTextFieldsEditable(boolean b) {
        tfFirstName.setEditable(b);
        tfMiddleInitial.setEditable(b);
        tfLastName.setEditable(b);
        tfEmail.setEditable(b);
        tfPhone.setEditable(b);
        tfPosition.setEditable(b);
        tfAgency.setEditable(b);
    }
}