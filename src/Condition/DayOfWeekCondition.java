package Condition;

import java.io.Serializable;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

/**
 *
 * @author Palma Orlando
 */
public class DayOfWeekCondition implements Condition, Serializable {
    private String daySelected;
    private boolean checkedToday;
    private boolean not;

    public DayOfWeekCondition(String daySelected, boolean not) {
        this.daySelected = daySelected;
        this.not = not;
    }

    @Override
    public boolean checkCondition(){
        String dayUpperCase = daySelected.toUpperCase();
        // Ottenere il giorno della settimana corrente
        DayOfWeek giornoSettimanaCorrente = LocalDate.now().getDayOfWeek();

        // Confronto l'input con il giorno della settimana corrente
        boolean cond = dayUpperCase.equals(giornoSettimanaCorrente.getDisplayName(TextStyle.FULL, Locale.getDefault()).toUpperCase());
        
        if (cond && !checkedToday) {
            return !not;  // Se cond è vera e checkedToday è falso, restituisci il valore di !not
        } else if (!cond && not) {
            checkedToday = true;
            return true;  // Se cond è falsa e not è true, setta checkedToday a true e restituisci true
        }

        checkedToday = !not; // Altrimenti, setta checkedToday a !not
        return not;  // Restituisci il valore di not
    }
    
    @Override
    public void resetState() {
        this.checkedToday = true;
    }
}