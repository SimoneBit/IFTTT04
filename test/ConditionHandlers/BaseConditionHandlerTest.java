package ConditionHandlers;

import Condition.*;
import java.io.File;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author miria
 */
public class BaseConditionHandlerTest {
    BaseConditionHandler base;
    TimeConditionHandler time;
    DayOfMonthConditionHandler dayOfMonth;
    DayOfYearConditionHandler dayOfYear;
    DayOfWeekConditionHandler dayOfWeek;
    ExitStatusConditionHandler exitStatus;
    FileExistenceConditionHandler fileExistence;
    FileSizeConditionHandler fileSize;
    
    
    @Before
    public void setUp() {
        base = new BaseConditionHandler();
        time = new TimeConditionHandler();
        dayOfMonth = new DayOfMonthConditionHandler();
        dayOfYear = new DayOfYearConditionHandler();
        dayOfWeek = new DayOfWeekConditionHandler();
        exitStatus = new ExitStatusConditionHandler();
        fileExistence = new FileExistenceConditionHandler();
        fileSize = new FileSizeConditionHandler();
        
        base.setNext(time);
        time.setNext(dayOfMonth);
        dayOfMonth.setNext(dayOfYear);
        dayOfYear.setNext(dayOfWeek);
        dayOfWeek.setNext(exitStatus);
        exitStatus.setNext(fileExistence);
        fileExistence.setNext(fileSize);
    }
    /**
     * Test of setNext method, of class BaseConditionHandler.
     */
    @Test
    public void testSetNext() {
        assertEquals(base.getNext(), time);
        assertEquals(time.getNext(), dayOfMonth);
        assertEquals(dayOfMonth.getNext(), dayOfYear);
        assertEquals(dayOfYear.getNext(), dayOfWeek);
        assertEquals(dayOfWeek.getNext(), exitStatus);
        assertEquals(exitStatus.getNext(), fileExistence);
        assertEquals(fileExistence.getNext(), fileSize);
    }

    /**
     * Test of handle method, of class BaseConditionHandler.
     */
    @Test
    public void testHandleNull() {
        //Test caso fail
        String request = "";
        String param = "";
        boolean logic = false;
        Condition expResult0 = null;
        Condition result = base.handle(request, param, logic);
        assertEquals(expResult0, result);
    }
    
    @Test
    public void testHandleNullNOT() {
        //Test caso fail
        String request = "";
        String param = "";
        boolean logic = true;
        Condition expResult0 = null;
        Condition result = base.handle(request, param, logic);
        assertEquals(expResult0, result);
    }
    
    @Test
    public void testHandleTime() {
        //Test ora del giorno
        String request = "Alle";
        String param = "18:30";
        boolean logic = false;
        TimeOfDayCondition expResult1 = new TimeOfDayCondition(param, logic);
        Condition result = base.handle(request, param, logic);
        assertEquals(expResult1.getClass(), result.getClass());
    }
    
    @Test
    public void testHandleTimeNOT() {
        //Test ora del giorno
        String request = "Alle";
        String param = "18:30";
        boolean logic = false;
        TimeOfDayCondition expResult1 = new TimeOfDayCondition(param, logic);
        Condition result = base.handle(request, param, logic);
        assertEquals(expResult1.getClass(), result.getClass());
    }
    
    @Test
    public void testHandleDayOfYear(){
        String request = "Il";
        String param = "11/5";
        boolean logic = false;
        DayOfYearCondition expResult1 = new DayOfYearCondition(11, 5, logic);
        Condition result = base.handle(request, param, logic);
        assertEquals(expResult1.getClass(), result.getClass());        
    }
    
    @Test
    public void testHandleDayOfYearNOT(){
        String request = "Il";
        String param = "11/5";
        boolean logic = true;
        DayOfYearCondition expResult1 = new DayOfYearCondition(11, 5, logic);
        Condition result = base.handle(request, param, logic);
        assertEquals(expResult1.getClass(), result.getClass());        
    }
    
    @Test
    public void testHandleDayOfMonth(){
        String request = "Il giorno";
        String param = "2";
        boolean logic = false;
        DayOfMonthCondition expResult1 = new DayOfMonthCondition(2, logic);
        Condition result = base.handle(request, param, logic);
        assertEquals(expResult1.getClass(), result.getClass());
    }
    
    @Test
    public void testHandleDayOfMonthNOT(){
        String request = "Il giorno";
        String param = "2";
        boolean logic = true;
        DayOfMonthCondition expResult1 = new DayOfMonthCondition(2, logic);
        Condition result = base.handle(request, param, logic);
        assertEquals(expResult1.getClass(), result.getClass());
    }
    
    @Test
    public void testHandleDayOfWeek(){
        String request = "Il giorno della settimana è";
        String param = "giovedì";
        boolean logic = false;
        DayOfWeekCondition expResult1 = new DayOfWeekCondition("giovedì", logic);
        Condition result = base.handle(request, param, logic);
        assertEquals(expResult1.getClass(), result.getClass());
    }
    
    @Test
    public void testHandleDayOfWeekNOT(){
        String request = "Il giorno della settimana è";
        String param = "giovedì";
        boolean logic = true;
        DayOfWeekCondition expResult1 = new DayOfWeekCondition("giovedì", logic);
        Condition result = base.handle(request, param, logic);
        assertEquals(expResult1.getClass(), result.getClass());
    }
    
    @Test
    public void testHandleExitStatus(){
        String request = "Controlla il valore";
        String jarName = "TestExitStatus.jar";
        String projectPath = System.getProperty("user.dir");
        String jarPath = "\"" + projectPath + File.separator + jarName + "\"";
        String expectedValue = "0";
        String param = jarPath + " Valore atteso: " + expectedValue;
        boolean logic = false;
        
        ExitStatusCondition expResult1 = new ExitStatusCondition(jarPath, 0, logic);
        Condition result = base.handle(request, param, logic);
        assertEquals(expResult1.getClass(), result.getClass());
    }
    
    @Test
    public void testHandleExitStatusNOT(){
        String request = "Controlla il valore";
        String jarName = "TestExitStatus.jar";
        String projectPath = System.getProperty("user.dir");
        String jarPath = "\"" + projectPath + File.separator + jarName + "\"";
        String expectedValue = "0";
        String param = jarPath + " Valore atteso: " + expectedValue;
        boolean logic = true;
        
        ExitStatusCondition expResult1 = new ExitStatusCondition(jarPath, 0, logic);
        Condition result = base.handle(request, param, logic);
        assertEquals(expResult1.getClass(), result.getClass());
    }
    
    @Test
    public void testHandlefileExistence(){
        String request = "Il file";
        String fileName = "file_esistente.txt";
        String folderPath = System.getProperty("user.dir"); 
        String param = fileName + " esiste nella cartella: " + folderPath;
        boolean logic = false;
        
        FileExistenceCondition expResult1 = new FileExistenceCondition(folderPath, fileName, logic);
        Condition result = base.handle(request, param, logic);
        assertEquals(expResult1.getClass(), result.getClass());
    }
    
    @Test
    public void testHandlefileExistenceNOT(){
        String request = "Il file";
        String fileName = "file_esistente.txt";
        String folderPath = System.getProperty("user.dir"); 
        String param = fileName + " esiste nella cartella: " + folderPath;
        boolean logic = true;
        
        FileExistenceCondition expResult1 = new FileExistenceCondition(folderPath, fileName, logic);
        Condition result = base.handle(request, param, logic);
        assertEquals(expResult1.getClass(), result.getClass());
    }
    
    @Test
    public void testHandlefileSize(){
        String request = "Il file selezionato";
        String folderPath = System.getProperty("user.dir"); 
        String filePath= folderPath.replace("\\", "\\\\") + File.separator + "\\" +  "file_esistente.txt";
        String minSize = "5";
        String param = filePath + " ha dimensione maggiore di: " + minSize;
        boolean logic = false;
        
        FileSizeCondition expResult1 = new FileSizeCondition(filePath, 5, logic);
        Condition result = base.handle(request, param, logic);
        assertEquals(expResult1.getClass(), result.getClass());
    }
    
    @Test
    public void testHandlefileSizeNOT(){
        String request = "Il file selezionato";
        String folderPath = System.getProperty("user.dir"); 
        String filePath= folderPath.replace("\\", "\\\\") + File.separator + "\\" +  "file_esistente.txt";
        String minSize = "5";
        String param = filePath + " ha dimensione maggiore di: " + minSize;
        boolean logic = true;
        
        FileSizeCondition expResult1 = new FileSizeCondition(filePath, 5, logic);
        Condition result = base.handle(request, param, logic);
        assertEquals(expResult1.getClass(), result.getClass());
    }
}
