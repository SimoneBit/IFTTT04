/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Action;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author miria
 */
public class DialogBoxAction implements Action {

    private String message;
    private Alert alert;
    
    
    public DialogBoxAction(String message) {
        this.message = message;
        this.alert = new Alert(AlertType.INFORMATION);
    }
    
    @Override
    public boolean executeAction() {
       
        alert.setTitle("Messaggio");
        alert.setHeaderText(null); // Senza intestazione
        alert.setContentText(this.message);

       // Mostra il popup e attendi la chiusura
        alert.showAndWait();
           
        return true;
    } 

    @Override
    public String toString() {
        return "DialogBoxAction{" + "message=" + message + '}';
    }
    
    public Alert getAlert() {
        return this.alert;
    }
}
    
    

