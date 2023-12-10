/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Rule;

import Action.Action;
import Condition.Condition;
import Condition.Trigger;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Simone
 */
public class ActiveState implements ActivationState, Serializable{

    @Override
    public boolean executeRule(Rule rule) {
        ArrayList<Action> action = rule.getAction();
        boolean exit=false;
        
            for(Action a : action){
                 if(a.executeAction()==false){
                    //se almeno una delle azioni dell'ArrayList non è stata eseguita con successo restituisce false
                 return false;
                 }
                else{
                    exit=true;
                 }   
            }
            //executeOnce è utilizzato per quando l'utende vuole che la regola sia eseguita una sola volta e poi si disattivi automaticamente
            if(rule.isExecuteOnce()){
                rule.setActive(false);
            
            }
        return exit;
    }

    @Override
    public boolean checkRule(Rule rule) {
        Trigger t = rule.getTrigger();
        return t.checkTrigger();
    }

    @Override
    public boolean isActive() {
        return true;
    }
    
    
}
