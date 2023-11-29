/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ConditionHandlers;

import Condition.*;

/**
 *
 * @author Simone
 */
public class DayOfYearConditionHandler extends BaseConditionHandler{
    
    
    @Override
    public Condition handle(String request, String param) {
        if(request.compareTo("Il") == 0){
            String []values = param.split("/");
            Integer day = Integer.valueOf(values[0]);
            Integer month = Integer.valueOf(values[1]);
            Condition c = new DayOfYearCondition(month, day);
            return c;
        }else{
            if(next != null){
                return next.handle(request, param);
            }
        }
        return null;
    }
}
