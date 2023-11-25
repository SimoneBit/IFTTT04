/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ConditionHandlers;

import Condition.ConditionHandler;

/**
 *
 * @author Simone
 */
public interface ConditionHandler {
    public void setNext(ConditionHandler h);
    public abstract ConditionHandler handle(String request, String param);
    
}
