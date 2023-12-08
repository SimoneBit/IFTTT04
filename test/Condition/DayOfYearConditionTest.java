/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package Condition;

import java.time.LocalDate;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Simone
 */
public class DayOfYearConditionTest {
    
    public DayOfYearConditionTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }

    /**
     * Test of checkCondition method, of class DayOfYearCondition.
     */
    @Test
    public void testCheckConditionTrue() {
        LocalDate currentDate = LocalDate.now();
        // Get the current day and month as integers
        Integer currentMonth = currentDate.getMonthValue();
        Integer currentDay = currentDate.getDayOfMonth();
        
        DayOfYearCondition cond = new DayOfYearCondition(currentMonth, currentDay);
        assertTrue(cond.checkCondition());
    }
    
   
    
    public void testCheckConditionFalse() {
        LocalDate currentDate = LocalDate.now();
        // Get the current day and month as integers
        Integer currentMonth = currentDate.getMonthValue();
        Integer currentDay = currentDate.getDayOfMonth();
        if(currentDay != 15){
            currentDay = 15;
        }else{
            currentDay++;
        }
        DayOfYearCondition cond = new DayOfYearCondition(currentMonth, currentDay);
        assertFalse(cond.checkCondition());
    }
    
    
    
}
