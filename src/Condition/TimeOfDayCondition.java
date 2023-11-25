package Condition;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 *
 * @author Palma
 */
public class TimeOfDayCondition implements ConditionHandler {
    private LocalTime specifiedTime;  
    private boolean checkedToday;
    
    public TimeOfDayCondition(String orarioSpecificato) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        this.specifiedTime = LocalTime.parse(orarioSpecificato, formatter);
        this.checkedToday = false;
    }

    @Override
    public boolean checkCondition() {
        boolean cond = LocalTime.now().truncatedTo(ChronoUnit.MINUTES).equals(specifiedTime);
        if (cond && !checkedToday){
            checkedToday = true;
            return true;
        }
        if(!cond){
            checkedToday = false;
        }
        return false;
    }

    @Override
    public String toString() {
        return "TimeOfDayCondition{" + "specifiedTime=" + specifiedTime + '}';
    }
    
    
}
