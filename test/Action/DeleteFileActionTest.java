/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package Action;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Simone
 */
public class DeleteFileActionTest {
   
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of executeAction method, of class DeleteFileAction.
     */
    

    @Test
    public void testDeleteFileSuccess() {
        // File temporaneo per i test
        String filePath = "testfile.txt";

        File file = new File("testfile.txt");
        try {
            file.createNewFile();
        } catch (IOException ex) {
            Logger.getLogger(DeleteFileActionTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Crea l'azione
        DeleteFileAction testAction = new DeleteFileAction(file);
        // Chiama la funzione execute e controlla che restituisca true
        assertTrue(testAction.executeAction());
    }

    @Test
    public void testDeleteFileNotFound() {
        // File temporaneo per i test
        String filePath = "testfile.txt";

        File file = new File("testfile.txt");
        
        //Crea l'azione
        DeleteFileAction testAction = new DeleteFileAction(file);
        // Chiama la funzione execute e controlla che restituisca true
        assertFalse(testAction.executeAction());
    }

    @Test
    public void testDeleteFileUnableToDelete() {
        // File temporaneo per i test
        String filePath = "testfile.txt";

        File file = new File("testfile.txt");
        try {
            file.createNewFile();
        } catch (IOException ex) {
            Logger.getLogger(DeleteFileActionTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Crea l'azione
        DeleteFileAction testAction = new DeleteFileAction(file);
        //Apri il file in modo da non poterlo cancellare
        try (FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)){
            //Controlla che il file non venga cancellato e quindi che execute action restituisca false
            assertFalse(testAction.executeAction());
        }catch (IOException ex) {
            Logger.getLogger(DeleteFileActionTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        file.delete();
        
        // Chiama la funzione execute e controlla che restituisca true
        

    }   
    
    
}
