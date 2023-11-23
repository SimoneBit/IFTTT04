/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ActionHandlers;

import Action.*;

/**
 *
 * @author Simone
 */
public interface ActionHandler {
    public void setNext(ActionHandler h);
    public abstract Action handle(String request, String param);
}
