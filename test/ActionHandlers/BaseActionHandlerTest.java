package ActionHandlers;

import Action.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
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
    CopyFileActionHandler copyFile;
    MoveFileActionHandler moveFile;
    WriteOnFileActionHandler writeFile;
    
    @Before
    public void setUp() {
        base = new BaseActionHandler();
        audio = new AudioActionHandler();
        dialog = new DialogBoxActionHandler();
        deleteFile = new DeleteFileActionHandler();
        copyFile = new CopyFileActionHandler();
        moveFile = new MoveFileActionHandler();
        writeFile = new WriteOnFileActionHandler();
        
        base.setNext(audio);
        audio.setNext(dialog);
        dialog.setNext(deleteFile);
        deleteFile.setNext(copyFile);
        copyFile.setNext(moveFile);
        moveFile.setNext(writeFile);
    }

    /**
     * Test of setNext method, of class BaseActionHandler.
     */
    @Test
    public void testSetNext() {
        assertEquals(base.getNext(), audio);
        assertEquals(audio.getNext(), dialog);
        assertEquals(dialog.getNext(), deleteFile);
        assertEquals(deleteFile.getNext(), copyFile);
        assertEquals(copyFile.getNext(), moveFile);
        assertEquals(moveFile.getNext(), writeFile);
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
    
    @Test
    public void testCopyFile() throws IOException{
        String request = "Copia il file";
        File tempFile = createTempFile();
        Path tempDir = Files.createTempDirectory("tempDir");
        String param = tempFile + " Path di destinazione: " + tempDir.toString();
        CopyFileAction expResult3 = new CopyFileAction(tempFile, tempDir.toString());
        Action result = base.handle(request, param);
        assertEquals(expResult3.getClass(), result.getClass());
        
    }
    
    @Test
    public void testMoveFile() throws IOException{
        String request = "Sposta il file";
        File tempFile = createTempFile();
        Path tempDir = Files.createTempDirectory("tempDir");
        String param = tempFile + " Path di destinazione: " + tempDir.toString();
        MoveFileAction expResult3 = new MoveFileAction(tempFile, tempDir.toString());
        Action result = base.handle(request, param);
        assertEquals(expResult3.getClass(), result.getClass());
        
    }
    
    @Test
    public void testWriteFile() throws IOException{
        String request = "Scrivi sul file";
        Path tempDirectory = Files.createTempDirectory("tempDir");
        Path filePath = tempDirectory.resolve("testFile.txt");
        String param = filePath + " Testo da scrivere: " + "Hello, World";
        WriteOnFileAction expResult3 = new WriteOnFileAction(filePath.toFile(), "Hello, World");
        Action result = base.handle(request, param);
        assertEquals(expResult3.getClass(), result.getClass());
        
    }
    
    private File createTempFile() throws IOException {
        // Creo un file temporaneo con alcuni dati di prova
        File tempFile = File.createTempFile("testFile", ".txt");
        Files.write(tempFile.toPath(), "Hello, World!".getBytes());
        return tempFile;
    }


   @Test
    public void testExecuteProgram() throws IOException {
        String request = "Esegui il programma";
        String programPath = "test\\TestFiles\\sum1.exe"; 
        String programParameters = "1 2 3";
        String param = programPath + " con parametri: " + programParameters;

        ExecuteProgramAction expResult = new ExecuteProgramAction(programPath, programParameters);
        // Esegui l'azione
        boolean result = expResult.executeAction();

        // Verifica che l'esecuzione abbia avuto successo
        assertTrue(result);

    }
}