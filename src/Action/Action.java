/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Action;

import java.io.File;
import javafx.stage.Stage;

/**
 *
 * @author Simone
 */
public interface Action {
    public boolean executeAction(File file);
    public boolean executeAction(String message, Stage primaryStage);
    
}
