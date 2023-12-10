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
    private Integer lastMonthChecked;
    private Integer lastDayChecked;
    private boolean checkedToday;
    private boolean not;

    public DayOfYearCondition(Integer month, Integer day, boolean not) {
        this.month = month;
        this.day = day;
        this.not = not;
        LocalDate currentDate = LocalDate.now();
        lastMonthChecked = currentDate.getMonthValue();
        lastDayChecked = currentDate.getDayOfMonth();
        
    }

    
    @Override
    public boolean checkCondition() {
        boolean exit;
        LocalDate currentDate = LocalDate.now();
        // Get the current day and month as integers
        Integer currentMonth = currentDate.getMonthValue();
        Integer currentDay = currentDate.getDayOfMonth();
        
        
        boolean cond = month.equals(currentMonth) && day.equals(currentDay);
        if(!cond){
            if (!lastMonthChecked.equals(currentMonth) || !lastDayChecked.equals(currentDay)){
                checkedToday = false;
                lastMonthChecked = currentMonth;
                lastDayChecked = currentDay;
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
