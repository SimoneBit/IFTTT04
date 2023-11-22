/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ifttt;


import Action.*;
import Rule.Rule;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Simone
 */
public class FXMLController implements Initializable {

    @FXML
    private AnchorPane Menu_principale;
    @FXML
    private Pane cond1;
    @FXML
    private Pane cond2;
    @FXML
    private Pane cond3;
    @FXML
    private Pane cond4;
    @FXML
    private Label inserisciMessaggio;
    @FXML
    private TextField messaggioUtente;
    @FXML
    private Button closeButton;
    @FXML
    private AnchorPane dialogBox;
    @FXML
    private ListView<Rule> listView;
    @FXML
    private Button aggiungiRegolaId;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        cond1.setVisible(false);
        cond2.setVisible(false);
        cond3.setVisible(false);
        cond4.setVisible(false);
    }    

    @FXML
    private void buttonPressed(ActionEvent event) {
        
        String message= messaggioUtente.getText();
        
          if(message.equals("")){
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Messaggio di Avviso");
            alert.setHeaderText(null);
            alert.setContentText("Inserisci il messaggio che vuoi ti venga mostrato");
            alert.showAndWait();
            message= messaggioUtente.getText();
        
           }else{
    
        System.out.println(message);
        DialogBoxAction d= new DialogBoxAction(message);
        //d.executeAction(message, primaryStage);
        // Pulisci il TextField dopo l'aggiunta
            messaggioUtente.clear();
            // Nascondi DialogBox
            dialogBox.setVisible(false);
            Menu_principale.setVisible(true);
            }
        
        }

    @FXML
    private void aggiungiRegola(Rule rule) {
        listView.getItems().add(rule);
    }
}
