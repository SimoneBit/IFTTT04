package Rule;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * La classe RulesSet rappresenta un insieme di regole di automazione.
 * Contiene metodi per l'aggiunta, la rimozione e l'accesso alle regole nell'insieme.
 * @author Nicola Lanzara
 */

public class RulesSet implements Serializable{
    private List<Rule> ruleList;

    /**
     * Costruisce un'istanza di @see RulesSet inizializzando la lista delle regole come una nuova ArrayList.
     */
    public RulesSet() {
        this.ruleList = new ArrayList<>();
    }

    /**
     * Aggiunge una nuova regola all'insieme.
     * @param newRule la nuova regola da aggiungere.
     */
    public void addRule(Rule newRule) {
        
        ruleList.add(newRule);
    }

  /**
   * Rimuove una regola dall'insieme.
   * @param rule la regola da rimuovere.
   */  
    public void removeRule(Rule rule) {
        
        ruleList.remove(rule);
    }
    
    /**
     * Restituisce la lista delle regole nell'insieme.
     * @return la lista delle regole nell'insieme.
     */
    public List<Rule> getRuleList() {
        return ruleList;
    }
    
/**
 * Imposta la lista delle regole nell'insieme. 
 * @param ruleList la nuova lista delle regole.
 */
    public void setRuleList(List<Rule> ruleList) {
        this.ruleList = ruleList;
    }

    @Override
    public String toString() {
        return "RulesSet{" + "ruleList=" + ruleList + '}';
    }
 

}
