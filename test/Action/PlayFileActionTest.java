/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package Action;

import java.io.File;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Simone
 */
public class PlayFileActionTest {
    
    public PlayFileActionTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of executeAction method, of class PlayFileAction.
     */
    @Test
    public void testExecuteAction() {
        System.out.println("executeAction");
        File file = null;
        PlayFileAction instance = null;
        boolean expResult = false;
        boolean result = instance.executeAction(file);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of stopAudio method, of class PlayFileAction.
     */
    @Test
    public void testStopAudio() {
        System.out.println("stopAudio");
        PlayFileAction instance = null;
        boolean expResult = false;
        boolean result = instance.stopAudio();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getFile method, of class PlayFileAction.
     */
    @Test
    public void testGetFile() {
        System.out.println("getFile");
        PlayFileAction instance = null;
        File expResult = null;
        File result = instance.getFile();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
