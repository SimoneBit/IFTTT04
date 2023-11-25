package Action;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;


/**
 *La classe DialogBoxAction implementa l'interfaccia @see Action e rappresenta
 * un'azione che mostra un messaggio in una finestra di dialogo.
 * @author Miriam Vitolo
 */
public class DialogBoxAction implements Action {

   private String message;
    
    
    /**
     * Costruisce un'istanza di DialogBoxAction con il messaggio specificato.
     * @param message Il messaggio da visualizzare nella finestra di dialogo.
     */
    public DialogBoxAction(String message) {
        this.message = message;
        
    }
    
    
    /**
     * Esegue l'azione, mostrando un messaggio in una finestra di dialogo di tipo informazione.
     * @return true se l'azione è stata eseguita con successo, altrimenti false.
     */
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

    
    /**
     * Restituisce una rappresentazione stringa dell'oggetto DialogBoxAction, mostrando il messaggio associato.
     * @return una stringa che rappresenta l'oggetto.
     */
    @Override
    public String toString() {
        return "DialogBoxAction{" + "message=" + message + '}';
    }
        
}
    
    

