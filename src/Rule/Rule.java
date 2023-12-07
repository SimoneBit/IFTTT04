    package Rule;

import Action.Action;
import Condition.Trigger;
import java.io.Serializable;
import java.time.Duration;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

/**
 * La classe Rule rappresenta una regola che associa un trigger a un'azione all'interno di un sistema di 
 * automazione. Ogni regola può essere attivata o disattivata e contiene informazioni come il nome, 
 * il trigger associato, l'azione associata e lo stato di attivazione.
 * 
 * @author Nicola Lanzara
 */
public class Rule implements Serializable{
    private String name;
    private Trigger trigger;
    private ArrayList<Action>action;

    public LocalTime getLastChecked() {
        return lastChecked;
    }

    public void setLastChecked(LocalTime lastChecked) {
        this.lastChecked = lastChecked;
    }

    public boolean isExecuteOnce() {
        return executeOnce;
    }

    public void setExecuteOnce(boolean executeOnce) {
        this.executeOnce = executeOnce;
    }
    private boolean Active;
    private int sleepingPeriod;
    private LocalTime lastChecked;
    private boolean sleeping;
    private boolean executeOnce;

    
    /**
     *Costruisce un'istanza di @see Rule con il nome specificato, il trigger associato e l'azione associata. 
     * La regola è inizialmente attivata.
     *
     * @param name il nome della regola.
     * @param trigger il trigger associato alla regola.
     * @param action l'azione associata alla regola.
     */
    public Rule(String name, Trigger trigger, ArrayList<Action> action, int sleepingPeriod, boolean executeOnce) {
        this.name = name;
        this.trigger = trigger;
        this.action = action;
        this.Active = true;
        this.sleepingPeriod= sleepingPeriod;
        this.sleeping=false;
        this.executeOnce=executeOnce;
    }

    
    public boolean checkSleepingState(){
        boolean exit = false;
        LocalTime currentDate= LocalTime.now().truncatedTo(ChronoUnit.SECONDS);
        if(sleepingPeriod == 0){
            return exit;
        }
        if(lastChecked == null) {
            lastChecked = currentDate;
            return exit;     
        }
        else {
           
           long difference=  Duration.between(lastChecked, currentDate).getSeconds();
           if(sleepingPeriod<= difference){
             lastChecked = currentDate;
             return exit;
             
         }
           else{
               if(sleepingPeriod> difference);
              exit=true;           
          }
      }
        return exit;
    }

    public boolean isSleeping() {
       this.sleeping = checkSleepingState();
       return sleeping;
    }
    
    public boolean executeRule(){
        boolean exit=false;
        
            for(Action a : this.action){
            
            if(a.executeAction()==false){
                return false;
            }
            else{
                exit=true;
            }
            
        }
            if(this.executeOnce){
                this.setActive(false);
            
            }
        return exit;
    }
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
    public ArrayList<Action> getAction() {
        return this.action;
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
   public void setSleepingPeriod(int sleepingPeriod) {
    this.sleepingPeriod = sleepingPeriod;
}
 
}
