package Condition;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
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
        LocalTime time = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        String orarioSpecificato = time.format(formatter);

        // Crea un'istanza di TimeOfDayCondition con l'orario specificato
        TimeOfDayCondition timeCondition = new TimeOfDayCondition(orarioSpecificato, false);

        // Ottiene l'orario attuale
        LocalTime orarioAttuale = LocalTime.now().truncatedTo(ChronoUnit.MINUTES);
        
        // Verifica che checkTrigger restituisca true quando l'orario attuale è uguale all'orario specificato
        assertTrue(timeCondition.checkCondition()); 
    } 
    
    @Test
    public void checkTriggerReturnsFalseWhenCurrentTimeDoesNotMatchSpecifiedTime() {
        // Imposta un orario specificato
        String orarioSpecificato = "15:59";

        // Crea un'istanza di TimeOfDayCondition con l'orario specificato
        TimeOfDayCondition timeCondition = new TimeOfDayCondition(orarioSpecificato, false);

        // Ottieni l'orario attuale
        LocalTime orarioAttuale = LocalTime.now().truncatedTo(ChronoUnit.MINUTES);
        
        // Verifica che checkTrigger restituisca false quando l'orario attuale è diverso dall'orario specificato
        assertFalse(timeCondition.checkCondition()); 
    }
    
    @Test
    public void checkTriggerReturnsFalseWhenCurrentTimeMatchesSpecifiedTimeNOT() {
        // Imposta un orario specificato
        LocalTime time = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        String orarioSpecificato = time.format(formatter);

        // Crea un'istanza di TimeOfDayCondition con l'orario specificato
        TimeOfDayCondition timeCondition = new TimeOfDayCondition(orarioSpecificato, true);

        // Ottiene l'orario attuale
        LocalTime orarioAttuale = LocalTime.now().truncatedTo(ChronoUnit.MINUTES);
        
        // Verifica che checkTrigger restituisca false quando l'orario attuale è uguale all'orario specificato
        assertFalse(timeCondition.checkCondition()); 
    } 

    @Test
    public void checkTriggerReturnsTrueWhenCurrentTimeDoesNotMatchSpecifiedTimeNOT() {
        // Imposta un orario specificato
        String orarioSpecificato = "15:59";

        // Crea un'istanza di TimeOfDayCondition con l'orario specificato
        TimeOfDayCondition timeCondition = new TimeOfDayCondition(orarioSpecificato, true);

        // Ottieni l'orario attuale
        LocalTime orarioAttuale = LocalTime.now().truncatedTo(ChronoUnit.MINUTES);
        
        // Verifica che checkTrigger restituisca true quando l'orario attuale è diverso dall'orario specificato
        assertTrue(timeCondition.checkCondition()); 
    }
}

