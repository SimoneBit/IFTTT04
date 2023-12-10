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
    Integer lastCheck;
    
    public DayOfMonthCondition(Integer day, boolean not) {
        this.day = day;
        this.not = not;
        lastCheck = LocalDate.now().getDayOfMonth();
    }
    
    
    @Override
    public boolean checkCondition() {
        boolean exit;
        LocalDate currentDate = LocalDate.now();
        // Get the current day as integers
        Integer now = currentDate.getDayOfMonth();
        boolean cond = day.equals(now);
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
