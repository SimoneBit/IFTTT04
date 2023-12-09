package ConditionHandlers;

import Condition.Condition;

/**
 * L'interfaccia ConditionHandler stabilisce le regole per i gestori delle condizioni all'interno di una catena di 
 * responsabilità. Ogni gestore di condizioni può essere associato a un successivo gestore nella catena gestire una 
 * richiesta di condizione specifica.
 * 
 * Gli oggetti che implementano questa interfaccia devono fornire un'implementazione per il metodo @see #setNext che 
 * permette di impostare il successivo gestore di condizioni nella catena e per il metodo @see #handle che gestisce 
 * una richiesta di condizione.
 * @author Palma Orlando
 */
public interface ConditionHandler {
    
    /**
     * Imposta il successivo gestore di condizioni nella catena di responsabilità.
     * @param h il successivo gestore di condizioni nella catena.
     */
    public void setNext(ConditionHandler h);
    
    
    /**
     * Questo metodo astratto è responsabile di gestire richieste relative a condizioni logiche,
     * applicando un'operazione specifica in base alla richiesta fornita. La logica può essere invertita
     * utilizzando il parametro booleano 'logic'.
     * 
     * @param request la richiesta di condizione da gestire.
     * @param param parametri aggiuntivi necessari per la gestione della richiesta di condizione.
     * @param logic se true, applica la logica invertita del NOT alla condizione, se false, applica la logica normale.
     * @return Il risultato della gestione della richiesta di condizione, che può includere la valutazione della condizione
     *                  o il passaggio della richiesta al successivo gestore nella catena.
     */
    public abstract Condition handle(String request, String param, boolean logic);
    
}
