/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package Rule;

import Action.Action;
import Condition.Condition;
import Condition.Trigger;
import java.time.LocalTime;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author miria
 */
public class RuleTest {

    private Rule rule;

    @Before
    public void setUp() {
        Condition condition = null;
        Trigger trigger = new Trigger(condition);
        Action action = null;
        int sleepingPeriod = 10; // Imposta il periodo di riposo come necessario;
        boolean executeOnce = false; // Imposta executeOnce come necessario;

        rule = new Rule("TestRule", trigger, action, sleepingPeriod, executeOnce);
    }

    @Test
    public void testCheckSleepingState_NoSleepingPeriod() {
        // Imposta il periodo di riposo a 0
        rule.setSleepingPeriod(0);

        // Verifica che il risultato sia false quando il periodo di riposo è 0
        assertFalse(rule.checkSleepingState());
    }

    @Test
    public void testCheckSleepingState_FirstTimeCheck() {
        // Imposta il periodo di riposo a un valore qualsiasi
        rule.setSleepingPeriod(10);

        // Verifica che il risultato sia false alla prima chiamata
        assertFalse(rule.checkSleepingState());
    }



    @Test
    public void testCheckSleepingState_SleepingPeriodElapsed() {
        // Imposta il periodo di riposo a un valore qualsiasi
        rule.setSleepingPeriod(10);

        // Imposta l'ultimo controllo a 11 secondi fa
        rule.setLastChecked(LocalTime.now().minusSeconds(11));

        // Verifica che il risultato sia true poiché il periodo di riposo è trascorso
        assertTrue(rule.checkSleepingState());
    }

   
}