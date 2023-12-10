package Condition;

import java.time.LocalDate;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Simone Pacifico
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
        // Ottieni il giorno e il mese corrente come numeri interi
        Integer currentMonth = currentDate.getMonthValue();
        Integer currentDay = currentDate.getDayOfMonth();
        // Crea una condizione che dovrebbe essere vera con il mese e il giorno corrente
        DayOfYearCondition cond = new DayOfYearCondition(currentMonth, currentDay, false);
        // Verifica che la condizione sia vera
        assertTrue(cond.checkCondition());
    }
    
   
    
    public void testCheckConditionFalse() {
        LocalDate currentDate = LocalDate.now();
        // Ottieni il giorno e il mese corrente come numeri interi
        Integer currentMonth = currentDate.getMonthValue();
        Integer currentDay = currentDate.getDayOfMonth();
        // Imposta il giorno corrente in modo che la condizione sia falsa
        if(currentDay != 15){
            currentDay = 15;
        }else{
            currentDay++;
        }
        // Crea una condizione che dovrebbe essere falsa con il mese e il giorno corrente
        DayOfYearCondition cond = new DayOfYearCondition(currentMonth, currentDay, false);
        // Verifica che la condizione sia falsa
        assertFalse(cond.checkCondition());
    }
    
    
    @Test
    public void testCheckConditionFalseNOT() {
        LocalDate currentDate = LocalDate.now();
        // Ottieni il giorno e il mese corrente come numeri interi
        Integer currentMonth = currentDate.getMonthValue();
        Integer currentDay = currentDate.getDayOfMonth();
        // Crea una condizione negata che dovrebbe essere falsa con il mese e il giorno corrente
        DayOfYearCondition cond = new DayOfYearCondition(currentMonth, currentDay, true);
        // Verifica che la condizione negata sia falsa
        assertFalse(cond.checkCondition());
    }
    
   
    
    public void testCheckConditionTrueNOT() {
        LocalDate currentDate = LocalDate.now();
        // Ottieni il giorno e il mese corrente come numeri interi
        Integer currentMonth = currentDate.getMonthValue();
        Integer currentDay = currentDate.getDayOfMonth();
        if(currentDay != 15){
            currentDay = 15;
        }else{
            currentDay++;
        }
        // Crea una condizione negata che dovrebbe essere vera con il mese e il giorno corrente
        DayOfYearCondition cond = new DayOfYearCondition(currentMonth, currentDay, true);
        // Verifica che la condizione negata sia vera
        assertTrue(cond.checkCondition());
    }
    
    
}
