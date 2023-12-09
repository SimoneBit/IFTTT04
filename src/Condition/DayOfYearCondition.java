package Condition;

import java.io.Serializable;
import java.time.LocalDate;
/**
 *
 * @author Simone Pacifico
 */
public class DayOfYearCondition implements Condition, Serializable {
    private Integer month;
    private Integer day;
    private int lastMonthChecked;
    private int lastDayChecked;
    private boolean checkedToday;
    private boolean not;

    public DayOfYearCondition(Integer month, Integer day, boolean not) {
        this.month = month;
        this.day = day;
        this.not = not;
    }

    
    @Override
    public boolean checkCondition() {
        LocalDate currentDate = LocalDate.now();
        // Get the current day and month as integers
        Integer currentMonth = currentDate.getMonthValue();
        Integer currentDay = currentDate.getDayOfMonth();
        
        
        boolean cond = month.equals(currentMonth) && day.equals(currentDay);
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
