/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package ActionHandlers;

import Action.Action;
import Action.DialogBoxAction;
import Action.PlayFileAction;
import java.io.File;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author Simone
 */
public class BaseActionHandlerTest {
    BaseActionHandler base;
    AudioActionHandler audio;
    DialogBoxActionHandler dialog;
    
    
    @Before
    public void setUp() {
        base = new BaseActionHandler();
        audio = new AudioActionHandler();
        dialog = new DialogBoxActionHandler();
        
        base.setNext(audio);
        audio.setNext(dialog);
    }

    /**
     * Test of setNext method, of class BaseActionHandler.
     */
    @Test
    public void testSetNext() {
        assertEquals(base.getNext(), audio);
        assertEquals(audio.getNext(), dialog);
    }

    /**
     * Test of handle method, of class BaseActionHandler.
     */
    @Test
    public void testHandleNull() {
        //Test caso fail
        String request = "";
        String param = "";
        Action expResult0 = null;
        Action result = base.handle(request, param);
        assertEquals(expResult0, result);
        
    }
    
    @Test
    public void testHandleAudio() {
    //Test caso audio
        String request = "Riproduci il file";
        String param = "audio.wav";
        File f = new File(param);
        PlayFileAction expResult1 = new PlayFileAction(f);
        Action result = base.handle(request, param);
        assertEquals(expResult1.getClass(), result.getClass());
    
    }
    
    
     @Test
    public void testHandleMessage() {
    //Test caso audio
        String request = "Mostra un messaggio";
        String param = "ciao";
        DialogBoxAction expResult2 = new DialogBoxAction(param); 
        Action result = base.handle(request, param);
        assertEquals(expResult2.getClass(), result.getClass());
    }
}
