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
public class TimeConditionHandler extends BaseConditionHandler{
    
    @Override
    public ConditionHandler handle(String request, String param) {
        if(request.compareTo("Alle") == 0){
            ConditionHandler c = new TimeOfDayCondition(param);
            return c;
        }else{
            if(next != null){
                return next.handle(request, param);
            }
        }
        return null;
    }
    
    
}
