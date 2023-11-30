package Action;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author Palma Orlando
 */
public class CopyFileActionTest {

    /**
     * Test of executeAction method, of class CopyFileAction.
     */
    @Test
    public void testExecuteAction() throws IOException {
        // Creo un file temporaneo per il test
        File tempFile = createTempFile();

        // Creo una directory temporanea per la destinazione
        Path tempDir = Files.createTempDirectory("tempDir");

        // Eseguo l'azione di copia
        CopyFileAction copyFileAction = new CopyFileAction(tempFile, tempDir.toString());
        assertTrue(copyFileAction.executeAction());

        // Verifico che il file sia stato copiato correttamente
        Path destinationFilePath = tempDir.resolve(tempFile.getName());
        assertTrue(Files.exists(destinationFilePath));
        assertTrue(Files.isRegularFile(destinationFilePath));
    }   

    private File createTempFile() throws IOException {
        // Creo un file temporaneo con alcuni dati di prova
        File tempFile = File.createTempFile("testFile", ".txt");
        Files.write(tempFile.toPath(), "Hello, World!".getBytes());
        return tempFile;
    }
}
