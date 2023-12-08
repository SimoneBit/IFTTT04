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
public class DayOfYearCondition implements Condition, Serializable {
    private Integer month;
    private Integer day;
    private int lastMonthChecked;
    private int lastDayChecked;
    private boolean checkedToday;

    public DayOfYearCondition(Integer month, Integer day) {
        this.month = month;
        this.day = day;
    }

    
    @Override
    public boolean checkCondition() {
        LocalDate currentDate = LocalDate.now();
        // Get the current day and month as integers
        Integer currentMonth = currentDate.getMonthValue();
        Integer currentDay = currentDate.getDayOfMonth();
        
        
        boolean cond = month.equals(currentMonth) && day.equals(currentDay);
        if (cond && !checkedToday){
            return true;
        }
        if(!cond){
            checkedToday = false;
        }
        return false;
        
        
        
    }
    
    @Override
    public void resetState() {
        this.checkedToday = true;
    }
}
