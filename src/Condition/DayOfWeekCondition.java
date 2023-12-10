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
    DayOfWeek lastCheck;

    public DayOfWeekCondition(String daySelected, boolean not) {
        this.daySelected = daySelected;
        this.not = not;
        lastCheck = LocalDate.now().getDayOfWeek();
    }

    @Override
    public boolean checkCondition(){
        boolean exit;
        String dayUpperCase = daySelected.toUpperCase();
        // Ottenere il giorno della settimana corrente
        DayOfWeek now = LocalDate.now().getDayOfWeek();

        // Confronto l'input con il giorno della settimana corrente
        boolean cond = dayUpperCase.equals(now.getDisplayName(TextStyle.FULL, Locale.getDefault()).toUpperCase());
        if(!cond){
            if (!lastCheck.equals(now)){
                checkedToday = false;
                lastCheck = now;
            }
        }
        if (!checkedToday && (cond ^ not)){
            exit = true;
        }else{
            exit = false;
            if(!(cond ^ not)){
                checkedToday = false;
            }
        }
        return exit;
    }
    
    @Override
    public void resetState() {
        this.checkedToday = true;
    }
}