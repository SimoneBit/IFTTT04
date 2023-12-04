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
import java.util.ArrayList;
import java.util.List;
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
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.DirectoryChooser;
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
    private final String[] possibleActions = {"Seleziona un'azione","Fai partire un audio","Mostra un messaggio", 
                                                                       "Scrivi su un file", "Copia un file", "Sposta un file", "Elimina un file"};
    @FXML
    private ChoiceBox<String> conditionChoiceBox;
    private final String[] possibleConditions = {"Seleziona una condizione","Orario specifico","Giorno della settimana", 
                                                                            "Giorno del mese", "Giorno dell'anno", "File esiste" ,"Dimensione del file"};
    @FXML
    private ChoiceBox<String> controlChoiceBox;
    private final String[] possibleControls = {"Seleziona un controllo", "Sempre", "Una volta", "Periodicamente"};
    
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
    private ComboBox<String> hourComboBox;
    private final String[] possibleHours = {"00","01","02","03","04","05","06","07","08","09","10","11","12","13","14",
                                            "15","16","17","18","19","20","21","22","23"};
    @FXML
    private ComboBox<String> minutesComboBox;
    private final String[] possibleMinutes = {"00","01","02","03","04","05","06","07","08","09","10","11","12","13","14",
                                            "15","16","17","18","19","20","21","22","23", "24","25","26","27","28","29","30","31","32","33",
                                            "34","35","36","37","38", "39","40","41","42","43","44","45","46","47","48","49","50","51","52", 
                                            "53","54","55","56","57","58","59" };
    
     @FXML
    private AnchorPane sleepingPeriodPage;
    @FXML
    private TextField daysSleeping;
    @FXML
    private TextField hoursSleeping;
    @FXML
    private TextField minutesSleeping;
    @FXML
    private Button buttonCancel;
    @FXML
    private Button sleepOK;
    
    @FXML
    private AnchorPane chooseFileToAppendStringPage;   
    @FXML
    private Button chooseFileButton;
    @FXML
    private TextField stringToAppendTextField;
    @FXML
    private Button backButton3;
    @FXML
    private Button confirmStringButton;
    @FXML
    private Label selectedFilePathLabel;
    @FXML
    private AnchorPane copyMoveFilePage;
    @FXML
    private Button selectFileButton;
    @FXML
    private Button backButton4;
    @FXML
    private Button destinationFileButton;
    @FXML
    private Label selectedFilePathLabel2;
    @FXML
    private Label destinationFilePathLabel;
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
        @FXML
    private Label controlLabel;
    @FXML
    private AnchorPane dayAndMonthPage;
    @FXML
    private TextField dayAndMonthText;
    @FXML
    private TextField dayField;
    @FXML
    private AnchorPane dayPage;
    @FXML
    private Button confirmCopyMoveFileButton;
    @FXML
    private AnchorPane existsFilePage;
    @FXML
    private Label pathFileLabel;
    @FXML
    private Button confirmExistFileButton;
    @FXML
    private Button backButtonExists;
    @FXML
    private Label pathFile_id;
    @FXML
    private AnchorPane dimensionFilePage;
    @FXML
    private Label pathFileLabel1;
    @FXML
    private Button confirmDimensionFileButton1;
    @FXML
    private Button backButtonDimension;
    @FXML
    private Button selectFileButton2;
    @FXML
    private Label pathFile_id1;
    @FXML
    private TextField DimensionLabel;
    @FXML
    private Button selectPathButton1;
    @FXML
    private AnchorPane chooseDayWeekPage;
        @FXML
    private RadioButton LunRadioButton;
    @FXML
    private RadioButton MarRadioButton;
    @FXML
    private RadioButton MerRadioButton;
    @FXML
    private RadioButton GioRadioButton;
    @FXML
    private RadioButton VenRadioButton;
    @FXML
    private RadioButton SabRadioButton;
    @FXML
    private RadioButton DomRadioButton;
  
    
    private ObservableList<Rule> ruleList;
    private RulesSet rulesSet = new RulesSet();
    private RuleFileHandler ruleFileHandler = new RuleFileHandler("rules.bin");
    private File selectedFile;
    private File selectedDirectory;
    private boolean isCopying;
    private ToggleGroup toggleGroup;
    
    Task<Void> checkingRulesTask = new Task<Void>() {
            @Override
            protected Void call(){
                while (true) { 
                    for (Rule rule: rulesSet.getRuleList()){
                        if(rule.isActive() && !rule.isSleeping()){
                            if (rule.getTrigger().checkTrigger() ){
                                
                                Platform.runLater(new Runnable(){
                                    @Override public void run(){
                                    rule.executeRule();
                                    tableView.refresh();
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
    @FXML
    private Button confirmDayWeekButton;
    @FXML
    private Button backButton8;
    @FXML
    private TextField ExistFileTextField;
  
    
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
        sleepingPeriodPage.setVisible(false);
        chooseDayWeekPage.setVisible(false);
        dayAndMonthPage.setVisible(false);
        dayPage.setVisible(false);
        chooseFileToAppendStringPage.setVisible(false);
        copyMoveFilePage.setVisible(false);
        dimensionFilePage.setVisible(false);
        
        // Carica le regole dal file al momento dell'avvio
        loadRules();
        
        
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
        
        hourComboBox.getItems().addAll(possibleHours);
        minutesComboBox.getItems().addAll(possibleMinutes);
        
        // Creazione ToggleGroup e aggiunta dei RadioButton al gruppo
        toggleGroup = new ToggleGroup();
        LunRadioButton.setToggleGroup(toggleGroup);
        MarRadioButton.setToggleGroup(toggleGroup);
        MerRadioButton.setToggleGroup(toggleGroup);
        GioRadioButton.setToggleGroup(toggleGroup);
        VenRadioButton.setToggleGroup(toggleGroup);
        SabRadioButton.setToggleGroup(toggleGroup);
        DomRadioButton.setToggleGroup(toggleGroup);
        
        
        //Creazione della catena delle responsabilità per le azioni
        AudioActionHandler audioHandler = new AudioActionHandler();
        DialogBoxActionHandler dialogBoxHandler = new DialogBoxActionHandler();
        DeleteFileActionHandler deleteFileHandler = new DeleteFileActionHandler();
        WriteOnFileActionHandler writeOnFileHandler = new WriteOnFileActionHandler();
        CopyFileActionHandler copyFileHandler = new CopyFileActionHandler();
        MoveFileActionHandler moveFileHandler = new MoveFileActionHandler();        
        
        baseActionHandler.setNext(audioHandler);
        audioHandler.setNext(dialogBoxHandler);
        dialogBoxHandler.setNext(deleteFileHandler);
        deleteFileHandler.setNext(writeOnFileHandler);
        writeOnFileHandler.setNext(copyFileHandler);
        copyFileHandler.setNext(moveFileHandler);
        
        //Creazione della catena delle responsabilità per le condizioni
        TimeConditionHandler timeHandler = new TimeConditionHandler();
        DayOfWeekConditionHandler dayOfWeekHandler= new DayOfWeekConditionHandler();
        DayOfMonthConditionHandler dayOfMonthHandler = new DayOfMonthConditionHandler();
        DayOfYearConditionHandler dayOfYearHandler = new DayOfYearConditionHandler();
        FileExistenceConditionHandler fileExistenceHandler = new FileExistenceConditionHandler();
        FileSizeConditionHandler fileSizeHandler = new FileSizeConditionHandler();
                
        baseConditionHandler.setNext(timeHandler);
        timeHandler.setNext(dayOfMonthHandler);
        dayOfMonthHandler.setNext(dayOfYearHandler);
        timeHandler.setNext(dayOfWeekHandler);
        dayOfWeekHandler.setNext(fileExistenceHandler);
        fileExistenceHandler.setNext(fileSizeHandler);
        
        //Creazione e avvio del thread che controlla le regole
        checkingRulesThread.setDaemon(true);
        checkingRulesThread.start();        
        
        // Aggiunta di un'azione per salvare le regole quando l'applicazione viene chiusa
        Platform.runLater(() -> {
        Stage stage = (Stage) tableView.getScene().getWindow();
        stage.setOnCloseRequest(event -> saveRulesAndExit());
    });
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
        chooseFileToAppendStringPage.setVisible(false);
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
        chooseFileToAppendStringPage.setVisible(false);
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
       //Prendi i parametri e crea la condizione scelta con il relativo trigger
       String conditionString = conditionLabel.getText();
       String []conditionParam;
       conditionParam = conditionString.split(" : ");
       Condition cond = baseConditionHandler.handle(conditionParam[0], conditionParam[1]);
       Trigger trigger = new Trigger(cond);
       //Prendi i parametri e imposta la periodicità
       String controlString = controlLabel.getText();
       String []controlParam;
       controlParam = controlString.split(" : ");
       Integer sleepingTime=0;
       boolean executeOnce=false;
       if(controlParam[0].compareTo("Ogni")==0){
           String []num = controlParam[1].split("[a-zA-Z] ");
            Integer days = Integer.parseInt(num[0]);
            Integer hours = Integer.parseInt(num[1]);
            Integer minutes = Integer.parseInt(num[2]);
            sleepingTime = (days*24*60*60)+(hours*60*60)+(minutes*60);
       }
       if(controlParam[0].compareTo("Una volta ")==0){
           executeOnce=true;
   }
       
       
       //Prendi il nome per la nuova regola e creala
       String name1 = ruleName.getText();       
       Rule rule = new Rule(name1, trigger, act,sleepingTime,executeOnce); 
       
       //Aggiungi la regola al set delle regole
       rulesSet.getRuleList().add(rule);
       ruleList.add(rule); 
       System.out.println("RuleSet"+rulesSet);
       System.out.println("RuleList"+ruleList);
       
       // si disabilita nel menù la possibilità di rendere attiva la regola selezionata
       activeRuleId.setDisable(true);
       //ruleList.setAll(rulesSet.getRuleList());
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
     * un oggetto @see DialogBoxAction con il messaggio e aggiorna l'etichetta dell'azione nella pagina delle nuove regole.
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
     *Gestisce l'evento di selezione di un file attraverso un FileChooser.
    * Apre una finestra di dialogo per la scelta di un file, consentendo all'utente di navigare e selezionare un file dal 
    * sistema di file. Applica un filtro per accettare solo file con estensione .txt. e, una volta selezionato il file, aggiorna 
    * l'etichetta visualizzata nell'interfaccia utente per mostrare il percorso completo del file selezionato.
    *
    * @param event l'evento di azione scatenato dalla scelta del file.
     */
    @FXML
    private void chooseFile(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        // Impostazione di un filtro per accettare solo file con estensione .txt
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("File di testo (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);
        selectedFile = fileChooser.showOpenDialog(new Stage());
        selectedFilePathLabel.setText("File selezionato: " + selectedFile.toString());
    }
    
    /**
     * Gestisce l'evento di conferma della stringa da scrivere su un file selezionato.
     * Controlla che sia stato inserito del testo nel TextField e che sia stato selezionato un file. Se il testo e il file sono stati 
     * inseriti correttamente, imposta l'azione da eseguire e aggiorna l'etichetta nell'interfaccia utente con i dettagli dell'azione.
     * Dopo la conferma, pulisce il TextField, resetta l'etichetta del percorso del file selezionato, nasconde la pagina di 
     * selezione del file e mostra la pagina principale dell'applicazione.
     *
     * @param event l'evento di azione scatenato dalla conferma della stringa da scrivere.
     */
    @FXML
    private void confirmString(ActionEvent event){
        String stringToWrite = stringToAppendTextField.getText();
                // Controllo che sia stato inserito del testo nel TextField
        if(stringToWrite.isEmpty()){
            showAlert("Inserisci il testo che vuoi scrivere sul file selezionato", Alert.AlertType.ERROR);
            return;
        }
        // Controllo che sia stato selezionato un file prima di confermare l'azione
        if (selectedFile == null) {
        showAlert("Devi selezionare un file prima di confermare.", Alert.AlertType.ERROR);
        return;
         }
        // Setto l'azione da eseguire
        if (selectedFile != null && !stringToWrite.isEmpty()){
            actionLabel.setText("Scrivi sul file : " + selectedFile.toString() + " Testo da scrivere: " + stringToAppendTextField.getText());
            //System.out.println("File selezionato: " + selectedFile.toString() + " Testo da scrivere: " + stringToAppendTextField.getText());
        }
        
        // Pulisci il TextField dopo l'aggiunta
        stringToAppendTextField.clear();
        selectedFilePathLabel.setText("");
        // Nascondi la pagina chooseFileToAppendStringPage
        chooseFileToAppendStringPage.setVisible(false);
        // Mostra la pagina newRulePage
        newRulePage.setVisible(true);
    }
    
    /**
     * Gestisce l'evento di selezione di un file attraverso un FileChooser per le azioni di copia e spostamento.
     * Apre una finestra di dialogo per la scelta di un file consentendo all'utente di navigare e selezionare un file dal sistema 
     * di file. Una volta selezionato il file, aggiorna l'etichetta visualizzata nell'interfaccia utente per mostrare il percorso 
     * completo del file selezionato.
     *
     * @param event l'evento di azione scatenato dalla scelta del file per le azioni di copia e spostamento.
     */
    @FXML
    private void chooseFileToCopyMove(ActionEvent event){
        FileChooser fileChooser = new FileChooser();
        selectedFile = fileChooser.showOpenDialog(new Stage());
        selectedFilePathLabel2.setText("File selezionato: " + selectedFile.toString());
    }
    
    /**
     * Gestisce l'evento di selezione di una destinazione attraverso un DirectoryChooser.
     * Apre una finestra di dialogo per la scelta di una directory destinazione consentendo all'utente di navigare e selezionare 
     * una directory dal sistema di file. Una volta selezionata la directory destinazione, aggiorna l'etichetta visualizzata 
     * nell'interfaccia utente per mostrare il percorso completo della directory selezionata.
     *
     * @param event l'evento di azione scatenato dalla scelta della directory destinazione. 
     */
    @FXML
    private void chooseDestination(ActionEvent event){
        DirectoryChooser directoryChooser = new DirectoryChooser();;
        selectedDirectory = directoryChooser.showDialog(new Stage());
        destinationFilePathLabel.setText("Destinazione selezionata: " + selectedDirectory.toString()); 
    }
    
    /**
     * Gestisce l'evento di conferma dell'azione di copia o spostamento del file.
     * Controlla che sia stato selezionato un file e una destinazione prima di confermare l'azione. Se entrambi sono stati 
     * selezionati correttamente, imposta l'azione da eseguire e aggiorna l'etichetta nell'interfaccia utente con i dettagli 
     * dell'azione. Dopo la conferma, resetta le etichette del percorso del file selezionato e della destinazione,
     * nasconde la pagina di selezione del file e destinazione, e mostra la pagina principale dell'applicazione.
     *
     * @param event l'evento di azione scatenato dalla conferma dell'azione di copia o spostamento del file. 
     */
     @FXML
    private void confirmCopyMoveFile(ActionEvent event){
        // Controllo che sia stato selezionato un file prima di confermare
        if(selectedFile == null){
            showAlert("Devi selezionare un file prima di confermare.", Alert.AlertType.ERROR);
            return;
        }
        // Controllo che sia stata selezionata una destinazione prima di confermare
        if(selectedDirectory == null){
            showAlert("Devi selezionare una cartella destinazione prima di confermare.", Alert.AlertType.ERROR);
            return;
        }
        if (isCopying) {
            actionLabel.setText("Copia il file : " + selectedFile.toString() + " Path di destinazione: " + selectedDirectory.toString());                
        } else {
            actionLabel.setText("Sposta il file : " + selectedFile.toString() + " Path di destinazione: " + selectedDirectory.toString());               
        }
        
        // Pulisci le label dopo l'aggiunta
        selectedFilePathLabel2.setText("");
        destinationFilePathLabel.setText("");
        // Nascondi la pagina chooseFileToAppendStringPage
        copyMoveFilePage.setVisible(false);
        // Mostra la pagina newRulePage
        newRulePage.setVisible(true);        
    }

private void showAlert(String message, Alert.AlertType alertType) {
    Alert alert = new Alert(alertType);
    alert.setTitle("Messaggio di Avviso");
    alert.setHeaderText(null);
    alert.setContentText(message);
    alert.showAndWait();
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
        if(action.compareTo("Scrivi su un file") == 0){
            newRulePage.setVisible(false);
            chooseFileToAppendStringPage.setVisible(true);                        
        }
        if(action.compareTo("Copia un file") == 0){
            newRulePage.setVisible(false);
            copyMoveFilePage.setVisible(true);
            isCopying = true;                            
        }
        if(action.compareTo("Sposta un file") == 0){
            newRulePage.setVisible(false);
            copyMoveFilePage.setVisible(true);
            isCopying = false;                        
        }
        if(action.compareTo("Elimina un file") == 0){

            FileChooser fileChooser = new FileChooser();
            File selectedFile = fileChooser.showOpenDialog(new Stage());
            actionLabel.setText("Elimina il file : " + selectedFile.toString());
            
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
        if(condition.compareTo("Giorno della settimana") == 0){
            chooseDayWeekPage.setVisible(true);
            newRulePage.setVisible(false);
        }
        if(condition.compareTo("Giorno dell'anno") == 0){
            dayAndMonthPage.setVisible(true);
            newRulePage.setVisible(false);
        }
        if(condition.compareTo("Giorno del mese") == 0){
            dayPage.setVisible(true);
            newRulePage.setVisible(false);
        }
         if(condition.compareTo("File esiste") == 0){
            existsFilePage.setVisible(true);
            newRulePage.setVisible(false); 
        }
        if(condition.compareTo("Dimensione del file") == 0){
            dimensionFilePage.setVisible(true);
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
            controlLabel.setText("Sempre  ");
                   
        }
        if(control.compareTo("Una volta") == 0){
            controlLabel.setText("Una volta ");
            
        }
        if(control.compareTo("Periodicamente") == 0){
            sleepingPeriodPage.setVisible(true);
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
        chooseFileToAppendStringPage.setVisible(false);
        copyMoveFilePage.setVisible(false);
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
        String hour = hourComboBox.getValue();
        String minutes = minutesComboBox.getValue();
        String time = hour + ":" + minutes;
        conditionLabel.setText("Alle : " + time);
        chooseHourPage.setVisible(false);
        newRulePage.setVisible(true);
        hourComboBox.setValue(" ");
        minutesComboBox.setValue(" ");
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
        hourComboBox.setValue(" ");
        minutesComboBox.setValue(" ");
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
        tableView.getSelectionModel().getSelectedItem().setActive(true);
        inactiveRuleId.setDisable(false);
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
        tableView.getSelectionModel().getSelectedItem().setActive(false);
        inactiveRuleId.setDisable(true);
        activeRuleId.setDisable(false);
        tableView.refresh();

    }      
    @FXML
    private void addSleepingPeriod(ActionEvent event) {
        sleepingPeriodPage.setVisible(false);
        newRulePage.setVisible(true);
        String gg =daysSleeping.getText(); 
        String h = hoursSleeping.getText();
        String m = minutesSleeping.getText();
        Integer ggi = Integer.parseInt(gg);
        Integer hi = Integer.parseInt(h);
        Integer mi = Integer.parseInt(m);
        if(!(ggi >=0 && ggi<=7)){
            showAlert("Inserire un numero compreso tra 0 e 7",  Alert.AlertType.ERROR);
             sleepingPeriodPage.setVisible(true);
             newRulePage.setVisible(false);
        }
        if(!(hi >= 0 && hi<=24)){
            showAlert("Inserire un numero compreso tra 0 e 24",  Alert.AlertType.ERROR);
            sleepingPeriodPage.setVisible(true);
            newRulePage.setVisible(false);
        }
        if(!(mi >= 0 && mi<=60)){
            showAlert("Inserire un numero compreso tra 0 e 60",  Alert.AlertType.ERROR);
            sleepingPeriodPage.setVisible(true);
            newRulePage.setVisible(false);
        }

        controlLabel.setText("Ogni : " + gg +"g "+h +"h "+m+"m " );
        daysSleeping.setText("");
        hoursSleeping.setText("");
        minutesSleeping.setText("");
        
    }

    @FXML
    private void showNewRulePage(ActionEvent event) {
        sleepingPeriodPage.setVisible(false);
        newRulePage.setVisible(true);
        }

   
/*
   
*/
    public void loadRules() {
    List<Rule> loadedRules = ruleFileHandler.loadRules();
    if (loadedRules != null && !loadedRules.isEmpty()) {
        if (rulesSet != null) {
            rulesSet.getRuleList().addAll(loadedRules);
        } else {
            // Inizializza rulesSet se è null
            rulesSet = new RulesSet();
            rulesSet.getRuleList().addAll(loadedRules);
        }

        if (ruleList != null) {
            ruleList.setAll(rulesSet.getRuleList());
        } else {
            // Inizializza ruleList se è null
            ruleList = FXCollections.observableArrayList();
            ruleList.setAll(rulesSet.getRuleList());
        }
    }
}
    
    public void saveRules() {
        ruleFileHandler.saveRules(new ArrayList<>(rulesSet.getRuleList()));
    }
    
    
    public void saveRulesAndExit() {
        ruleFileHandler.saveRules(rulesSet.getRuleList());
        Platform.exit(); }

    @FXML
    private void showAddPageBack2(ActionEvent event) {
        dayAndMonthPage.setVisible(false);
        newRulePage.setVisible(true);
        dayAndMonthText.setText("");
        conditionLabel.setText("");
        conditionChoiceBox.setValue("Seleziona una condizione");
    }

    @FXML
    private void confirmDayAndMonth(ActionEvent event) {
        String day = dayAndMonthText.getText();
        conditionLabel.setText("Il : " + day);
        dayAndMonthPage.setVisible(false);
        newRulePage.setVisible(true);
        dayAndMonthText.setText("");
    }

    @FXML
    private void confirmDay(ActionEvent event) {
        String day = dayField.getText();
        conditionLabel.setText("Il giorno : " + day);
        dayPage.setVisible(false);
        newRulePage.setVisible(true);
        dayField.setText("");
        
    }
    
    /**
     * Gestisce l'evento di conferma della scelta del giorno della settimana attraverso i RadioButton.
     * Verifica se un RadioButton è stato selezionato. Se sì, imposta l'etichetta nell'interfaccia utente
     * con il giorno della settimana selezionato. Se nessun RadioButton è selezionato, mostra un avviso
     * all'utente indicando che deve selezionare un giorno prima di confermare.
     * Dopo la conferma, nasconde la pagina di scelta del giorno della settimana e mostra la pagina principale
     * dell'applicazione, resettando anche la selezione del toggleGroup per i RadioButton.
     *
     * @param event l'evento di azione scatenato dalla conferma della scelta del giorno della settimana.
     */
    @FXML
    private void confirmDayWeek(ActionEvent event){
        RadioButton selectedRadioButton = (RadioButton) toggleGroup.getSelectedToggle();
        // Verifica se un RadioButton è stato selezionato
        if (selectedRadioButton != null) {
            conditionLabel.setText("Il giorno della settimana è : " + selectedRadioButton.getText());
        } else {
            showAlert("Devi selezionare un giorno prima di confermare.", Alert.AlertType.ERROR);
        }
        
        chooseDayWeekPage.setVisible(false);
        newRulePage.setVisible(true);     
        toggleGroup.selectToggle(null);            
    }

    @FXML
    private void showAddPageBack3(ActionEvent event) {
        dayPage.setVisible(false);
        newRulePage.setVisible(true);
        dayField.setText("");
    }
    
    /**
     * Gestisce l'evento di ritorno alla pagina principale dall'aggiunta di una stringa su file.
     * Nasconde la pagina di scelta della stringa da scrivere sul file e mostra la pagina principale dell'applicazione. 
     * Inoltre, pulisce il TextField utilizzato per inserire la stringa da scrivere e resetta l'etichetta del percorso del file selezionato.
     *
     * @param event l'evento di azione scatenato dal ritorno alla pagina principale dall'aggiunta di stringa su file.
     */
    private void showAddPageBack4(ActionEvent event) {
        chooseFileToAppendStringPage.setVisible(false);
        newRulePage.setVisible(true);
        stringToAppendTextField.clear();
        selectedFilePathLabel.setText("");
    }
    
    /**
     * Gestisce l'evento di ritorno alla pagina principale dalla scelta di copia o spostamento di un file.
     * Nasconde la pagina di scelta del file e di destinazione per le azioni di copia o spostamento, mostrando
     * la pagina principale dell'applicazione. Inoltre, resetta le etichette del percorso del file e della destinazione selezionati.
     *
     * @param event l'evento di azione scatenato dal ritorno alla pagina principale dalla scelta di copia o spostamento di un file.
     */
    private void showAddPageBack5(ActionEvent event) {
        copyMoveFilePage.setVisible(false);
        newRulePage.setVisible(true);     
        selectedFilePathLabel2.setText("");
        destinationFilePathLabel.setText("");
    }

    

    @FXML
    private void chooseFileExists(ActionEvent event) {
        DirectoryChooser directoryChooser = new DirectoryChooser();;
        File selectedDirectory = directoryChooser.showDialog(new Stage());
        pathFile_id.setVisible(true);
        pathFileLabel.setText(selectedDirectory.toString()); 
    }
      
    @FXML
    private void ConfirmExistFileButton(ActionEvent event) {
        String nameFile = ExistFileTextField.getText();
        
       if(nameFile.isEmpty()){
            showAlert("Inserisci il nome del file di cui vuoi  verificare l'esistenza", Alert.AlertType.ERROR);
            return;
        }
        if(pathFileLabel.getText() == null){
            showAlert("Devi selezionare una cartella destinazione prima di confermare.", Alert.AlertType.ERROR);
            return;
        }
        
        if (pathFileLabel.getText() != null && !nameFile.isEmpty()){
            conditionLabel.setText("Il file : " + nameFile + " esiste nella cartella: " + pathFileLabel.getText());
            
        pathFileLabel.setText("");
        ExistFileTextField.clear();
        existsFilePage.setVisible(false);
        newRulePage.setVisible(true);
        
    }
    }

    

    @FXML
    private void ConfirmDimensionFileButton(ActionEvent event) {
        String minSize = DimensionLabel.getText();
        if(minSize.isEmpty()){
            showAlert("Inserisci la dimensione del file che vuoi verificare", Alert.AlertType.ERROR);
            return;
        }
        if(pathFileLabel1.getText() == ""){
            showAlert("Devi selezionare una cartella destinazione prima di confermare.", Alert.AlertType.ERROR);
            return;
        }
        
        if (pathFileLabel1.getText() != null && !minSize.isEmpty()){
            conditionLabel.setText("Il file selezionato : " + pathFileLabel1.getText() + " ha dimensione maggiore di: " + minSize);
            
        pathFileLabel1.setText("");
        DimensionLabel.clear();
        dimensionFilePage.setVisible(false);
        newRulePage.setVisible(true);
        
    }
    }

    @FXML
    private void chooseFileDimension(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        selectedFile = fileChooser.showOpenDialog(new Stage());
        pathFile_id1.setVisible(true);
        pathFileLabel1.setText(selectedFile.toString());
    }

    @FXML
    private void showAddPageBack6(ActionEvent event) {
        existsFilePage.setVisible(false);
        newRulePage.setVisible(true);
        ExistFileTextField.clear();
        pathFileLabel.setText("");
    }

    @FXML
    private void showAddPageBack7(ActionEvent event) {
        dimensionFilePage.setVisible(false);
        newRulePage.setVisible(true);
        DimensionLabel.clear();
        pathFileLabel1.setText("");
        
    }
    
    /**
     * Gestisce l'evento di ritorno alla pagina principale dalla scelta del giorno della settimana.
     * Nasconde la pagina di scelta del giorno della settimana e mostra la pagina principale dell'applicazione.
     * Inoltre, resetta la selezione del toggleGroup per i RadioButton.
     *
     * @param event l'evento di azione scatenato dal ritorno alla pagina principale dalla scelta del giorno della settimana.
     */
    @FXML
    private void showAddPageBack8(ActionEvent event) {
        chooseDayWeekPage.setVisible(false);
        newRulePage.setVisible(true);     
        toggleGroup.selectToggle(null);
    }
}


