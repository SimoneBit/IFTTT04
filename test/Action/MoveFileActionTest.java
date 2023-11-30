package Action;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author PalmaOrlando
 */
public class MoveFileActionTest {
    
    public MoveFileActionTest() {
    }

    /**
     * Test of executeAction method, of class MoveFileAction.
     */
    @Test
    public void testExecuteAction() throws IOException {
        // Creo un file temporaneo per il test
        File tempFile = createTempFile();

        // Creo una directory temporanea per la destinazione
        Path tempDir = Files.createTempDirectory("tempDir");

        // Eseguo l'azione di spostamento
        MoveFileAction moveFileAction = new MoveFileAction(tempFile, tempDir.toString());
        assertTrue(moveFileAction.executeAction());

        // Verifico che il file sia stato spostato correttamente
        Path destinationFilePath = tempDir.resolve(tempFile.getName());
        assertTrue(Files.exists(destinationFilePath));
        assertTrue(Files.isRegularFile(destinationFilePath));

        // Verifico che il file non esista più nella directory originale
        assertFalse(Files.exists(tempFile.toPath()));
    }
    
    private File createTempFile() throws IOException {
        // Creo un file temporaneo con alcuni dati di prova
        File tempFile = File.createTempFile("testFile", ".txt");
        Files.write(tempFile.toPath(), "Hello, World!".getBytes());
        return tempFile;
    }
}
