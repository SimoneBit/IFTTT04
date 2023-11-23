package Condition;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Palma
 */
public class TimeOfDayConditionTest {
    
    @Test
    public void checkTriggerReturnsTrueWhenCurrentTimeMatchesSpecifiedTime() {
        // Imposta un orario specificato
        String orarioSpecificato = "15:58";

        // Crea un'istanza di TimeOfDayCondition con l'orario specificato
        TimeOfDayCondition timeCondition = new TimeOfDayCondition(orarioSpecificato);

        // Ottieni l'orario attuale
        LocalTime orarioAttuale = LocalTime.now().truncatedTo(ChronoUnit.MINUTES);
        
        // Verifica che checkTrigger restituisca true quando l'orario attuale è uguale all'orario specificato
        assertTrue(timeCondition.checkTrigger()); 
    } 
    
    @Test
    public void checkTriggerReturnsFalseWhenCurrentTimeDoesNotMatchSpecifiedTime() {
        // Imposta un orario specificato
        String orarioSpecificato = "15:59";

        // Crea un'istanza di TimeOfDayCondition con l'orario specificato
        TimeOfDayCondition timeCondition = new TimeOfDayCondition(orarioSpecificato);

        // Ottieni l'orario attuale
        LocalTime orarioAttuale = LocalTime.now().truncatedTo(ChronoUnit.MINUTES);
        
        // Verifica che checkTrigger restituisca false quando l'orario attuale è diverso dall'orario specificato
        assertFalse(timeCondition.checkTrigger()); 
    }
    
}
