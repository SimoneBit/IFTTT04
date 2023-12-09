package Condition;

import java.io.Serializable;
import java.time.LocalDate;

/**
 *
 * @author Simone Pacifico
 */
public class DayOfMonthCondition implements Condition, Serializable{
    private Integer day;
    private boolean checkedToday;
    private boolean not;

    public DayOfMonthCondition(Integer day, boolean not) {
        this.day = day;
        this.not = not;
    }
    
    
    @Override
    public boolean checkCondition() {
        LocalDate currentDate = LocalDate.now();
        // Get the current day as integers
        Integer currentDay = currentDate.getDayOfMonth();
        boolean cond = day.equals(currentDay);
        
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
