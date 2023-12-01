package Action;

import java.io.BufferedReader;
import java.io.FileReader;
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
public class WriteOnFileActionTest {
    
private Path tempDirectory;

    @Before
    public void setUp() throws IOException {
        // Creo una directory temporanea
        tempDirectory = Files.createTempDirectory("tempDir");
    }

    /**
     * Test of executeAction method, of class WriteOnFileAction.
     */
    @Test
    public void testExecuteAction() {
        try {
            // Creo un file nella directory temporanea
            Path filePath = tempDirectory.resolve("testFile.txt");
            WriteOnFileAction writeAction = new WriteOnFileAction(filePath.toFile(), "Hello, World");

            // Eseguo l'azione
            assertTrue(writeAction.executeAction());

            // Verifico che il file contenga la stringa appena inserita
            try (BufferedReader reader = new BufferedReader(new FileReader(filePath.toFile()))) {
                String content = reader.readLine();
                assertEquals("Hello, World", content);
            }
        } catch (IOException e) {
            fail("Eccezione durante il test: " + e.getMessage());
        }
    }
   
}
