/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ConditionHandlers;

import Condition.Condition;
import Condition.DayOfMonthCondition;

/**
 *
 * @author Simone
 */
public class DayOfMonthConditionHandler extends BaseConditionHandler {
    @Override
    public Condition handle(String request, String param){
        if(request.compareTo("Il giorno") == 0){
            Integer day = Integer.valueOf(param);
            Condition c = new DayOfMonthCondition(day);
            return c;
        }else{
            if(next != null){
                return next.handle(request, param);
            }
        }
        return null;
    }
}
