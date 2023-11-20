/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Action;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
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

    private File file;
    private Clip clip;
    
    public PlayFileAction(File file) {
        this.file = file;
    }
            
            
    @Override
    public boolean executeAction(File file) {
        boolean exit = false;
        try {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(this.file);
            // Ottieni il clip audio
            Clip clip = AudioSystem.getClip();
            this.clip = clip;
            // Apri il clip con il flusso audio
            clip.open(audioStream);
            
            long startTime = System.currentTimeMillis();
            while (System.currentTimeMillis() - startTime < 180000){
                // Avvia la riproduzione
                clip.start();
                exit = true;
                while (!clip.isRunning()) {
                    Thread.sleep(10);
                }
                while (clip.isRunning() && System.currentTimeMillis() - startTime < 180000) {
                    Thread.sleep(10);
                }
                
                clip.flush();
            }
        } catch (UnsupportedAudioFileException ex) {
            Logger.getLogger(PlayFileAction.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(PlayFileAction.class.getName()).log(Level.SEVERE, null, ex);
        } catch (LineUnavailableException ex) {
            Logger.getLogger(PlayFileAction.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(PlayFileAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        return exit;
    }

    public boolean stopAudio(){
        boolean exit = false;
        if(this.clip.isRunning()){
            this.clip.stop();
            exit = true;
        }
        return exit;
    }
    public File getFile() {
        return file;
    }
    
    
}
