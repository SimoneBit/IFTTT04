/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ifttt;

import Rule.Rule;
import java.io.File;
import java.net.URL;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Simone
 */
public class FXMLController implements Initializable {

    @FXML
    private TableView<Rule> ruleList;
    @FXML
    private Button AddRule;
    @FXML
    private AnchorPane mainMenuPage;
    @FXML
    private AnchorPane newRulePage;
    @FXML
    private Button addRuleButton;
    @FXML
    private ChoiceBox<String> actionChoiceBox;
    private final String[] possibleActions = {"Seleziona un'azione","Fai partire un audio","Mostra un messaggio"};
    @FXML
    private ChoiceBox<String> conditionChoiceBox;
    private final String[] possibleConditions = {"Seleziona una condizione","Orario specifico"};

    @FXML
    private AnchorPane chooseMessagePage;
    @FXML
    private Button confirmMessageButton;
    @FXML
    private Label insertMessage;
    @FXML
    private TextField userMessage;
    @FXML
    private Button backButton1;
    @FXML
    private Button backButton2;
    @FXML
    private Label actionLabel;
    @FXML
    private Label conditionLabel;
    @FXML
    private AnchorPane chooseHourPage;
    @FXML
    private ChoiceBox<String> hourChoiceBox;
    private final String[] possibleHours = {"00","01","02","03","04","05","06","07","08","09","10","11","12","13","14",
                                            "15","16","17","18","19","20","21","22","23"};
    @FXML
    private ChoiceBox<String> minutesChoiceBox;
    private final String[] possibleMinutes = {"00","01","02","03","04","05","06","07","08","09","10","11","12","13","14",
                                            "15","16","17","18","19","20","21","22","23"};
    @FXML
    private TextField ruleName;
    
    private String name,condition,action;
    @FXML
    private TableColumn<Rule, String> ruleColumn;
    @FXML
    private TableColumn<Rule, String> StateColumn;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Mostra la schermata iniziale
        mainMenuPage.setVisible(true);
        newRulePage.setVisible(false);
        chooseMessagePage.setVisible(false);
        chooseHourPage.setVisible(false);
        
        
        //Popolamento choiche boxes
        actionChoiceBox.getItems().addAll(possibleActions);
        actionChoiceBox.setOnAction(this::getAction);
        conditionChoiceBox.getItems().addAll(possibleConditions);
        conditionChoiceBox.setOnAction(this::getCondition);
        
        hourChoiceBox.getItems().addAll(possibleHours);
        minutesChoiceBox.getItems().addAll(possibleMinutes);
    }    

    @FXML
    private void showAddPage(ActionEvent event) {
        mainMenuPage.setVisible(false);
        newRulePage.setVisible(true);
        chooseMessagePage.setVisible(false);
        actionChoiceBox.setValue("Seleziona un'azione");
        conditionChoiceBox.setValue("Seleziona una condizione");
    }

    @FXML
    private void showMainMenuPage(ActionEvent event) {
        mainMenuPage.setVisible(true);
        newRulePage.setVisible(false);
        chooseMessagePage.setVisible(false);
        actionLabel.setText("");
        conditionLabel.setText("");
        actionChoiceBox.setValue("Seleziona un'azione");
        conditionChoiceBox.setValue("Seleziona una condizione");
    }

    @FXML
    private void addRule(ActionEvent event) {
        name= ruleName.getText();
        condition= conditionChoiceBox.getValue();
        action =actionChoiceBox.getValue();
        
        
    }

    @FXML
    private void confirmMessage(ActionEvent event) {
        String message= userMessage.getText();
        
        if(message.equals("")){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Messaggio di Avviso");
            alert.setHeaderText(null);
            alert.setContentText("Inserisci il messaggio che vuoi ti venga mostrato");
            alert.showAndWait();
            message= userMessage.getText();
        
        }else{
            System.out.println(message);
            //DialogBoxAction d = new DialogBoxAction(message);
            //d.executeAction(message, primaryStage);
            // Pulisci il TextField dopo l'aggiunta
            userMessage.clear();
            // Nascondi DialogBox
            chooseMessagePage.setVisible(false);
            newRulePage.setVisible(true);
            actionLabel.setText("Mostra il messaggio : " + message);
            
        }
    }
    
    public void getAction(ActionEvent event){
        String action = actionChoiceBox.getValue();
        if(action.compareTo("Mostra un messaggio") == 0){
            newRulePage.setVisible(false);
            chooseMessagePage.setVisible(true);          
        }
        if(action.compareTo("Fai partire un audio") == 0){

            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Audio Files","*.wav"));
            File selectedFile = fileChooser.showOpenDialog(new Stage());
            actionLabel.setText("Riproduci il file : " + selectedFile.toString());
            
        }
        if(action.compareTo("Seleziona un'azione") == 0){
            actionLabel.setText("");         
        }
    }
    
    
    public void getCondition(ActionEvent event){
        String condition = conditionChoiceBox.getValue();
        if(condition.compareTo("Seleziona una condizione") == 0){
            conditionLabel.setText("");         
        }
        if(condition.compareTo("Orario specifico") == 0){
            chooseHourPage.setVisible(true);
            newRulePage.setVisible(false);
        }
    }

    @FXML
    private void showAddPageBack(ActionEvent event) {
        mainMenuPage.setVisible(false);
        newRulePage.setVisible(true);
        chooseMessagePage.setVisible(false);
        actionLabel.setText("");
        actionChoiceBox.setValue("Seleziona un'azione");
    }

    @FXML
    private void confirmHour(ActionEvent event) {
        String hour = hourChoiceBox.getValue();
        String minutes = minutesChoiceBox.getValue();
        String time = hour + ":" + minutes;
        conditionLabel.setText("Alle " + time);
        chooseHourPage.setVisible(false);
        newRulePage.setVisible(true);
    }

    @FXML
    private void showAddPageBack1(ActionEvent event) {
        chooseHourPage.setVisible(false);
        newRulePage.setVisible(true);
        conditionLabel.setText("");
        conditionChoiceBox.setValue("Seleziona una condizione");
    }
    
   
           
    
}
