package ConditionHandlers;

import Condition.Condition;

/**
 *La classe BaseConditionHandler implementa l'interfaccia @see ConditionHandler e fornisce un'implementazione 
 * di base per la gestione delle condizioni in una catena di responsabilità. Ogni gestore di condizioni nella catena è 
 * collegato al successivo attraverso l'attributo next.
 * 
 * Questa classe fornisce metodi per impostare il successivo gestore di condizioni nella catena e per gestire una richiesta 
 * di condizione passandola al successivo gestore, se presente.
 * @author Palma Orlando
 */
public class BaseConditionHandler implements ConditionHandler{
    ConditionHandler next;
    
    
    /**
     * Imposta il successivo gestore di condizioni nella catena di responsabilità.
     * @param h il successivo gestore di condizioni nella catena.
     */
    @Override
    public void setNext(ConditionHandler h) {
        this.next = h;  
    }
 
    
    
    /**
     * Gestisce la richiesta di condizione passandola al successivo gestore nella catena, se presente.
     *
     * @param request la richiesta di condizione da gestire.
     * @param param aggiuntivi necessari per la gestione della richiesta di condizione.
     * @param logic se true, applica la logica invertita del NOT alla condizione, se false, applica la logica normale.
     * @return il risultato della gestione da parte del successivo gestore nella catena.
     */
    @Override
    public Condition handle(String request, String param, boolean logic) {
        return next.handle(request, param, logic);
    }

    
    
    /**
     * Restituisce il successivo gestore di condizioni nella catena.
     * @return l successivo gestore di condizioni nella catena.
     */
    Object getNext() {
        return next;
    }
    
}
