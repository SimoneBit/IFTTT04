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
    private ActivationState Active;
    private int sleepingPeriod;
    private LocalTime lastChecked;
    private boolean sleeping;
    private boolean executeOnce;

    /**
     * Restituisce l'orario dell'ultima verifica.
     * 
     * @return l'orario dell'ultima verifica.
     */
    public LocalTime getLastChecked() {
        return lastChecked;
    }

    /**
     * Imposta l'orario dell'ultima verifica.
     * 
     * @param lastChecked l'orario dell'ultima verifica da impostare.
     */
    public void setLastChecked(LocalTime lastChecked) {
        this.lastChecked = lastChecked;
    }

    /**
     * Verifica se l'azione associata deve essere eseguita solo una volta.
     * 
     * @return true se l'azione deve essere eseguita solo una volta, altrimenti false.
     */
    public boolean isExecuteOnce() {
        return executeOnce;
    }

    /**
     * Imposta se l'azione associata deve essere eseguita solo una volta.
     * 
     * @param executeOnce flag che indica se l'azione deve essere eseguita solo una volta.
     */
    public void setExecuteOnce(boolean executeOnce) {
        this.executeOnce = executeOnce;
    }
    
    /**
     *Costruisce un'istanza di @see Rule con il nome specificato, il trigger associato e l'azione associata. 
     * La regola è inizialmente attivata.
     *
     * @param nome il nome della regola.
     * @param trigger il trigger associato alla regola.
     * @param azioni l'insieme di azioni associate alla regola.
     * @param periodoDiAttesa il periodo di attesa associato alla regola.
     * @param eseguiSoloUnaVolta flag che indica se la regola deve essere eseguita solo una volta.
     */
    public Rule(String name, Trigger trigger, ArrayList<Action> action, int sleepingPeriod, boolean executeOnce) {
        this.name = name;
        this.trigger = trigger;
        this.action = action;
        this.Active = new ActiveState();
        this.sleepingPeriod= sleepingPeriod;
        this.sleeping=false;
        this.executeOnce=executeOnce;
    }

    /**
     * Controlla e aggiorna lo stato dormiente di una regola
     * @return nuovo valore di sleeping della regola
     */
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

    /**
    * Verifica se la regola è in stato di attesa.
    * 
    * @return true se la regola è in stato di attesa, altrimenti false.
    */
    public boolean isSleeping() {
       this.sleeping = checkSleepingState();
       return sleeping;
    }
    
    /**
    * Esegue la regola, delegando l'esecuzione al relativo stato attivo.
    * 
    * @return true se l'esecuzione è avvenuta con successo, altrimenti false.
    */
    public boolean executeRule(){
        return Active.executeRule(this);
    }
    
    /**
    * Verifica la condizione associata alla regola, delegando la verifica al relativo stato attivo.
    * 
    * @param rule la regola da verificare.
    * @return true se la condizione è soddisfatta, altrimenti false.
    */
    public boolean checkRule(Rule rule) {
        return Active.checkRule(this);
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
        return Active.isActive();
    }

    /**
     * Imposta lo stato di attivazione della regola.
     *
     * @param Active lo stato di attivazione desiderato.
     */
    public void setActive(boolean Active) {
        if(Active){
            this.Active = new ActiveState();
        }else{
            this.Active = new UnactiveState();
        }
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
