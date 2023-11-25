/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Action;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;


/**
 *
 * @author miria
 */
public class DialogBoxAction implements Action {

    private String message;
    
    
    
    public DialogBoxAction(String message) {
        this.message = message;
        
    }
    
    @Override
    public boolean executeAction() {
       
        Alert alert = new Alert(AlertType.INFORMATION);
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
    
    
}
    
    

