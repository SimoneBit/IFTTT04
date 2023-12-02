/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Condition;

import java.io.Serializable;
import java.time.LocalDate;

/**
 *
 * @author Simone
 */
public class DayOfMonthCondition implements Condition, Serializable{
    private Integer day;
    private int lastDayChecked;
    private boolean checkedToday;

    public DayOfMonthCondition(Integer day) {
        this.day = day;
    }
    
    
    @Override
    public boolean checkCondition() {
        LocalDate currentDate = LocalDate.now();
        // Get the current day as integers
        Integer currentDay = currentDate.getDayOfMonth();
        
        
        boolean cond = day.equals(currentDay);
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
