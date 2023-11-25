package Rule;

import Action.Action;
import Condition.Trigger;

/**
 * La classe Rule rappresenta una regola che associa un trigger a un'azione all'interno di un sistema di 
 * automazione. Ogni regola può essere attivata o disattivata e contiene informazioni come il nome, 
 * il trigger associato, l'azione associata e lo stato di attivazione.
 * 
 * @author Nicola Lanzara
 */
public class Rule {
    private String name;
    private Trigger trigger;
    private Action action;
    private boolean Active;

    
    /**
     *Costruisce un'istanza di @see Rule con il nome specificato, il trigger associato e l'azione associata. 
     * La regola è inizialmente attivata.
     *
     * @param name il nome della regola.
     * @param trigger il trigger associato alla regola.
     * @param action l'azione associata alla regola.
     */
    public Rule(String name, Trigger trigger, Action action) {
        this.name = name;
        this.trigger = trigger;
        this.action = action;
        this.Active = true;
    }

    
    /*public boolean checkRule(){
        boolean exit = false;
        if(trigger.checkTrigger() == true){
            action.executeAction();
            exit = true;
        }        
        return exit;
    }*/
    
    /**
     *Restituisce il nome della regola.
     *
     * @return il nome della regola.
     */
    public String getName() {
        return name;
    }

    /**
     * Verifica se la regola è attiva o inattiva.
     *
     * @return true se la regola è attiva, altrimenti false.
     */
    public boolean isActive() {
        return Active;
    }

    /**
     * Imposta lo stato di attivazione della regola.
     *
     * @param Active lo stato di attivazione desiderato.
     */
    public void setActive(boolean Active) {
        this.Active = Active;
    }

    /**
     * Restituisce il trigger associato alla regola.
     *
     * @return il trigger associato alla regola.
     */
    public Trigger getTrigger() {
        return trigger;
    }

    /**
     *Restituisce l'azione associata alla regola.
     *
     * @return l'azione associata alla regola.
     */
    public Action getAction() {
        return action;
    }

    /**
     * Restituisce una rappresentazione stringa dell'oggetto @see Rule, mostrando il nome e lo stato di attivazione 
     * della regola.
     *
     * @return una stringa che rappresenta l'oggetto.
     */
    @Override
    public String toString() {
        return "Rule{" + "name=" + name + ", Active=" + Active + '}';
    }

   
    
    
}
