/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ActionHandlers;

import Action.Action;

/**
 *
 * @author Simone
 */
public class BaseActionHandler implements ActionHandler{
    ActionHandler next;
    
    @Override
    public void setNext(ActionHandler h) {
        this.next = h;
    }

    @Override
    public Action handle(String request, String param) {
        return next.handle(request, param);
    }

    public ActionHandler getNext() {
        return next;
    }
    
    
}
