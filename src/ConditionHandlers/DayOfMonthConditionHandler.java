package ConditionHandlers;

import Condition.Condition;
import Condition.DayOfMonthCondition;

/**
 *La classe DayOfMonthConditionHandler estende la classe @see BaseConditionHandler e implementa la gestione 
 * delle richieste di condizioni legate ai giorni del mese all'interno di una catena di responsabilità.
 * 
 * Questo gestore di condizioni può gestire richieste specifiche creando un'istanza di DayOfMonthCondition con il giorno 
 * specificato e restituendola. Se la richiesta non è gestita, passa la richiesta al successivo gestore di condizioni nella catena.
 * @author Simone Pacifico
 */
public class DayOfMonthConditionHandler extends BaseConditionHandler {
    
    /**
     * Gestisce la creazione di un'istanza di @seeDayOfMonthCondition in risposta a una richiesta specifica. 
     * Se la richiesta è "Il giorno", crea una condizione basata sul giorno del mese specificato e sulla logica specificata. 
     * Altrimenti, delega la gestione della richiesta al successivo gestore condizioni nella catena, se presente.
     * 
     * @param request la richiesta da gestire.
     * @param param il parametro associato alla richiesta (nel caso di "Il giorno", rappresenta il giorno del mese).
     * @param logic la logica associata alla condizione (se deve essere negata o meno).
     * @return un'istanza di DayOfMonthCondition se la richiesta è "Il giorno", altrimenti, passa la richiesta al successivo gestore nella catena.
     * 
     */
    @Override
    public Condition handle(String request, String param, boolean logic){
        if(request.compareTo("Il giorno") == 0){
            Integer day = Integer.valueOf(param);
            Condition c = new DayOfMonthCondition(day, logic);
            return c;
        }else{
            if(next != null){
                return next.handle(request, param, logic);
            }
        }
        return null;
    }
}
