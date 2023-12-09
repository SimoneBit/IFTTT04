package Action;

import org.junit.Test;
import static org.junit.Assert.*;

public class ExecuteProgramActionTest {

    @Test
    public void testExecuteActionWithParameters() {
        // Simula l'esecuzione di un programma con parametri
        String programPath = "test\\TestFiles\\sum1.exe";
        String programParameters = "1 2 3 4";

        ExecuteProgramAction executeProgramAction = new ExecuteProgramAction(programPath, programParameters);
        
        // Esegui l'azione
        boolean result = executeProgramAction.executeAction();

        // Verifica che l'esecuzione abbia avuto successo
        assertTrue(result);
        
        // Puoi aggiungere ulteriori asserzioni o verifiche specifiche qui
    }

    @Test
    public void testExecuteActionWithoutParameters() {
        // Simula l'esecuzione di un programma senza parametri
        String programPath = "\"C:\\Windows\\System32\\bash.exe\"";
        String programParameters = "";

        ExecuteProgramAction executeProgramAction = new ExecuteProgramAction(programPath, programParameters);
        
        // Esegui l'azione
        boolean result = executeProgramAction.executeAction();

        // Verifica che l'esecuzione abbia avuto successo
        assertTrue(result);
        
        // Puoi aggiungere ulteriori asserzioni o verifiche specifiche qui
    }
}