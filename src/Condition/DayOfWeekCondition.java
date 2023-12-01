package Condition;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

/**
 *
 * @author Palma Orlando
 */
public class DayOfWeekCondition implements Condition {
    private String daySelected;
    private boolean checkedToday;

    public DayOfWeekCondition(String daySelected) {
        this.daySelected = daySelected;
    }

    @Override
    public boolean checkCondition(){
        String dayUpperCase = daySelected.toUpperCase();
        // Ottenere il giorno della settimana corrente
        DayOfWeek giornoSettimanaCorrente = LocalDate.now().getDayOfWeek();

        // Confronto l'input con il giorno della settimana corrente
        boolean cond = dayUpperCase.equals(giornoSettimanaCorrente.getDisplayName(TextStyle.FULL, Locale.getDefault()).toUpperCase());
        
        if (cond && !checkedToday){
            checkedToday = true;
            return true;
        }
        if(!cond){
            checkedToday = false;
        }
        return false;        
    }
}