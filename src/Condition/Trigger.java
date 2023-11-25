package Condition;

/**
 *La classe Trigger rappresenta un trigger che può essere attivato in base al risultato di una condizione specifica.
 * Un trigger è associato ad una condizione e può essere attivato invocando il metodo @see #checkTrigger() che,
 * a sua volta, valuta la condizione associata mediante il metodo @see #checkCondition() dell'oggetto @see Condition.
 * 
 * @author Palma Orlando
 */
public class Trigger {
    
    private Condition condition;

    
    /**
     * Costruisce un'istanza di Trigger associata alla condizione specificata.
     * @param condition La condizione associata al trigger.
     */
    public Trigger(Condition condition) {
        this.condition = condition;
    }
 
    
 /**
  * Verifica se il trigger si è attivato valutando la condizione associata.
  * @return true se il trigger è attivato, altrimenti false.
  */   
    public boolean checkTrigger(){
        return condition.checkCondition();
    }

    
    
    /**
     * Restituisce la condizione associata al trigger.
     * @return L'oggetto @see Condition associato al trigger.
     */
    public Condition getCondition() {
        return condition;
    }

    
    
    /**
     * Restituisce una rappresentazione stringa dell'oggetto Trigger, mostrando la condizione associata.     *
     * @return Una stringa che rappresenta l'oggetto.
     */
    @Override
    public String toString() {
        return "Trigger{" + "condition=" + condition.toString() + '}';
    }
      
}
