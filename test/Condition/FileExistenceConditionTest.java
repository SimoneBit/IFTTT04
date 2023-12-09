package Condition;

import java.io.File;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Nicola Lanzara
 */
public class FileExistenceConditionTest {

    @Test
    public void testCheckConditionWhenFileExists() {
        // Specifica un percorso di cartella esistente e un nome di file esistente
        String fileName = "file_esistente.txt";

        // Trovo il percorso della cartella 
        String folderPath = System.getProperty("user.dir");  

        // Crea un'istanza di FileExistenceCondition
        FileExistenceCondition condition = new FileExistenceCondition(folderPath, fileName, false);

        // Verifica che la condizione sia soddisfatta (file esistente)
        assertTrue(condition.checkCondition());
    }

    @Test
    public void testCheckConditionWhenFileDoesNotExist() {
        // Specifica un percorso di cartella esistente e un nome di file inesistente
        String folderPath = System.getProperty("user.dir");  
        String fileName = "file_inesistente.txt";

        // Crea un'istanza di FileExistenceCondition
        FileExistenceCondition condition = new FileExistenceCondition(folderPath, fileName, false);

        // Verifica che la condizione non sia soddisfatta (file inesistente)
        assertFalse(condition.checkCondition());
    }
    
     @Test
    public void testCheckConditionWhenFileExistsNOT() {
        // Specifica un percorso di cartella esistente e un nome di file esistente
        String folderPath = System.getProperty("user.dir");  
        String fileName = "file_esistente.txt";

        // Crea un'istanza di FileExistenceCondition
        FileExistenceCondition condition = new FileExistenceCondition(folderPath, fileName, true);

        // Verifica che la condizione non sia soddisfatta (file esistente) ma locica invertita
        assertFalse(condition.checkCondition());
    }

    @Test
    public void testCheckConditionWhenFileDoesNotExistNOT() {
        // Specifica un percorso di cartella esistente e un nome di file inesistente
        String folderPath = System.getProperty("user.dir");  
        String fileName = "file_inesistente.txt";

        // Crea un'istanza di FileExistenceCondition
        FileExistenceCondition condition = new FileExistenceCondition(folderPath, fileName, true);

        // Verifica che la condizione sia soddisfatta (file inesistente) ma logica invertita
        assertTrue(condition.checkCondition());
    }

}