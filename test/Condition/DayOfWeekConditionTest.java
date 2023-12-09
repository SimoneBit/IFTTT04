package Condition;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Palma Orlando
 */
public class DayOfWeekConditionTest {
    
    public DayOfWeekConditionTest() {
    }

    /**
     * Test of checkCondition method, of class DayOfWeekCondition.
     */
 @Test
    public void testCheckConditionReturnsTrueWhenCurrentDayMatchesSpecifiedDay() {
        // Ottengo il giorno attuale
        DayOfWeek actualDay = LocalDate.now().getDayOfWeek();
        String specifiedDay = actualDay.getDisplayName(TextStyle.FULL, Locale.getDefault());
        // Creo un'istanza di DayOfWeekCondition con il giorno specificato
        DayOfWeekCondition dayCondition = new DayOfWeekCondition(specifiedDay, false);
        // Verifico che checkCondition restituisca true quando il giorno attuale è uguale al giorno specificato
        assertTrue(dayCondition.checkCondition());
    }



    @Test
    public void testCheckConditionReturnsFalseWhenCurrentDayDoesNotMatchSpecifiedDay() {
        // Imposto un giorno specificato diverso dal giorno attuale
        String giornoSpecificato = "Mercoledì";

        // Creo un'istanza di DayOfWeekCondition con il giorno specificato
        DayOfWeekCondition dayCondition = new DayOfWeekCondition(giornoSpecificato, false);

        // Ottengo il giorno attuale
        DayOfWeek giornoAttuale = LocalDate.now().getDayOfWeek();

        // Verifico che checkCondition restituisca false quando il giorno attuale è diverso dal giorno specificato
        assertFalse(dayCondition.checkCondition());
    }
    
    @Test
    public void testCheckConditionReturnsFalseWhenCurrentDayMatchesSpecifiedDayNOT() {
        // Ottengo il giorno attuale
        DayOfWeek actualDay = LocalDate.now().getDayOfWeek();
        String specifiedDay = actualDay.getDisplayName(TextStyle.FULL, Locale.getDefault());
        // Creo un'istanza di DayOfWeekCondition con il giorno specificato
        DayOfWeekCondition dayCondition = new DayOfWeekCondition(specifiedDay, true);
        // Verifico che quando è attiva la logica NOT, checkCondition restituisca false quando il giorno attuale è lo stesso del giorno specificato
        assertFalse(dayCondition.checkCondition());
    }

    @Test
    public void testCheckConditionReturnsTrueWhenCurrentDayDoesNotMatchSpecifiedDayNOT() {
        // Imposto un giorno specificato diverso dal giorno attuale
        String giornoSpecificato = "Mercoledì";

        // Creo un'istanza di DayOfWeekCondition con il giorno specificato
        DayOfWeekCondition dayCondition = new DayOfWeekCondition(giornoSpecificato, true);

        // Ottengo il giorno attuale
        DayOfWeek giornoAttuale = LocalDate.now().getDayOfWeek();

        // Verifico che quando è attiva la logica NOT, checkCondition restituisca true quando il giorno attuale è lo stesso del giorno specificato
        assertTrue(dayCondition.checkCondition());
    }
}
