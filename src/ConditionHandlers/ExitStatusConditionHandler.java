package ConditionHandlers;

import Condition.*;

/**
 *
 * @author Palma Orlando
 */
public class ExitStatusConditionHandler extends BaseConditionHandler {

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

