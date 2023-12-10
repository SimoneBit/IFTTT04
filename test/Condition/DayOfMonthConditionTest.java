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
        // Ottieni il giorno corrente come interi
        Integer currentDay = currentDate.getDayOfMonth();
        // Crea una condizione che dovrebbe essere vera con il giorno corrente
        DayOfMonthCondition cond = new DayOfMonthCondition(currentDay, false);
        // Verifica che la condizione sia vera
        assertTrue(cond.checkCondition());
    }

    
    public void testCheckConditionFalse() {
        LocalDate currentDate = LocalDate.now();
        // Ottieni il giorno corrente come interi
        Integer currentDay = currentDate.getDayOfMonth();
        // Imposta il giorno corrente in modo che la condizione sia falsa
        if(currentDay != 15){
            currentDay = 15;
        }else{
            currentDay++;
        }
        // Crea una condizione che dovrebbe essere falsa con il giorno corrente
        DayOfMonthCondition cond = new DayOfMonthCondition(currentDay, false);
        // Verifica che la condizione sia falsa
        assertFalse(cond.checkCondition());
    }
    
    
    @Test
    public void testCheckConditionFalseNOT() {
        LocalDate currentDate = LocalDate.now();
        // Ottieni il giorno corrente come interi
        Integer currentDay = currentDate.getDayOfMonth();
        // Crea una condizione negata che dovrebbe essere falsa con il giorno corrente
        DayOfMonthCondition cond = new DayOfMonthCondition(currentDay, true);
        // Verifica che la condizione negata sia falsa
        assertFalse(cond.checkCondition());
    }

    
    public void testCheckConditionTrueNOT() {
        LocalDate currentDate = LocalDate.now();
        // Ottieni il giorno corrente come interi
        Integer currentDay = currentDate.getDayOfMonth();
        // Imposta il giorno corrente in modo che la condizione negata sia vera
        if(currentDay != 15){
            currentDay = 15;
        }else{
            currentDay++;
        }
        // Crea una condizione negata che dovrebbe essere vera con il giorno corrente
        DayOfMonthCondition cond = new DayOfMonthCondition(currentDay, true);
        // Verifica che la condizione negata sia vera
        assertTrue(cond.checkCondition());
    }
}
