package ifttt;


import Action.DialogBoxAction;
import Action.*;
import ActionHandlers.*;
import Condition.*;
import ConditionHandlers.*;
import Rule.Rule;
import Rule.RulesSet;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * La classe FXMLController è un controller per la gestione dell'interfaccia utente definita in un file FXML. 
 * Questo controller gestisce la configurazione delle regole di automazione, l'interazione con gli elementi dell'interfaccia 
 * utente e il controllo delle regole attive.
 *
 * @author Nicola Lanzara
 * @author Palma Orlando 
 * @author Simone Pacifico
 * @author Miriam Vitolo
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
    private ChoiceBox<String> controlChoiceBox;
    private final String[] possibleControls = { "Sempre", "Una volta", "Periodicamente"};
     
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
    private Button sleepOK;
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
    private AnchorPane sleepingPeriod;
    @FXML
    private DatePicker sleepDate;
    @FXML
    private ComboBox<String> sleepHours;
    private final String[] sh = {"00","01","02","03","04","05","06","07","08","09","10","11","12","13","14",
                                            "15","16","17","18","19","20","21","22","23"};
    @FXML
    private ComboBox<String> sleepMinute;
    private final String[] sm = {"00","01","02","03","04","05","06","07","08","09","10","11","12","13","14",
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
    
    Task<Void> checkingRulesTask = new Task<Void>() {
            @Override
            protected Void call(){
                while (true) { 
                    System.out.println("Thread vivo");
                    for (Rule rule: rulesSet.getRuleList()){
                        if(rule.isActive()){
                            if (rule.getTrigger().checkTrigger() ){
                                
                                Platform.runLater(new Runnable(){
                                    @Override public void run(){
                                    rule.getAction().executeAction();
                                    }
                                });
                            } 
                        }

                   }
                  try {
                        // Dormi per 5 secondi
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                
            }
    };
    //private checkRules checkRules = new checkRules(rulesSet);
    Thread checkingRulesThread = new Thread(checkingRulesTask);
    
    BaseActionHandler baseActionHandler = new BaseActionHandler();
    BaseConditionHandler baseConditionHandler = new BaseConditionHandler();
   
    
    /**
     * Metodo chiamato quando viene inizializzata l'interfaccia utente.
     * Inizializza i componenti grafici, popola le choice boxes, crea le catene di responsabilità e avvia il thread per il controllo
     * periodico delle regole.
     * 
     * @param url l'URL della radice dell'oggetto dell'archivio FXML.
     * @param rb risorse specifiche regionalmente.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Mostra la schermata iniziale
        mainMenuPage.setVisible(true);
        newRulePage.setVisible(false);
        chooseMessagePage.setVisible(false);
        chooseHourPage.setVisible(false);
        sleepingPeriod.setVisible(false);
        
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
        controlChoiceBox.getItems().addAll(possibleControls);
        controlChoiceBox.setOnAction(this::getControl);
        
        hourChoiceBox.getItems().addAll(possibleHours);
        minutesChoiceBox.getItems().addAll(possibleMinutes);
        
        //Popolamento ComboBox ore e minuti per lo spleeping time 
        sleepHours.getItems().addAll(sh);
        sleepMinute.getItems().addAll(sm);
        
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
        checkingRulesThread.setDaemon(true);
        checkingRulesThread.start();
    }    

    /**
     *  Mostra la pagina di aggiunta regola nascondendo le altre pagine e reimpostando le scelte predefinite.
     * 
     * @param event l'evento scatenato da un'azione utente.
     */
    @FXML
    private void showAddPage(ActionEvent event) {
        mainMenuPage.setVisible(false);
        newRulePage.setVisible(true);
        chooseMessagePage.setVisible(false);
        actionChoiceBox.setValue("Seleziona un'azione");
        conditionChoiceBox.setValue("Seleziona una condizione");
        controlChoiceBox.setValue("Seleziona un controllo");
    }

    /**
     * Torna alla pagina principale nascondendo le altre pagine e ripristinando i valori predefiniti.
     * 
     * @param event l'evento scatenato da un'azione utente.
     */
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
        controlChoiceBox.setValue("Seleziona un controllo");
    }

    /**
     * Aggiunge una nuova regola con i parametri forniti, crea l'azione e la condizione corrispondenti. Aggiunge la 
     * regola alla lista delle regole e disabilita la possibilità di rendere attiva la regola selezionata nel menu.
     * 
     * @param event l'evento scatenato da un'azione utente.
     */
    @FXML
    private void addRule(ActionEvent event) {
       //Prendi i parametri e crea l'azione scelta
       String actionString = actionLabel.getText();
       String []actionParam;
       actionParam = actionString.split(" : ");
       Action act = baseActionHandler.handle(actionParam[0], actionParam[1]);
       System.out.println( act== null);
       //Prendi i parametri e crea la condizione scelta con il relativo trigger
       String conditionString = conditionLabel.getText();
       String []conditionParam;
       conditionParam = conditionString.split(" : ");
       Condition cond = baseConditionHandler.handle(conditionParam[0], conditionParam[1]);
       Trigger trigger = new Trigger(cond);
       
       //Prendi il nome per la nuova regola e creala
       String name1 = ruleName.getText();       
       Rule rule = new Rule(name1, trigger, act);
       
       //Aggiungi la regola al set delle regole
       rulesSet.getRuleList().add(rule);
       ruleList.add(rule); 
       
       System.out.println(rulesSet.getRuleList().toString());
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

    /**
     * Gestisce l'azione di conferma del messaggio inserito. Se il messaggio è vuoto, visualizza un avviso, altrimenti crea 
     * un oggetto @see DialogBoxActioncon il messaggio e aggiorna l'etichetta dell'azione nella pagina delle nuove regole.
     * 
     * @param event l'evento scatenato da un'azione utente.
     */
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
            DialogBoxAction d = new DialogBoxAction(message);
            //d.executeAction(message, primaryStage);

            // Pulisci il TextField dopo l'aggiunta
            userMessage.clear();
            // Nascondi DialogBox
            chooseMessagePage.setVisible(false);
            newRulePage.setVisible(true);
            actionLabel.setText("Mostra il messaggio : " + message);
            
        }
    }
 
    /**
     * Gestisce l'evento di selezione di un'azione dalla ChoiceBox.
     * Mostra la pagina corrispondente all'azione selezionata o imposta l'etichetta dell'azione nella pagina delle nuove regole.
     * 
     * @param event l'evento scatenato dalla selezione di un'azione dalla ChoiceBox.
     */
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
    
  
    /**
     * Gestisce l'evento di selezione di una condizione dalla ChoiceBox.
     * Mostra la pagina corrispondente alla condizione selezionata o imposta l'etichetta della condizione nella pagina 
     * delle nuove regole.
     * 
     * @param event l'evento scatenato dalla selezione di una condizione dalla ChoiceBox.
     */
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
    
    /**
     * Gestisce l'evento di selezione di un controllo dalla ChoiceBox.
     * Mostra la pagina corrispondente al controllo selezionata o imposta l'etichetta del controllo
     * nella pagina delle nuove regole.
     * 
     * @param event l'evento scatenato dalla selezione di un controllo dalla ChoiceBox.
     */
     public void getControl(ActionEvent event) {
        String control = controlChoiceBox.getValue();
        if(control.compareTo("Sempre") == 0){
                   
        }
        if(control.compareTo("Una volta") == 0){

            
        }
        if(control.compareTo("Periodicamente") == 0){
            sleepingPeriod.setVisible(true);
            newRulePage.setVisible(false);
        }
        if(control.compareTo("Seleziona un controllo") == 0){
                
        }
    }

    /**
     * Gestisce l'evento di ritorno alla pagina delle nuove regole dalla pagina principale.
     * Nasconde la pagina principale, visualizza la pagina delle nuove regole e reimposta l'etichetta dell'azione e 
     * la ChoiceBox delle azioni.
     * 
     * @param event l'evento scatenato da un'azione utente.
     */
    @FXML
    private void showAddPageBack(ActionEvent event) {
        mainMenuPage.setVisible(false);
        newRulePage.setVisible(true);
        chooseMessagePage.setVisible(false);
        actionLabel.setText("");
        actionChoiceBox.setValue("Seleziona un'azione");
    }

    /**
     * Gestisce l'evento di conferma dell'orario selezionato. 
     * Ottiene l'orario e i minuti scelti dalle rispettive ChoiceBox, compone una stringa nel formato "HH:mm" e la visualizza
     * nell'etichetta delle condizioni. Nasconde la pagina della scelta dell'orario e visualizza la pagina delle nuove regole.
     * 
     * @param event l'evento scatenato da un'azione utente.
     */
    @FXML
    private void confirmHour(ActionEvent event) {
        String hour = hourChoiceBox.getValue();
        String minutes = minutesChoiceBox.getValue();
        String time = hour + ":" + minutes;
        conditionLabel.setText("Alle : " + time);
        chooseHourPage.setVisible(false);
        newRulePage.setVisible(true);
    }

    /**
     * Gestisce l'evento di ritorno alla pagina delle nuove regole dalla pagina della scelta dell'orario.
     * Nasconde la pagina dell'orario, visualizza la pagina delle nuove regole e reimposta l'etichetta della condizione 
     * e la ChoiceBox delle condizioni.
     * 
     * @param event l'evento scatenato da un'azione utente.
     */
    @FXML
    private void showAddPageBack1(ActionEvent event) {
        chooseHourPage.setVisible(false);
        newRulePage.setVisible(true);
        conditionLabel.setText("");
        conditionChoiceBox.setValue("Seleziona una condizione");
    }

    /**
     * Gestisce l'evento di eliminazione di una regola. 
     * Rimuove la regola selezionata dalla lista delle regole e dall'elenco visualizzato nella TableView.
     * 
     * @param event l'evento scatenato da un'azione utente.
     */
    @FXML
    private void deleteRule(ActionEvent event) {
        Rule rule = tableView.getSelectionModel().getSelectedItem();
        rulesSet.getRuleList().remove(rule);
        ruleList.remove(rule);
    }

    /**
     * Gestisce l'evento di attivazione di una regola. 
     * Imposta lo stato della regola selezionata come attivo, abilita l'opzione per disattivare la regola e disabilita l'opzione 
     * per attivarla. Aggiorna la TableView per riflettere lo stato aggiornato della regola.
     * 
     * @param event l'evento scatenato da un'azione utente.
     */
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

    /**
     * Gestisce l'evento di disattivazione di una regola. 
     * Imposta lo stato della regola selezionata come inattivo, abilita l'opzione per attivare la regola e disabilita l'opzione 
     * per disattivarla. Aggiorna la TableView per riflettere lo stato aggiornato della regola.
     * 
     * @param event l'evento scatenato da un'azione utente.
     */
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

    @FXML
    private void confirmSleepingPeriod(ActionEvent event) {
        
    }
    
}
