package Condition;

import java.time.LocalDate;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Simone Pacifico
 */
public class DayOfMonthConditionTest {
    
    public DayOfMonthConditionTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }

    /**
     * Test of checkCondition method, of class DayOfMonthCondition.
     */
    @Test
    public void testCheckConditionTrue() {
        LocalDate currentDate = LocalDate.now();
        // Get the current day as integers
        Integer currentDay = currentDate.getDayOfMonth();
        
        DayOfMonthCondition cond = new DayOfMonthCondition(currentDay, false);
        assertTrue(cond.checkCondition());
    }

    
    public void testCheckConditionFalse() {
        LocalDate currentDate = LocalDate.now();
        // Get the current day as integers
        Integer currentDay = currentDate.getDayOfMonth();
        if(currentDay != 15){
            currentDay = 15;
        }else{
            currentDay++;
        }
        DayOfMonthCondition cond = new DayOfMonthCondition(currentDay, false);
        assertFalse(cond.checkCondition());
    }
    
    
    @Test
    public void testCheckConditionFalseNOT() {
        LocalDate currentDate = LocalDate.now();
        // Get the current day as integers
        Integer currentDay = currentDate.getDayOfMonth();
        
        DayOfMonthCondition cond = new DayOfMonthCondition(currentDay, true);
        assertFalse(cond.checkCondition());
    }

    
    public void testCheckConditionTrueNOT() {
        LocalDate currentDate = LocalDate.now();
        // Get the current day as integers
        Integer currentDay = currentDate.getDayOfMonth();
        if(currentDay != 15){
            currentDay = 15;
        }else{
            currentDay++;
        }
        DayOfMonthCondition cond = new DayOfMonthCondition(currentDay, true);
        assertTrue(cond.checkCondition());
    }
}
