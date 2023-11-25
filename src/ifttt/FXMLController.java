/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ifttt;

import Action.*;
import ActionHandlers.*;
import Condition.*;
import ConditionHandlers.*;
import Rule.checkRules;
import Rule.Rule;
import Rule.RulesSet;
import java.io.File;
import java.net.URL;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
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
    private TableView<Rule> tableView;
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
    private final String[] possibleHours = {" ","00","01","02","03","04","05","06","07","08","09","10","11","12","13","14",
                                            "15","16","17","18","19","20","21","22","23"};
    @FXML
    private ChoiceBox<String> minutesChoiceBox;
    private final String[] possibleMinutes = {" ","00","01","02","03","04","05","06","07","08","09","10","11","12","13","14",
                                            "15","16","17","18","19","20","21","22","23", "24","25","26","27","28","29","30","31","32","33",
                                            "34","35","36","37","38", "39","40","41","42","43","44","45","46","47","48","49","50","51","52", 
                                            "53","54","55","56","57","58","59" };
    @FXML
    private TextField ruleName;
    
    private String name,condition,action;
    @FXML
    private TableColumn<Rule, String> ruleColumn;
    @FXML
    private TableColumn<Rule, String> StateColumn;
    
    @FXML
    private MenuItem deleteRuleId;
    @FXML
    private MenuItem activeRuleId;
    @FXML
    private MenuItem inactiveRuleId;
    
    private ObservableList<Rule> ruleList;
    private RulesSet rulesSet = new RulesSet();
    private checkRules checkRules = new checkRules(rulesSet);
    Thread checkingRules = new Thread(checkRules);
    
    BaseActionHandler baseActionHandler = new BaseActionHandler();
    BaseConditionHandler baseConditionHandler = new BaseConditionHandler();
    
    
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
        
        //Setting iniziale TableView
        ruleList = new SimpleListProperty<>(FXCollections.observableArrayList(rulesSet.getRuleList()));

        //ruleList = FXCollections.observableArrayList(rulesSet.getRuleList())
        tableView.setItems(ruleList);
       
        
        ruleColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        StateColumn.setCellValueFactory(cellData -> {
        Rule rule = cellData.getValue();
        String stato = rule.isActive() ? "Attivo" : "Inattivo";
        return new SimpleStringProperty(stato);
        });
        
        
        
        
        
        //Popolamento choiche boxes
        actionChoiceBox.getItems().addAll(possibleActions);
        actionChoiceBox.setOnAction(this::getAction);
        conditionChoiceBox.getItems().addAll(possibleConditions);
        conditionChoiceBox.setOnAction(this::getCondition);
        
        hourChoiceBox.getItems().addAll(possibleHours);
        minutesChoiceBox.getItems().addAll(possibleMinutes);
        
        
        //Creazione della catena delle responsabilità per le azioni
        AudioActionHandler audioHandler = new AudioActionHandler();
        DialogBoxActionHandler dialogBoxHandler = new DialogBoxActionHandler();
        baseActionHandler.setNext(audioHandler);
        audioHandler.setNext(dialogBoxHandler);
        
        //Creazione della catena delle responsabilità per le azioni
        TimeConditionHandler timeHandler = new TimeConditionHandler();
        baseConditionHandler.setNext(timeHandler);
        
        //TO CHECK
        //Creazione e avvio del thread che controlla le regole
        
        checkingRules.start();
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
        ruleName.clear();
        actionLabel.setText("");
        conditionLabel.setText("");
        actionChoiceBox.setValue("Seleziona un'azione");
        conditionChoiceBox.setValue("Seleziona una condizione");
    }

    @FXML
    private void addRule(ActionEvent event) {
       //Prendi i parametri e crea l'azione scelta
       String actionString = actionLabel.getText();
       String []actionParam;
       actionParam = actionString.split(" : ");
       Action act = baseActionHandler.handle(actionParam[0], actionParam[1]);
       act.executeAction();
       //Prendi i parametri e crea la condizione scelta con il relativo trigger
       String conditionString = conditionLabel.getText();
       String []conditionParam;
       conditionParam = conditionString.split(" : ");
       Condition cond = baseConditionHandler.handle(conditionParam[0], conditionParam[1]);
       Trigger trigger = new Trigger(cond);
       
       //Prendi il nome per la nuova regola e creala
       String name1 = ruleName.getText();       
       Rule rule = new Rule(name1, trigger, act);
       
       //Aggiungi la regola la set delle regole
       rulesSet.add(rule);
       ruleList.add(rule); 
       // si disabilita nel menù la possibilità di rendere attiva la regola selezionata
       activeRuleId.setDisable(true);
       //ruleList.setAll(rulesSet.getRuleList())
       //Ripulisci l'interfaccia
       ruleName.clear();
       actionLabel.setText("");
       conditionLabel.setText("");
       actionChoiceBox.setValue("");
       conditionChoiceBox.setValue("");
       newRulePage.setVisible(false);
       mainMenuPage.setVisible(true);
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
        conditionLabel.setText("Alle : " + time);
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

    @FXML
    private void deleteRule(ActionEvent event) {
        ruleList.remove(tableView.getSelectionModel().getSelectedItem());
    }

    @FXML
    private void activeRule(ActionEvent event) {
        // si setta lo stato della regola selezionata come attiva
        tableView.getSelectionModel().getSelectedItem().setActive(true);
        // si abilita nel menù la possibilità di rendere inattiva la regola selezionata
        inactiveRuleId.setDisable(false);
        // si disabilita nel menù la possibilità di rendere attiva la regola selezionata
        activeRuleId.setDisable(true);
        tableView.refresh();
    }

    @FXML
    private void inactiveRule(ActionEvent event) {
        // si setta lo stato della regola selezionata come inattiva
        tableView.getSelectionModel().getSelectedItem().setActive(false);
        // si disabilita nel menù la possibilità di rendere inattiva la regola selezionata
        inactiveRuleId.setDisable(true);
        // si abilita nel menù la possibilità di rendere attiva la regola selezionata
        activeRuleId.setDisable(false);
        tableView.refresh();

    }
    
           
    
}
