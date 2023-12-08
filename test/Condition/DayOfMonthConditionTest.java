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
        
        DayOfMonthCondition cond = new DayOfMonthCondition(currentDay);
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
        DayOfMonthCondition cond = new DayOfMonthCondition(currentDay);
        assertFalse(cond.checkCondition());
    }
    
}
