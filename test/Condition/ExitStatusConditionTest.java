package Condition;

import java.io.File;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author orlan
 */
public class ExitStatusConditionTest {
    
    @Test
    public void testCheckConditionWithMatchingExitCode() {
        String jarName = "TestExitStatus.jar";
        int expectedValue = 0;

        // Trovo il percorso del progetto
        String projectPath = System.getProperty("user.dir");

        // Costruisco il percorso completo del programma
        String jarPath = "\"" + projectPath + File.separator + jarName + "\"";
        ExitStatusCondition condition = new ExitStatusCondition(jarPath, expectedValue);

        // Verifico che la condizione sia soddisfatta
        assertTrue(condition.checkCondition());
    }
    
        @Test
    public void testCheckConditionWithNonMatchingExitCode() {
        String jarName = "TestExitStatus.jar";
        int expectedValue = 1;

        // Trovo il percorso del progetto
        String projectPath = System.getProperty("user.dir");

        // Costruisco il percorso completo del programma
        String jarPath = "\"" + projectPath + File.separator + jarName + "\"";
        ExitStatusCondition condition = new ExitStatusCondition(jarPath, expectedValue);

        // Verifico che la condizione sia soddisfatta
        assertFalse(condition.checkCondition());
    }

    
}
