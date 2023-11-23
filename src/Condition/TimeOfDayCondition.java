package Condition;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 *
 * @author Palma
 */
public class TimeOfDayCondition implements Condition {
    private LocalTime specifiedTime;   
    
    public TimeOfDayCondition(String orarioSpecificato) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        this.specifiedTime = LocalTime.parse(orarioSpecificato, formatter);
    }

    @Override
    public boolean checkCondition() {
        return LocalTime.now().truncatedTo(ChronoUnit.MINUTES).equals(specifiedTime);
    }
    
}
