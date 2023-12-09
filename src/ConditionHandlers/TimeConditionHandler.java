package ConditionHandlers;

import Condition.*;

/**
 * La classe TimeConditionHandler estende la classe @see BaseConditionHandler e implementa la gestione 
 * delle richieste di condizioni legate agli orari all'interno di una catena di responsabilità.
 * 
 * Questo gestore di condizioni può gestire richieste specifiche creando un'istanza di TimeOfDayCondition con l'orario 
 * specificato e restituendola. Se la richiesta non è gestita, passa la richiesta al successivo gestore di condizioni nella catena.
 * @author Palma Orlando
 */
public class TimeConditionHandler extends BaseConditionHandler{
       
    /**
     * Gestisce la richiesta di condizione legata agli orari in base alle regole definite dall'implementazione.
     * Se la richiesta è "Alle", crea un'istanza di @see TimeOfDayCondition con l'orario specificato e la restituisce.
     * Altrimenti, passa la richiesta al successivo gestore di condizioni nella catena, se presente.
     *
     * @param request la richiesta di condizione da gestire.
     * @param param Pparametri aggiuntivi necessari per la gestione della richiesta di condizione.
     * @return un'istanza di  TimeOfDayCondition se la richiesta è stata gestita, altrimenti il risultato della gestione da parte 
     *                  del successivo gestore di condizioni nella catena, o null se nessun gestore può gestire la richiesta.
     */
    @Override
    public Condition handle(String request, String param, boolean logic) {
        if(request.compareTo("Alle") == 0){
            Condition c = new TimeOfDayCondition(param, logic);
            return c;
        }else{
            if(next != null){
                return next.handle(request, param, logic);
            }
        }
        return null;
    }
    
    
}
