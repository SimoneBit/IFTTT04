package Rule;

import Action.Action;
import Condition.Condition;
import Condition.Trigger;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Simone Pacifico
 */
public class ActiveState implements ActivationState, Serializable{

    
    /**
     * Metodo per l'esecuzione delle azioni nella regola
     * @param rule Regola che contiene le azioni da eseguire
     * @return 
     */
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

    /**
     * Metodo per il controllo delle condizioni nella regola
     * @param rule Regola che contiene le condizioni da controllare
     * @return 
     */
    @Override
    public boolean checkRule(Rule rule) {
        Trigger t = rule.getTrigger();
        return t.checkTrigger();    //restituisce il valore del controllo sul trigger
    }

    @Override
    public boolean isActive() {
        return true;    //corrispettivo booleano di attivo
    }
    
    
}
