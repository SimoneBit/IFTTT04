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
public class FileSizeConditionTest {
  
    @Test
    public void testCheckConditionWhenFileSizeExceedsMinSize() {
    try {
        // Specifica un percorso di file esistente e una dimensione minima
        String fileName = "file_esistente.txt";
        long minSize = 5; // in KB
        // Trovo il percorso del file
        String projectPath = System.getProperty("user.dir");
        // Costruisco il percorso completo del file
        String filePath = projectPath.replace("\\", "\\\\") + File.separator + "\\" + fileName;

        // Crea un'istanza di FileSizeCondition
        FileSizeCondition condition = new FileSizeCondition(filePath, minSize, false);

        // Verifica che la condizione sia soddisfatta (la dimensione del file supera la dimensione minima)
        assertTrue(condition.checkCondition());
    } catch (Exception e) {
        e.printStackTrace();
        fail("Eccezione durante l'esecuzione del test");
    }
}

    @Test
    public void testCheckConditionWhenFileSizeDoesNotExceedMinSize() {
        // Specifica un percorso di file esistente e una dimensione minima
        String fileName = "file_esistente.txt";
        long minSize = 100000*1024; // in KB (un valore molto grande)
        // Trovo il percorso del file
        String projectPath = System.getProperty("user.dir");
        // Costruisco il percorso completo del file
        String filePath = projectPath.replace("\\", "\\\\") + File.separator + "\\" + fileName;
        
        // Crea un'istanza di FileSizeCondition
        FileSizeCondition condition = new FileSizeCondition(filePath, minSize, false);

        // Verifica che la condizione non sia soddisfatta (la dimensione del file non supera la dimensione minima)
        assertFalse(condition.checkCondition());
    }

    @Test
    public void testCheckConditionWhenFileDoesNotExist() {
        // Specifica un percorso di file esistente e una dimensione minima
        String fileName = "file_inesistente.txt";
        long minSize = 1; // in KB
        // Trovo il percorso del file
        String projectPath = System.getProperty("user.dir");
        // Costruisco il percorso completo del file
        String filePath = projectPath.replace("\\", "\\\\") + File.separator + "\\" + fileName;

        // Crea un'istanza di FileSizeCondition
        FileSizeCondition condition = new FileSizeCondition(filePath, minSize,false);

        // Verifica che la condizione non sia soddisfatta (il file non esiste)
        assertFalse(condition.checkCondition());
    }
    
    @Test
    public void testCheckConditionWhenFileSizeExceedsMinSizeNOT() {
    try {
        // Specifica un percorso di file esistente e una dimensione minima
        String fileName = "file_esistente.txt";
        long minSize = 5; // in KB
        // Trovo il percorso del file
        String projectPath = System.getProperty("user.dir");
        // Costruisco il percorso completo del file
        String filePath = projectPath.replace("\\", "\\\\") + File.separator + "\\" + fileName;

        // Crea un'istanza di FileSizeCondition
        FileSizeCondition condition = new FileSizeCondition(filePath, minSize, true);
        
        // Verifica che la condizione non sia soddisfatta (la dimensione del file supera la dimensione minima)
        assertFalse(condition.checkCondition());
    } catch (Exception e) {
        e.printStackTrace();
        fail("Eccezione durante l'esecuzione del test");
    }
}

    @Test
    public void testCheckConditionWhenFileSizeDoesNotExceedMinSizeNOT() {
        // Specifica un percorso di file esistente e una dimensione minima
        String fileName = "file_esistente.txt";
        long minSize = 100000*1024; // in KB (un valore molto grande)
        // Trovo il percorso del file
        String projectPath = System.getProperty("user.dir");
        // Costruisco il percorso completo del file
        String filePath = projectPath.replace("\\", "\\\\") + File.separator + "\\" + fileName;
        
        // Crea un'istanza di FileSizeCondition
        FileSizeCondition condition = new FileSizeCondition(filePath, minSize, true);

        // Verifica che la condizione sia soddisfatta (la dimensione del file non supera la dimensione minima)
        assertTrue(condition.checkCondition());
    }

    @Test
    public void testCheckConditionWhenFileDoesNotExistNOT() {
        // Specifica un percorso di file esistente e una dimensione minima
        String fileName = "file_inesistente.txt";
        long minSize = 1; // in KB
        // Trovo il percorso del file
        String projectPath = System.getProperty("user.dir");
        // Costruisco il percorso completo del file
        String filePath = projectPath.replace("\\", "\\\\") + File.separator + "\\" + fileName;

        // Crea un'istanza di FileSizeCondition
        FileSizeCondition condition = new FileSizeCondition(filePath, minSize, true);

        // Verifica che la condizione non sia soddisfatta (il file non esiste) anche con la logica invertita
        assertFalse(condition.checkCondition());
    }

    
}