/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Rule;

import Action.Action;
import Condition.Trigger;

/**
 *
 * @author Simone
 */
public class Rule {
    private String name;
    private Trigger trigger;
    private Action action;
    private boolean Active;

    public Rule(String name, Trigger trigger, Action action) {
        this.name = name;
        this.trigger = trigger;
        this.action = action;
        this.Active = true;
    }

    
    public boolean checkRule(){
        boolean exit = false;
        if(trigger.checkTrigger() == true){
            action.executeAction();
            exit = true;
        }        
        return exit;
    }
    
    public boolean isActive() {
        return Active;
    }

    public void setActive(boolean Active) {
        this.Active = Active;
    }
    
    
}
