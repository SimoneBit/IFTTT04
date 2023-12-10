package ConditionHandlers;

import Condition.*;

/**
 * La classe DayOfYearConditionHandler è un gestore di condizioni che si occupa di creare istanze di 
 * @see DayOfYearCondition in risposta a richieste specifiche relative al giorno dell'anno.
 * Questa classe estende la classe @see BaseConditionHandler per sfruttare la catena di gestione delle richieste.
 * 
 * Ogni istanza di questo gestore può essere collegata a un successivo gestore della catena tramite @see next.
 * Quando una richiesta è ricevuta, il gestore decide se gestirla o passarla al successivo nella catena.
 * @author Simone Pacifico
 */
public class DayOfYearConditionHandler extends BaseConditionHandler{
    
    /**
     * Gestisce la richiesta di creare una condizione basata sul giorno dell'anno. Se la richiesta specifica "Il", estrae 
     * il giorno e il mese dalla stringa del parametro, crea un'istanza di DayOfYearCondition con i valori specificati e la 
     * logica desiderata. Altrimenti, passa la richiesta al successivo gestore nella catena.
     * 
     * @param request la richiesta da gestire.
     * @param param il parametro associato alla richiesta (deve essere nel formato "giorno/mese").
     * @param logic la logica associata alla condizione (se deve essere negata o meno).
     * @return un'istanza di DayOfYearCondition se la richiesta è "Il", altrimenti, passa la richiesta al successivo gestore nella catena.
     */
    @Override
    public Condition handle(String request, String param, boolean logic) {
        if(request.compareTo("Il") == 0){
            String []values = param.split("/");
            Integer day = Integer.valueOf(values[0]);
            Integer month = Integer.valueOf(values[1]);
            Condition c = new DayOfYearCondition(month, day, logic);
            return c;
        }else{
            if(next != null){
                return next.handle(request, param, logic);
            }
        }
        return null;
    }
}
