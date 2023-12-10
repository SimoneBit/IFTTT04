package ConditionHandlers;

import Condition.Condition;
import Condition.DayOfWeekCondition;

/**
 * La classe DayOfWeekConditionHandler è un gestore di condizioni che si occupa di creare istanze di 
 * @see DayOfWeekCondition in risposta a richieste specifiche relative al giorno della settimana.
 * Questa classe estende la classe @see BaseConditionHandler per sfruttare la catena di gestione delle richieste.
 * 
 * Ogni istanza di questo gestore può essere collegata a un successivo gestore della catena tramite @see next.
 * Quando una richiesta è ricevuta, il gestore decide se gestirla o passarla al successivo nella catena.
 * @author Palma Orlando
 */
public class DayOfWeekConditionHandler extends BaseConditionHandler{

    /**
     * Gestisce la richiesta di creare una condizione basata sul giorno della settimana.
     * Se la richiesta specifica "Il giorno della settimana è", crea un'istanza di @see DayOfWeekCondition con il giorno 
     * specificato e la logica desiderata. Altrimenti, passa la richiesta al successivo gestore nella catena, se presente.
     * 
     * @param request la richiesta da gestire.
     * @param param il parametro associato alla richiesta (nel caso di "Il giorno della settimana è", rappresenta il giorno della settimana).
     * @param logic la logica associata alla condizione (se deve essere negata o meno).
     * @return un'istanza di DayOfWeekCondition se la richiesta è "Il giorno della settimana è", altrimenti, passa la richiesta al successivo gestore nella catena.
     */
    @Override
    public Condition handle(String request, String param, boolean logic) {
        if(request.compareTo("Il giorno della settimana è") == 0){
            Condition c = new DayOfWeekCondition(param, logic);
            return c;
        }else{
            if(next != null){
                return next.handle(request, param,logic);
            }
        }
        return null;
    }
    
}
