package ConditionHandlers;

import Condition.*;

/**
 * La classe ExitStatusConditionHandler è un gestore di condizioni che si occupa di creare istanze di 
 * @see ExitStatusCondition in risposta a richieste specifiche relative al valore di uscita di un programma.
 * Questa classe estende la classe @see BaseConditionHandler per sfruttare la catena di gestione delle richieste.
 * 
 * Ogni istanza di questo gestore può essere collegata a un successivo gestore della catena tramite @see next.
 * Quando una richiesta è ricevuta, il gestore decide se gestirla o passarla al successivo nella catena.
 * @author Palma Orlando
 */
public class ExitStatusConditionHandler extends BaseConditionHandler {

    /**
     * Gestisce la richiesta di creare una condizione basata sul valore di uscita di un programma. Se la richiesta 
     * specifica "Controlla il valore", estrae il percorso del programma e il valore atteso dalla stringa del parametro,
     * crea un'istanza di @see ExitStatusCondition con i valori specificati e la logica desiderata.
     * Altrimenti, passa la richiesta al successivo gestore nella catena, se presente.
     * 
     * @param request la richiesta da gestire.
     * @param param il parametro associato alla richiesta (deve essere nel formato "percorso/programma Valore atteso: valore").
     * @param logic la logica associata alla condizione (se deve essere negata o meno).
     * @return un'istanza di @see ExitStatusCondition se la richiesta è "Controlla il valore", altrimenti, passa la richiesta 
     *                 al successivo gestore nella catena.
     */
    @Override
    public Condition handle(String request, String param, boolean logic) {
        if (request.equals("Controlla il valore")) {
            // Creare e restituire un'istanza di ExitStatusCondition
            try {
                String[] params = param.split(" Valore atteso: ");
                String programPath = params[0];
                int expectedValue = Integer.parseInt(params[1].trim()) ;
                return new ExitStatusCondition(programPath, expectedValue, logic);
            } catch (Exception e) {
                // Gestisci eventuali errori durante la creazione dell'istanza
                e.printStackTrace();
            }
        } else {
            if (next != null) {
                return next.handle(request, param, logic);
            }
        }
        return null;
    }
    
    
}

