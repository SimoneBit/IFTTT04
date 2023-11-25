package Action;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;


/**
 *La classe PlayFileAction implementa l'interfaccia @see Action e rappresenta un'azione che avvia la 
 * riproduzione di un file audio. La riproduzione può essere interrotta mediante un pulsante "STOP" in una finestra separata.
 * La costante @see LOOP_TIMES indica il numero di volte che l'audio verrà riprodotto.
 * @author Simone Pacifico
 */
public class PlayFileAction implements Action {
    public final int LOOP_TIMES = 2;          
    private File file;                          //Riferimento al file audio da riprodurre
    private Clip clip;                          //Riferimento alla clip in esecuzione
    
    
    /**
     * Costruisce un'istanza di PlayFileAction con il file audio specificato.
     * @param file il file audio da riprodurre.
     */
    public PlayFileAction(File file) {
        this.file = file;
    }
           
    
   /**
    * Esegue l'azione avviando la riproduzione del file audio e mostrando una finestra con un pulsante "STOP".
    * @return true se l'azione è stata eseguita con successo, altrimenti false
    */
    @Override
    public boolean executeAction() {
        
        Stage primaryStage = new Stage();
        primaryStage.setTitle("Sveglia");
        
        Button btn = new Button();
        btn.setText("STOP");
        btn.setOnAction(e -> {
            this.stopAudio();
            primaryStage.close();
        });

        StackPane root = new StackPane();
        root.getChildren().add(btn);
        primaryStage.setScene(new Scene(root, 300, 250));

        primaryStage.show();
        this.startAudio();
        return true;
    }

    
    /**
     * Avvia la riproduzione dell'audio dal file specificato.
     * @return true se la riproduzione è stata avviata con successo, altrimenti false.
     */
    
    public boolean startAudio(){
        boolean exit = false;
        
        try {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(this.file);
            // Ottieni il clip audio
            Clip clip = AudioSystem.getClip();
            this.clip = clip;
            // Apri il clip con il flusso audio
            clip.open(audioStream);
            exit = true;
            //Riproduci l'audio per il numero di volte indicato
            clip.loop(LOOP_TIMES);            
        } catch (UnsupportedAudioFileException ex) {
            Logger.getLogger(PlayFileAction.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(PlayFileAction.class.getName()).log(Level.SEVERE, null, ex);
        } catch (LineUnavailableException ex) {
            Logger.getLogger(PlayFileAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return exit;
    }
    
    
    /**
     * Interrompe la riproduzione dell'audio, se in corso.
     * @return true se la riproduzione è stata interrotta, altrimenti false.
     */
    public boolean stopAudio(){
        boolean exit = false;
        //Se la clip non è mai stata istanziata esci e ritorna false
        if(this.clip == null){
            return exit;
        }
        //Se la clip è stata istanziata ed è in esecuzione
        if(this.clip.isRunning()){
            this.clip.stop();           //fermala
            exit = true;
        }
        return exit;
    }
    
    
 /**
  * Restituisce il file audio associato a questa azione.
  * @return il file audio.
  */   
    public File getFile() {
        return file;
    }

    
    /**
     * Restituisce una rappresentazione stringa dell'oggetto PlayFileAction, mostrando il percorso completo 
     * del file audio.
     * @return una stringa che rappresenta l'oggetto.
     */
    @Override
    public String toString() {
        return "PlayFileAction{" + "file=" + file.toString() + '}';
    }
    
    
}
