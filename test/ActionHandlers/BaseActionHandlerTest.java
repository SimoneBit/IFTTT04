/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package ActionHandlers;

import Action.*;
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
    DeleteFileActionHandler deleteFile;
    
    @Before
    public void setUp() {
        base = new BaseActionHandler();
        audio = new AudioActionHandler();
        dialog = new DialogBoxActionHandler();
        deleteFile = new DeleteFileActionHandler();
        
        base.setNext(audio);
        audio.setNext(dialog);
        dialog.setNext(deleteFile);
    }

    /**
     * Test of setNext method, of class BaseActionHandler.
     */
    @Test
    public void testSetNext() {
        assertEquals(base.getNext(), audio);
        assertEquals(audio.getNext(), dialog);
        assertEquals(dialog.getNext(), deleteFile);
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
    //Test caso messaggio
        String request = "Mostra il messaggio";
        String param = "ciao";
        DialogBoxAction expResult2 = new DialogBoxAction(param); 
        Action result = base.handle(request, param);
        assertEquals(expResult2.getClass(), result.getClass());
    }
    
    @Test
    public void testDeleteFile(){
        String request = "Elimina il file";
        String param = "test.txt";
        File f = new File(param);
        DeleteFileAction expResult3 = new DeleteFileAction(f);
        Action result = base.handle(request, param);
        assertEquals(expResult3.getClass(), result.getClass());
        
    }
}
