/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ConditionHandlers;

import Condition.ConditionHandler;

/**
 *
 * @author Simone
 */
public class BaseConditionHandler implements ConditionHandler{
    ConditionHandler next;
    @Override
    public void setNext(ConditionHandler h) {
        this.next = h;  
    }

    @Override
    public ConditionHandler handle(String request, String param) {
        return next.handle(request, param);
    }
    
}
