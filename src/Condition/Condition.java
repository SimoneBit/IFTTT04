package Condition;

/**
 *L'interfaccia Condition rappresenta una condizione che può essere valutata per determinare se è soddisfatta 
 * o meno.
 * Gli oggetti che implementano questa interfaccia devono fornire un'implementazione per il metodo @see checkCondition()
 * che restituisce un valore booleano che indica se la condizione è soddisfatta o meno.
 * 
 * @author Palma Orlando
 */
public interface Condition {
    
    
    /**
     * Valuta la condizione e restituisce un valore booleano che indica se la condizione è soddisfatta.
     * @return true se la condizione è soddisfatta, altrimenti false.
     */
    public boolean checkCondition();
    
    /**
     * Resetta lo stato della regola.
     */
    public void resetState();
}
