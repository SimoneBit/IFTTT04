/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package ConditionHandlers;

import Condition.Condition;
import Condition.DayOfMonthCondition;
import Condition.DayOfYearCondition;
import Condition.TimeOfDayCondition;
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
    
    
    @Before
    public void setUp() {
        base = new BaseConditionHandler();
        time = new TimeConditionHandler();
        dayOfMonth = new DayOfMonthConditionHandler();
        dayOfYear = new DayOfYearConditionHandler();
        
        base.setNext(time);
        time.setNext(dayOfMonth);
        dayOfMonth.setNext(dayOfYear);
    }
    /**
     * Test of setNext method, of class BaseConditionHandler.
     */
    @Test
    public void testSetNext() {
        assertEquals(base.getNext(), time);
        assertEquals(time.getNext(), dayOfMonth);
        assertEquals(dayOfMonth.getNext(), dayOfYear);
    }

    /**
     * Test of handle method, of class BaseConditionHandler.
     */
    @Test
    public void testHandleNull() {
        //Test caso fail
        String request = "";
        String param = "";
        Condition expResult0 = null;
        Condition result = base.handle(request, param);
        assertEquals(expResult0, result);
    }
    
    @Test
    public void testHandleTime() {
        //Test ora del giorno
        String request = "Alle";
        String param = "18:30";
        TimeOfDayCondition expResult1 = new TimeOfDayCondition(param);
        Condition result = base.handle(request, param);
        assertEquals(expResult1.getClass(), result.getClass());
    }
    
    @Test
    public void testHandleDayOfYear(){
        String request = "Il";
        String param = "11/5";
        DayOfYearCondition expResult1 = new DayOfYearCondition(11, 5);
        Condition result = base.handle(request, param);
        assertEquals(expResult1.getClass(), result.getClass());
        
    }
    
    @Test
    public void testHandleDayOfMonth(){
        String request = "Il giorno";
        String param = "2";
        DayOfMonthCondition expResult1 = new DayOfMonthCondition(2);
        Condition result = base.handle(request, param);
        assertEquals(expResult1.getClass(), result.getClass());
    }
}
