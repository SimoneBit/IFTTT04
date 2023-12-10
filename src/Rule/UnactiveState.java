
package Rule;

import java.io.Serializable;

/**
 * Classe che indetifica lo stato inattivo di una regola.
 * @author Simone
 */
public class UnactiveState implements ActivationState, Serializable {

    
    /**
     * Metodo per l'esecuzione delle azioni nella regola
     * @param rule Regola che contiene le azioni da eseguire
     * @return 
     */
    @Override
    public boolean executeRule(Rule rule) {
        return true;    //nello stato disattivo non vengono eseguite azioni ma viene restituito true 
    }

    
    /**
     * Metodo per il controllo delle condizioni nella regola
     * @param rule Regola che contiene le condizioni da controllare
     * @return 
     */
    @Override
    public boolean checkRule(Rule rule) {
        return false; //nello stato disattivo non vengono controllate le condizioni ma viene restituito direttamente false
    }

    @Override
    public boolean isActive() {
        return false;   //corrispettivo boolean di inattivo
    }
    
}
