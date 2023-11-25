/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package Action;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author miria
 */
public class DialogBoxActionTest {
    
    private String message;
    private Alert alert;
    private DialogBoxAction dba;
    
    @Before
    public void setUp() {
        message = "Questo è un messaggio di prova";
        dba = new DialogBoxAction(message);
        alert = new Alert(AlertType.INFORMATION);
        alert.setContentText(message);
    }
       
    /**
     * Test of executeAction method, of class DialogBoxAction.
     */
    @Test
    public void testExecuteAction() {
        assertTrue(dba.executeAction());
        assertEquals(alert.getContentText(), dba.getAlert().getContentText());
    }
}

