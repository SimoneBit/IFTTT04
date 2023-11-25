/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package ConditionHandlers;

import Condition.Condition;
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
    
    
    @Before
    public void setUp() {
        base = new BaseConditionHandler();
        time = new TimeConditionHandler();
        
        base.setNext(time);
    }
    /**
     * Test of setNext method, of class BaseConditionHandler.
     */
    @Test
    public void testSetNext() {
        assertEquals(base.getNext(), time);
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
        String request = "Ora del giorno";
        String param = "18:30";
        TimeOfDayCondition expResult1 = new TimeOfDayCondition(param);
        Condition result = base.handle(request, param);
        assertEquals(expResult1.getClass(), result.getClass());
    }
    
}
