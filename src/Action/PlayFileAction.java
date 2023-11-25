/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Action;

import ifttt.IFTTT;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;


/**
 *
 * @author Simone
 */
public class PlayFileAction implements Action {
    public final int LOOP_TIMES = 2;          //Costante che indica il numero di volte che l'audio verrà riprodotto
    private File file;                          //Riferimento al file audio da riprodurre
    private Clip clip;                          //Riferimento alla clip in esecuzione
    
    public PlayFileAction(File file) {
        this.file = file;
    }
            
            
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
    public File getFile() {
        return file;
    }

    @Override
    public String toString() {
        return "PlayFileAction{" + "file=" + file.toString() + '}';
    }
    
    
}
